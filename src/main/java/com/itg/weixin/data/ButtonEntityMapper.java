package com.itg.weixin.data;

import com.itg.weixin.model.ButtonEntity;

public interface ButtonEntityMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table itg_weixin_user_button
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table itg_weixin_user_button
	 * @mbggenerated
	 */
	int insert(ButtonEntity record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table itg_weixin_user_button
	 * @mbggenerated
	 */
	int insertSelective(ButtonEntity record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table itg_weixin_user_button
	 * @mbggenerated
	 */
	ButtonEntity selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table itg_weixin_user_button
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(ButtonEntity record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table itg_weixin_user_button
	 * @mbggenerated
	 */
	int updateByPrimaryKeyWithBLOBs(ButtonEntity record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table itg_weixin_user_button
	 * @mbggenerated
	 */
	int updateByPrimaryKey(ButtonEntity record);
}