package com.bs.mapper;

import com.bs.pojo.comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface commentMapper extends BaseMapper<comment> {
    @Select("select c.*,g.*,ui.* from comments c join goods g on c.goodId=g.id \n" +
            "join user_infor ui on ui.id=c.userId\n" +
            "Where c.isDel=0")
    List<Map<String,Object>> selectComments() throws SQLException;

    @Select("select c.*,g.*,ui.* from comments c join goods g on c.goodId=g.id \n" +
            "join user_infor ui on ui.id=c.userId\n" +
            "Where c.isDel=0 and goodId=#{goodId}")
    List<Map<String,Object>> selectCommentsBygoodId(int goodId)throws SQLException;

    @Update("update comments set isDel=1 where id=#{id}")
    void deleteComment(int id)throws SQLException;

    @Insert("insert into comments (userId,goodId,content,createTime,isDel,commentType) values(#{userId},#{goodId},#{content},now(),0,#{commentType})")
    void addComment(Map<String,Object> obj)throws SQLException;
}
