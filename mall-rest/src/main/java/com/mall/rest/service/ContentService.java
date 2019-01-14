package com.mall.rest.service;

import java.util.List;

import com.mall.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContentsByCatId(Long catId) throws Exception;

}
