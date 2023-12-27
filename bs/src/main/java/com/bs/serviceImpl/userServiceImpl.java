package com.bs.serviceImpl;

import com.bs.mapper.BaseMapper;
import com.bs.mapper.UserMapper;
import com.bs.pojo.User;
import com.bs.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class userServiceImpl extends BaseServiceImpl<User> implements userService {

    @Autowired
    private UserMapper mapper;

    @Override
    public BaseMapper<User> getBaseMapper(){
        return mapper;
    }


    @Override
    public User loginAdmin(String userName, String password) {
        try {
            return mapper.loginAdmin(userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User loginCon(String userName, String password) {
        try {
            return mapper.loginCon(userName,password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectUsers() {
        try {
            return mapper.selectUsers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            mapper.deleteUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetPassword(int id) {
        try {
            mapper.resetPassword(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> selectUsersByuserName(String name) {
        try {
            return mapper.selectUsersByuserName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User selectRegisterUserName(String userName) {
        try {
            return mapper.selectRegisterUserName(userName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void Register(User user) {
        try {
            mapper.Register(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ResetPassworSelf(int userId, String password) {
        try {
            mapper.ResetPassworSelf(userId,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User selectAUser(int id) {
        try {
            return mapper.selectAUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
