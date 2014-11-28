package com.itg.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itg.weixin.data.UserEntityMapper;
import com.itg.weixin.model.UserEntity;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	public UserEntityMapper userMapper;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insert(UserEntity user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	@Override
	public int update(UserEntity user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserEntity> selectAll() {
		// TODO Auto-generated method stub
		return userMapper.selectAll();
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserEntity findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.selectByName(userName);
	}

	@Override
	public UserEntity selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub)
		return userMapper.selectByPrimaryKey(id);
	}

}
