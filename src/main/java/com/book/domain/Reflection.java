package com.book.domain;

import java.io.Serializable;

public class Reflection implements Serializable{
	private long reflectionId;
	private String name;
	private String bookName;
	private int readerId;
	private String introduction;
	private int likeNum;
	private int classId;
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public long getReflectionId() {
		return reflectionId;
	}
	public void setReflectionId(long reflectionId) {
		this.reflectionId = reflectionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getReaderId() {
		return readerId;
	}
	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
}
