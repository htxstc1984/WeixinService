package com.itg.weixin.model;

public class ButtonEntity {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column itg_weixin_user_button.id
	 * @mbggenerated
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column itg_weixin_user_button.userid
	 * @mbggenerated
	 */
	private Long userid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column itg_weixin_user_button.createdate
	 * @mbggenerated
	 */
	private String createdate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column itg_weixin_user_button.isactived
	 * @mbggenerated
	 */
	private String isactived;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column itg_weixin_user_button.buttonjson
	 * @mbggenerated
	 */
	private String buttonjson;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column itg_weixin_user_button.id
	 * @return  the value of itg_weixin_user_button.id
	 * @mbggenerated
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column itg_weixin_user_button.id
	 * @param id  the value for itg_weixin_user_button.id
	 * @mbggenerated
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column itg_weixin_user_button.userid
	 * @return  the value of itg_weixin_user_button.userid
	 * @mbggenerated
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column itg_weixin_user_button.userid
	 * @param userid  the value for itg_weixin_user_button.userid
	 * @mbggenerated
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column itg_weixin_user_button.createdate
	 * @return  the value of itg_weixin_user_button.createdate
	 * @mbggenerated
	 */
	public String getCreatedate() {
		return createdate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column itg_weixin_user_button.createdate
	 * @param createdate  the value for itg_weixin_user_button.createdate
	 * @mbggenerated
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate == null ? null : createdate.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column itg_weixin_user_button.isactived
	 * @return  the value of itg_weixin_user_button.isactived
	 * @mbggenerated
	 */
	public String getIsactived() {
		return isactived;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column itg_weixin_user_button.isactived
	 * @param isactived  the value for itg_weixin_user_button.isactived
	 * @mbggenerated
	 */
	public void setIsactived(String isactived) {
		this.isactived = isactived == null ? null : isactived.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column itg_weixin_user_button.buttonjson
	 * @return  the value of itg_weixin_user_button.buttonjson
	 * @mbggenerated
	 */
	public String getButtonjson() {
		return buttonjson;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column itg_weixin_user_button.buttonjson
	 * @param buttonjson  the value for itg_weixin_user_button.buttonjson
	 * @mbggenerated
	 */
	public void setButtonjson(String buttonjson) {
		this.buttonjson = buttonjson == null ? null : buttonjson.trim();
	}
}