package com.mall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.portal.service.ContentService;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	
	
	@RequestMapping("/index")
	//@ResponseBody
	public String showIndex(Model model) {
		model.addAttribute("ad1", contentService.getIndexBigAD());
		return "index";
	}
	
}
