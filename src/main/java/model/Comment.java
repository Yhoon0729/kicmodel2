package model;

import java.util.Date;

public class Comment {
	private int ser;
	private int num;
	private String content;
	private Date regdate;
	
	public int getSer() {
		return ser;
	}
	public void setSer(int ser) {
		this.ser = ser;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "Comment [ser=" + ser + ", num=" + num + ", content=" + content + ", regdate=" + regdate + "]";
	}
	
}
