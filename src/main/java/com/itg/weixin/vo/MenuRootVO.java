package com.itg.weixin.vo;

import java.io.Serializable;
import java.util.List;

public class MenuRootVO implements Serializable {

	private List<MenuItemVO> button;

	public MenuRootVO() {
		// TODO Auto-generated constructor stub
	}

	public List<MenuItemVO> getButtons() {
		return button;
	}

	public void setButtons(List<MenuItemVO> button) {
		this.button = button;
	}

}
