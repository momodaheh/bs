package com.bs.controller;



import com.bs.pojo.userInfor;
import com.bs.service.userInforService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("userInfor")
public class userInforController {
    @Resource
    private userInforService service;

    @RequestMapping("selectUserInfor")
    public ResultEntity<userInfor> selectUserInfor(@RequestBody String userId){
        int id =Integer.parseInt(userId);
        userInfor user=service.selectUserInfor(id);
        return ResultEntity.infor(ResultCode.SUCCESS,user);
    }

    @RequestMapping("updateUserInfor")
    public ResultEntity updateUserInfor(@RequestBody userInfor user){
        service.updateUserInfor(user);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
