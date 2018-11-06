package com.mall.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.mapper.TbItemCatMapper;
import com.mall.pojo.TbItemCat;
import com.mall.pojo.TbItemCatExample;
import com.mall.pojo.TbItemCatExample.Criteria;
import com.mall.rest.pojo.CatNode;
import com.mall.rest.pojo.CatResult;
import com.mall.rest.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		List list = getCatListByParentId(0);
		CatResult result = new CatResult();
		result.setData(list);
		return result;
	}
	
	private List getCatListByParentId(long parentId){
		
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
		List catNodes = new ArrayList<>();
		int i = 0;
		for(TbItemCat itemCat : tbItemCats) {
			i++;
			if (itemCat.getIsParent()) {	//父节点
				CatNode catNode = new CatNode();
				catNode.setUrl("/products/"+itemCat.getId()+".html");
				
				if (itemCat.getParentId() == 0) {	//一级节点
					catNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
				}else {		//中间节点:非一级节点、非叶子节点
					catNode.setName(itemCat.getName());
				}
				
				catNode.setItems(getCatListByParentId(itemCat.getId()));
				catNodes.add(catNode);
				
			}else {		//叶子节点
				catNodes.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
			}
			//TODO 页面只能显示14条,暂时这样处理
			if (i>=14) {
				return catNodes;
			}
		}
		return catNodes;
	}
	
	
}
