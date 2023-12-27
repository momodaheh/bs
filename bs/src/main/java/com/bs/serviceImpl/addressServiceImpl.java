package com.bs.serviceImpl;

import com.bs.mapper.BaseMapper;
import com.bs.mapper.addressMapper;
import com.bs.pojo.address;
import com.bs.service.addressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


@Transactional
@Service
public class addressServiceImpl extends BaseServiceImpl<address> implements addressService {

    @Autowired
    private addressMapper mapper;

    @Override
    public BaseMapper<address> getBaseMapper(){
        return mapper;
    }

    @Override
    public List<address> selectUserAddress(int userId) {
        try {
            return mapper.selectUserAddress(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addAddress(address address) {
        try {
            mapper.addAddress(address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAddress(int id) {
        try {
            mapper.deleteAddress(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAddress(address address) {
        try {
            mapper.updateAddress(address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
