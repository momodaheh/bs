package com.bs.serviceImpl;


import com.bs.mapper.BaseMapper;
import com.bs.mapper.addressMapper;
import com.bs.mapper.commentMapper;
import com.bs.pojo.address;
import com.bs.pojo.comment;
import com.bs.service.addressService;
import com.bs.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class commentServiceImpl extends BaseServiceImpl<comment> implements commentService {
    @Autowired
    private commentMapper mapper;

    @Override
    public BaseMapper<comment> getBaseMapper(){
        return mapper;
    }

    @Override
    public List<Map<String, Object>> selectComments() {
        try {
            return mapper.selectComments();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectCommentsBygoodId(int goodId) {
        try {
            return mapper.selectCommentsBygoodId(goodId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteComment(int id) {
        try {
            mapper.deleteComment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addComment(Map<String, Object> obj) {
        try {
            mapper.addComment(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
