package com.bs.controller;


import com.bs.pojo.User;
import com.bs.pojo.address;
import com.bs.service.userInforService;
import com.bs.service.userService;
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
@RequestMapping("user")
public class userController {
    @Resource
    private userService userService;

    @Resource
    private userInforService inforService;

    //管理员登录接口
    @RequestMapping("adminLogin")
    public ResultEntity<User> adminLogin(@RequestBody User data) {
        String userName = data.getUserName();
        String password = data.getPassword();
        User user = userService.loginAdmin(userName, password);
        if(user==null){
            return ResultEntity.infor(ResultCode.ERROR);
        }else
        if (userName.equals(user.getUserName()) && password.equals(user.getPassword()) && user.getLevel() == 99) {
            return ResultEntity.infor(ResultCode.SUCCESS);
        } else {
            return ResultEntity.infor(ResultCode.ERROR);
        }
    }

    //用户登录接口
    @RequestMapping("conLogin")
    public ResultEntity<User> conLogin(@RequestBody User data) {
        String userName = data.getUserName();
        String password = data.getPassword();
        User user = userService.loginCon(userName, password);
        if(user==null){
            return ResultEntity.infor(ResultCode.ERROR);
        }else
        if (userName.equals(user.getUserName()) && password.equals(user.getPassword()) ) {
            return ResultEntity.infor(ResultCode.SUCCESS,user);
        } else {
            return ResultEntity.infor(ResultCode.ERROR);
        }
    }

    @RequestMapping("selectUsers")
    public ResultEntity<List<Map<String,Object>>> selectUsers(){
        List<Map<String,Object>> userList =userService.selectUsers();
        return ResultEntity.infor(ResultCode.SUCCESS,userList);
    }

    @RequestMapping("deleteUser")
    public ResultEntity deleteUser(@RequestBody String userId){
        int id=Integer.parseInt(userId);
        userService.deleteUser(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("resetPassword")
    public ResultEntity resetPassword(@RequestBody String userId){
        int id=Integer.parseInt(userId);
        userService.resetPassword(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectUsersByuserName")
    public ResultEntity<List<Map<String,Object>>> selectUsersByuserName(@RequestBody String name){
        List<Map<String,Object>> userList =userService.selectUsersByuserName(name);
        return ResultEntity.infor(ResultCode.SUCCESS,userList);
    }

    @RequestMapping("selectRegisterUserName")
    public ResultEntity<User> selectRegisterUserName(@RequestBody String userName){
        User user=userService.selectRegisterUserName(userName);
        return ResultEntity.infor(ResultCode.SUCCESS,user);
    }

    @RequestMapping("Register")
    public ResultEntity Register(@RequestBody User user){
        userService.Register(user);
        User user1=userService.selectRegisterUserName(user.getUserName());
        inforService.addUserInfo(user1.getId());
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("ResetPassworSelf")
    public ResultEntity<User> ResetPassworSelf(@RequestBody User data) {
        String password = data.getPassword();
        int id=data.getId();
        userService.ResetPassworSelf(id,password);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("selectAUser")
    public ResultEntity<User> selectAUser(@RequestBody String userId){
        int id =Integer.parseInt(userId);
        User user=userService.selectAUser(id);
        return ResultEntity.infor(ResultCode.SUCCESS,user);
    }
}
