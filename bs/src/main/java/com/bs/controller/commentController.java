package com.bs.controller;

import com.bs.service.commentService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("comment")
public class commentController {
    @Resource
    private commentService service;

    @RequestMapping("selectComments")
    public ResultEntity<List<Map<String, Object>>> selectComments(){
        List<Map<String, Object>> nopayList =service.selectComments();
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("selectCommentsBygoodId")
    public ResultEntity<List<Map<String, Object>>> selectCommentsBygoodId(@RequestBody String goodId){
        int id=Integer.parseInt(goodId);
        List<Map<String, Object>> nopayList =service.selectCommentsBygoodId(id);
        return ResultEntity.infor(ResultCode.SUCCESS,nopayList);
    }

    @RequestMapping("deleteComment")
    public ResultEntity deleteComment(@RequestBody String commentId){
        int id=Integer.parseInt(commentId);
        service.deleteComment(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("addComment")
    public ResultEntity addComment(@RequestBody Map<String, Object> obj){
        service.addComment(obj);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
