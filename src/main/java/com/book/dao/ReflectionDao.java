package com.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.book.domain.Reflection;

@Repository
public class ReflectionDao {
	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADD_REFLECTION_SQL="INSERT INTO reflection_info VALUES(NULL ,?,?,?,?,?,?)";
    private final static String DELETE_REFLECTION_SQL="delete from reflection_info where reflection_id = ?  ";
    private final static String EDIT_REFLECTION_SQL="update reflection_info set name= ? ,book_name= ? ,reader_id= ? ,introduction= ? ,like_num= ? ,class_id = ? WHERE reflection_id = ?;";
    private final static String QUERY_ALL_REFLECTIONS_SQL="SELECT * FROM reflection_info ";
    private final static String QUERY_REFLECTION_SQL="SELECT * FROM reflection_info WHERE reflection_id like  ?  or name like ?   ";
    //查询匹配读后感的个数
    private final static String MATCH_REFLECTION_SQL="SELECT count(*) FROM reflection_info WHERE reflection_id like ?  or name like ?  ";
    //根据书号查询读后感
    private final static String GET_REFLECTION_SQL="SELECT * FROM reflection_info WHERE reflection_id = ? ";
    //根据classID 查询读后感
    private final static String QUERY_REFLECTION_BY_CLASSID = "SELECT * FROM reflection_info WHERE class_id = ? ORDER BY like_num DESC";
    //查询所有读后感，按likeNum排序
    private final static String QUERY_REFLECTION_ORDERBY_LIKENUM = "SELECT * FROM reflection_info ORDER BY like_num DESC";
    
    public int matchReflection(String searchWord){
    	//只要标题中含有searchWord字符即可
        String swcx="%"+searchWord+"%";
        return jdbcTemplate.queryForObject(MATCH_REFLECTION_SQL,new Object[]{swcx,swcx},Integer.class);
    }
    public ArrayList<Reflection> queryReflectionByClassId(int classId){
        final ArrayList<Reflection> reflections=new ArrayList<Reflection>();
        jdbcTemplate.query(QUERY_REFLECTION_BY_CLASSID, new Object[]{classId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Reflection reflection =new Reflection();
                    reflection.setReflectionId(resultSet.getLong("reflection_id"));
                    reflection.setName(resultSet.getString("name"));
                    reflection.setBookName(resultSet.getString("book_name"));
                    reflection.setReaderId(resultSet.getInt("reader_id"));
                    reflection.setIntroduction(resultSet.getString("introduction"));
                    reflection.setLikeNum(resultSet.getInt("like_num"));
                    reflections.add(reflection);
                }

            }
        });
        return reflections;
    }
    public ArrayList<Reflection> queryReflectionOrderByLikeNum(){
        final ArrayList<Reflection> reflections=new ArrayList<Reflection>();
        jdbcTemplate.query(QUERY_REFLECTION_ORDERBY_LIKENUM, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Reflection reflection =new Reflection();
                    reflection.setReflectionId(resultSet.getLong("reflection_id"));
                    reflection.setName(resultSet.getString("name"));
                    reflection.setBookName(resultSet.getString("book_name"));
                    reflection.setReaderId(resultSet.getInt("reader_id"));
                    reflection.setIntroduction(resultSet.getString("introduction"));
                    reflection.setLikeNum(resultSet.getInt("like_num"));
                    reflections.add(reflection);
                }

            }
        });
        return reflections;
    }
    
    //返回查询的结果
    public ArrayList<Reflection> queryReflection(String sw){
        String swcx="%"+sw+"%";
        final ArrayList<Reflection> reflections=new ArrayList<Reflection>();
        jdbcTemplate.query(QUERY_REFLECTION_SQL, new Object[]{swcx,swcx}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Reflection reflection =new Reflection();
                    reflection.setReflectionId(resultSet.getLong("reflection_id"));
                    reflection.setName(resultSet.getString("name"));
                    reflection.setBookName(resultSet.getString("book_name"));
                    reflection.setReaderId(resultSet.getInt("reader_id"));
                    reflection.setIntroduction(resultSet.getString("introduction"));
                    reflection.setLikeNum(resultSet.getInt("like_num"));
                    reflections.add(reflection);
                }

            }
        });
        return reflections;
    }

    public ArrayList<Reflection> getAllReflections(){
        final ArrayList<Reflection> reflections=new ArrayList<Reflection>();

        jdbcTemplate.query(QUERY_ALL_REFLECTIONS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                    while (resultSet.next()){
                    	Reflection reflection =new Reflection();
                        reflection.setReflectionId(resultSet.getLong("reflection_id"));
                        reflection.setName(resultSet.getString("name"));
                        reflection.setBookName(resultSet.getString("book_name"));
                        reflection.setReaderId(resultSet.getInt("reader_id"));
                        reflection.setIntroduction(resultSet.getString("introduction"));
                        reflection.setLikeNum(resultSet.getInt("like_num"));
                        reflections.add(reflection);
                    }
            }
        });
        return reflections;

    }

    public int deleteReflection(long reflectionId){

        return jdbcTemplate.update(DELETE_REFLECTION_SQL,reflectionId);
    }

    public int addReflection(Reflection reflection){
    	String name = reflection.getName();
    	String bookName = reflection.getBookName();
    	int readerId = reflection.getReaderId();
    	int likeNum = reflection.getLikeNum();
    	String introduction = reflection.getIntroduction();
    	int classId = reflection.getClassId();

        return jdbcTemplate.update(ADD_REFLECTION_SQL,new Object[]{name,bookName,readerId,introduction,likeNum,classId});
    }

    public Reflection getReflection(Long reflectionId){
        final Reflection reflection =new Reflection();
        jdbcTemplate.query(GET_REFLECTION_SQL, new Object[]{reflectionId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
            	reflection.setReflectionId(resultSet.getLong("reflection_id"));
                reflection.setName(resultSet.getString("name"));
                reflection.setBookName(resultSet.getString("book_name"));
                reflection.setReaderId(resultSet.getInt("reader_id"));
                reflection.setIntroduction(resultSet.getString("introduction"));
                reflection.setLikeNum(resultSet.getInt("like_num"));
                reflection.setClassId(resultSet.getInt("class_id"));
            }

        });
        return reflection;
    }
    public int editReflection(Reflection reflection){
    	Long reflectionId = reflection.getReflectionId();
    	int readerId = reflection.getReaderId();
    	String bookName = reflection.getBookName();
    	String introduction = reflection.getIntroduction();
    	int like = reflection.getLikeNum();
    	String name = reflection.getName();
    	int classId = reflection.getClassId();
    	//update reflection_info set name= ? ,book_name= ? ,reader_id= ? ,introduction= ? ,like= ? WHERE book_id = ?;
        return jdbcTemplate.update(EDIT_REFLECTION_SQL,new Object[]{name,bookName,readerId,introduction,like,classId,reflectionId});
    }
}
