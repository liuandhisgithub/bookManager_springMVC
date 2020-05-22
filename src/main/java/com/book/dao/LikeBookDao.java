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
public class LikeBookDao {
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public static final String ADD_LIKE_BOOK = "INSERT INTO like_book VALUES(?,?,?);";
    public static final String DELETE_LIKE_BOOK = "DELETE FROM like_book WHERE reader_id = ? and book_id = ?;";
    public static final String MATCH_LIKE_BOOK = "SELECT COUNT(*) FROM like_book WHERE reader_id = ? and book_id = ?;";
    public static final String BOOK_LIKE_NUM_UP = "UPDATE book_info set like_num= IFNULL(like_num,0)+1 WHERE book_id = ?;";
    public static final String BOOK_LIKE_NUM_DOWN = "UPDATE book_info set like_num= IF(like_num < 1 , 0 ,like_num-1) WHERE book_id = ?;";
    //获取当前用户的收藏最多的classid
    public static final String READER_LIKE_BOOK_CLASSID = "SELECT IF(tab1.class_id = null , 0 ,tab1.class_id ) FROM (SELECT class_id,count(*) as class_num FROM like_book WHERE reader_id = ? GROUP BY class_id) as tab1 WHERE 1 = 1 ORDER BY tab1.class_num DESC LIMIT 1;";
    
    //获取当前用户所有收藏的书籍id
    public static final String READER_LIKE_BOOK_ID = "SELECT book_id FROM like_book WHERE reader_id = ?; ";
    
    public ArrayList<Long> getBookIdByReaderId(int readerId) {
    	ArrayList<Long> bookIds = new ArrayList<Long>();
    	jdbcTemplate.query(READER_LIKE_BOOK_ID, new Object[]{readerId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    bookIds.add(resultSet.getLong("book_id"));
                }
            }
        });
    	return bookIds;
    }
    
    public int matchLikeBook(int readerId , Long bookId){
    	return jdbcTemplate.queryForObject(MATCH_LIKE_BOOK,new Object[]{readerId,bookId},Integer.class);
    }
    
    public int addLikeBook(int readerId,Long bookId,int classId) {
    	return jdbcTemplate.update(ADD_LIKE_BOOK,new Object[]{readerId,bookId,classId});
    }
    
    public int deleteLikeBook(int readerId,Long bookId) {
    	return jdbcTemplate.update(DELETE_LIKE_BOOK,new Object[]{readerId,bookId});
    }
    public int bookLikeNumUp(Long bookId) {
    	return jdbcTemplate.update(BOOK_LIKE_NUM_UP,new Object[]{bookId});
    }
    public int bookLikeNumDown(Long bookId) {
    	return jdbcTemplate.update(BOOK_LIKE_NUM_DOWN,new Object[]{bookId});
    }
    public int getReaderLikeBookClass(int readerId) {
    	int classId ;
    	try {
        	classId = jdbcTemplate.queryForObject(READER_LIKE_BOOK_CLASSID,new Object[]{readerId},Integer.class);
    	}
    	catch(Exception e) {
    		classId = -1;
    	}
    	//return jdbcTemplate.queryForObject(READER_LIKE_BOOK_CLASSID,new Object[]{readerId},Integer.class);
    	return classId;
    }
}
