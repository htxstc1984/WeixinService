package com.itg.weixin.vo;

import java.io.Serializable;

public class VotedVO implements Serializable {

	private boolean isVoted;

	private String vote_content;

	public VotedVO() {
		// TODO Auto-generated constructor stub
	}

	public boolean isVoted() {
		return isVoted;
	}

	public void setVoted(boolean isVoted) {
		this.isVoted = isVoted;
	}

	public String getVote_content() {
		return vote_content;
	}

	public void setVote_content(String vote_content) {
		this.vote_content = vote_content;
	}

}
