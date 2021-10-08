package com.qst.onlineVote.pojo;

import java.sql.Timestamp;

/**
选项标题，
选项数，
投票人数。
该登录账户是否已投票。
*/
public class Listing {
	private String title;//标题
	private int optionNum;//选项数目
	private int voteNum;//投票人数
	private boolean isVote;//是否已经投票
	private boolean isEnd;//投票是否结束
	private Timestamp endTime;//投票结束时间

	public Listing() {
		super();
	}

	public Listing(String title, int optionNum, int voteNum, boolean isVote, boolean isEnd, Timestamp endTime) {
		this.title = title;
		this.optionNum = optionNum;
		this.voteNum = voteNum;
		this.isVote = isVote;
		this.isEnd = isEnd;
		this.endTime = endTime;
	}

	public boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public boolean getIsVote() {
		return isVote;
	}
	public void setIsVote(boolean isVote) {
		this.isVote = isVote;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getOptionNum() {
		return optionNum;
	}
	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
	}
	public int getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}
	
}
