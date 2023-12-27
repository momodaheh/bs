package com.bs.serviceImpl;


import com.bs.mapper.BaseMapper;
import com.bs.mapper.userInforMapper;
import com.bs.pojo.userInfor;
import com.bs.service.userInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Transactional
@Service
public class userInforServiceImpl extends BaseServiceImpl<userInfor> implements userInforService {

    @Autowired
    private userInforMapper mapper;

    @Override
    public BaseMapper<userInfor> getBaseMapper(){
        return mapper;
    }

    @Override
    public userInfor selectUserInfor(int id) {
        try {
            return mapper.selectUserInfor(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateUserInfor(userInfor obj) {
        try {
            mapper.updateUserInfor(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUserInfo(int id) {
        try {
            mapper.addUserInfo(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
