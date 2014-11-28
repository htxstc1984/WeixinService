package com.itg.weixin.service;

import java.util.List;

import com.itg.weixin.model.UserEntity;

public interface UserService {

	public int insert(UserEntity user);

	public int update(UserEntity user);

	public int delete(Long id);

	public List<UserEntity> selectAll();

	public UserEntity selectByPrimaryKey(Long id);

	public int countAll();

	public UserEntity findByUserName(String userName);

}
