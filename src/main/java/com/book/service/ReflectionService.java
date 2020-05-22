package com.book.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.dao.ReflectionDao;
import com.book.domain.Book;
import com.book.domain.Reflection;
@Service
public class ReflectionService {
	    private ReflectionDao reflectionDao;

	    @Autowired
	    public void setReflectionDao(ReflectionDao reflectionDao) {
	        this.reflectionDao = reflectionDao;
	    }
	    public ArrayList<Reflection> queryReflectionByClassId(int classId){
	    	return reflectionDao.queryReflectionByClassId(classId);
	    }
	    public ArrayList<Reflection> queryReflectionOrderByLikeNum(){
	    	return reflectionDao.queryReflectionOrderByLikeNum();
	    }
	    public ArrayList<Reflection> queryReflection(String searchWord){
	        return  reflectionDao.queryReflection(searchWord);
	    }

	    public ArrayList<Reflection> getAllReflections(){
	        return reflectionDao.getAllReflections();
	    }

	    public int deleteReflection(long reflectionId){
	        return reflectionDao.deleteReflection(reflectionId);
	    }

	    public boolean matchReflection(String searchWord){
	        return reflectionDao.matchReflection(searchWord)>0;
	    }

	    public boolean addReflection(Reflection reflection){
	        return reflectionDao.addReflection(reflection)>0;
	    }

	    public Reflection getReflection(Long reflectionId){
	        Reflection reflection = reflectionDao.getReflection(reflectionId);
	        return reflection;
	    }
	    public boolean editReflection(Reflection reflection){
	        return reflectionDao.editReflection(reflection)>0;
	    }

}
