package com;

public class BookSet {
	String[] name = new String[50];		//数组1存储图书名称
	int[] state = new int[50];			//数组2存储图书借出状态：0->已借出 / 1->可借
	String[] date = new String[50];		//数组3存储图书借出日期
	int[] count = new int[50];			//数组4存储图书借出次数
	
	/*
	 * getters/setters方法
	 */
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
	public int[] getState() {
		return state;
	}
	public void setState(int[] state) {
		this.state = state;
	}
	public String[] getDate() {
		return date;
	}
	public void setDate(String[] date) {
		this.date = date;
	}
	public int[] getCount() {
		return count;
	}
	public void setCount(int[] count) {
		this.count = count;
	}
}

