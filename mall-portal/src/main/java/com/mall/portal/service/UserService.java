package com.mall.portal.service;

import com.mall.pojo.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
