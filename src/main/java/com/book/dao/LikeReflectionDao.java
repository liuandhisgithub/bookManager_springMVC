package com.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.book.domain.Book;
@Repository
public class LikeReflectionDao {
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public static final String ADD_LIKE_REFLECTION = "INSERT INTO like_reflection VALUES(?,?,?);";
    public static final String DELETE_LIKE_REFLECTION = "DELETE FROM like_reflection WHERE reader_id = ? and reflection_id = ?;";
    public static final String MATCH_LIKE_REFLECTION = "SELECT COUNT(*) FROM like_reflection WHERE reader_id = ? and reflection_id = ?;";
    public static final String REFLECTION_LIKE_NUM_UP = "UPDATE reflection_info set like_num= IFNULL(like_num,0)+1 WHERE reflection_id = ?;";
    public static final String REFLECTION_LIKE_NUM_DOWN = "UPDATE reflection_info set like_num= IF(like_num < 1 , 0 ,like_num-1) WHERE reflection_id = ?;";
    public static final String READER_LIKE_REFLECTION_CLASSID = "SELECT IF(tab1.class_id = null , 0 ,tab1.class_id ) FROM (SELECT class_id,count(*) as class_num FROM like_reflection WHERE reader_id = ? GROUP BY class_id) as tab1 WHERE 1 = 1 ORDER BY tab1.class_num DESC LIMIT 1;";
    public static final String READER_LIKE_REFLECTION_ID = "SELECT reflection_id FROM like_reflection WHERE reader_id = ?;";
    public int matchLikeReflection(int readerId , Long reflectionId){
    	return jdbcTemplate.queryForObject(MATCH_LIKE_REFLECTION,new Object[]{readerId,reflectionId},Integer.class);
    }
    
    public int addLikeReflection(int readerId,Long reflectionId,int classId) {
    	return jdbcTemplate.update(ADD_LIKE_REFLECTION,new Object[]{readerId,reflectionId,classId});
    }
    
    public int deleteLikeReflection(int readerId,Long reflectionId) {
    	return jdbcTemplate.update(DELETE_LIKE_REFLECTION,new Object[]{readerId,reflectionId});
    }
    public int reflectionLikeNumUp(Long reflectionId) {
    	return jdbcTemplate.update(REFLECTION_LIKE_NUM_UP,new Object[]{reflectionId});
    }
    public int reflectionLikeNumDown(Long reflectionId) {
    	return jdbcTemplate.update(REFLECTION_LIKE_NUM_DOWN,new Object[]{reflectionId});
    }
    public int getReaderLikeReflectionClass(int readerId) {
    	int classId ;
    	try {
        	classId = jdbcTemplate.queryForObject(READER_LIKE_REFLECTION_CLASSID,new Object[]{readerId},Integer.class);
    	}
    	catch(Exception e) {
    		classId = -1;
    	}
    	//return jdbcTemplate.queryForObject(READER_LIKE_BOOK_CLASSID,new Object[]{readerId},Integer.class);
    	return classId;
    }
    
    public ArrayList<Long> getReflectionIdByReaderId(int readerId){
    	ArrayList<Long> reflectionIds = new ArrayList<Long>();
    	jdbcTemplate.query(READER_LIKE_REFLECTION_ID, new Object[]{readerId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    reflectionIds.add(resultSet.getLong("reflection_id"));
                }
            }
        });
    	return reflectionIds;
    }
}
