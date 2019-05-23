package com.hauphvnjavaweb.model;

public class CommentModel extends AbstractModel<CommentModel>{
	private String content;
	private long userid;
	private long newsid;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getNewsid() {
		return newsid;
	}
	public void setNewsid(long newsid) {
		this.newsid = newsid;
	}
}
