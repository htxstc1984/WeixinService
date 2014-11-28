package com.itg.weixin.vo;

import java.io.Serializable;

public class ResultVO implements Serializable {

	private String type;

	private String result;

	private String ArticleCount;

	public ResultVO() {
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

}
