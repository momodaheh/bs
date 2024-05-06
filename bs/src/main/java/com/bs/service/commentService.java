package com.bs.service;


import com.bs.pojo.comment;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface commentService extends BaseService<comment>{

    List<Map<String,Object>> selectComments();


    List<Map<String,Object>> selectCommentsBygoodId(int goodId);
    void deleteComment(int id);
    void addComment(Map<String,Object> obj);

    List<Map<String,Object>> selectCommentByContent(String text);
}
