package com.itg.weixin.vo;

import java.io.Serializable;
import java.util.List;

public class MenuItemVO implements Serializable {

	private String type;

	private String name;

	private String url;

	private String key;

	private List<MenuItemVO> sub_button;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<MenuItemVO> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<MenuItemVO> sub_button) {
		this.sub_button = sub_button;
	}

	public MenuItemVO() {
		// TODO Auto-generated constructor stub
	}

}
