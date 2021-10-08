package com.qst.onlineVote.pojo;

import java.sql.Timestamp;

/**
ѡ����⣬
ѡ������
ͶƱ������
�õ�¼�˻��Ƿ���ͶƱ��
*/
public class Listing {
	private String title;//����
	private int optionNum;//ѡ����Ŀ
	private int voteNum;//ͶƱ����
	private boolean isVote;//�Ƿ��Ѿ�ͶƱ
	private boolean isEnd;//ͶƱ�Ƿ����
	private Timestamp endTime;//ͶƱ����ʱ��

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
