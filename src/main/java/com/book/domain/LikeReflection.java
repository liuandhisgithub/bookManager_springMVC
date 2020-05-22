package com.book.domain;

import java.io.Serializable;

public class LikeReflection implements Serializable{
	private Long reflectionId;
	private int readerid;
	private int classId;
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public Long getReflectionId() {
		return reflectionId;
	}
	public void setReflectionId(Long reflectionId) {
		this.reflectionId = reflectionId;
	}
	public int getReaderid() {
		return readerid;
	}
	public void setReaderid(int readerid) {
		this.readerid = readerid;
	}
}
