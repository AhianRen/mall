package com.mall.portal.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.portal.pojo.SearchResult;
import com.mall.portal.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(@RequestParam String q,@RequestParam(defaultValue="1")Integer page,Model model) {
		
		if (StringUtils.isBlank(q)) {
			return "search";
		}
		try {
			q = new String(q.getBytes("iso8859-1"), "utf-8");
			SearchResult searchResult = searchService.search(q,page);
			
			if (searchResult != null) {
				model.addAttribute("query", q);
				model.addAttribute("itemList", searchResult.getItems());
				model.addAttribute("page", searchResult.getCurPage());
				model.addAttribute("totalPages", searchResult.getPageCount());
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "search";
		
	}
	
	
}
