package com.book.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.LikeBookDao;

@Service
public class LikeBookService {
	private LikeBookDao likeBookDao;

	public LikeBookDao getLikeBookDao() {
		return likeBookDao;
	}
	@Autowired
	public void setLikeBookDao(LikeBookDao likeBookDao) {
		this.likeBookDao = likeBookDao;
	}
	
	public int deleteLikeBook(int readerId,Long bookId) {
		int deleteJud1 = likeBookDao.deleteLikeBook(readerId, bookId);
		int deleteJud2 = likeBookDao.bookLikeNumDown(bookId);
		return deleteJud1;
		
	}

	public int addLikeBook(int readerId,Long bookId,int classId) {
		int deleteJud1 = likeBookDao.addLikeBook(readerId, bookId,classId);
		int deleteJud2 = likeBookDao.bookLikeNumUp(bookId);
		return deleteJud1;
		
	}
	public boolean matchLikeBook(int readerId,Long bookId) {
		return likeBookDao.matchLikeBook(readerId, bookId)>0;
	}
	
	public int getReaderLikeBookClass(int readerId) {
		return likeBookDao.getReaderLikeBookClass(readerId);
	}
	
	public ArrayList<Long> getBookIdByReaderId(int readerId){
		return likeBookDao.getBookIdByReaderId(readerId);
	}
	
}
