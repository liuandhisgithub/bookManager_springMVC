package com.book.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.LikeReflectionDao;

@Service
public class LikeReflectionService {
	private LikeReflectionDao likeReflectionDao;

	public LikeReflectionDao getLikeReflectionDao() {
		return likeReflectionDao;
	}
	@Autowired
	public void setLikeReflectionDao(LikeReflectionDao likeReflectionDao) {
		this.likeReflectionDao = likeReflectionDao;
	}
	
	public int deleteLikeReflection(int readerId,Long reflectionId) {
		int deleteJud1 = likeReflectionDao.deleteLikeReflection(readerId, reflectionId);
		int deleteJud2 = likeReflectionDao.reflectionLikeNumDown(reflectionId);
		return deleteJud1;
		
	}

	public int addLikeReflection(int readerId,Long reflectionId,int classId) {
		int deleteJud1 = likeReflectionDao.addLikeReflection(readerId, reflectionId,classId);
		int deleteJud2 = likeReflectionDao.reflectionLikeNumUp(reflectionId);
		return deleteJud1;
		
	}
	public boolean matchLikeReflection(int readerId,Long reflectionId) {
		return likeReflectionDao.matchLikeReflection(readerId, reflectionId)>0;
	}
	public int getReaderLikeReflectionClass(int readerId) {
		return likeReflectionDao.getReaderLikeReflectionClass(readerId);
	}
	public ArrayList<Long> getReflectionIdByReaderId(int readerId){
		return likeReflectionDao.getReflectionIdByReaderId(readerId);
	}
}

