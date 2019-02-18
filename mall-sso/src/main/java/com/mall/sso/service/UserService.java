package com.mall.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mall.common.pojo.MallResult;
import com.mall.pojo.TbUser;

public interface UserService {

	MallResult checkData(String content, Integer type);
	MallResult createUser(TbUser user);
	MallResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	MallResult getUserByToken(String token);
}
