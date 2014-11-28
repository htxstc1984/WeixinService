package com.itg.weixin.service;

import java.util.List;

import com.itg.weixin.model.ButtonEntity;

public interface ButtonService {
	public int insert(ButtonEntity user);

	public int update(ButtonEntity user);

	public int delete(Long id);

	public List<ButtonEntity> selectAll();

	public ButtonEntity selectByPrimaryKey(Long id);

	public int countAll();
}
