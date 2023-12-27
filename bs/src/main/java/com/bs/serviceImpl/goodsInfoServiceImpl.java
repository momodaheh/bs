package com.bs.serviceImpl;


import com.bs.mapper.BaseMapper;
import com.bs.mapper.addressMapper;
import com.bs.mapper.goodsInfoMapper;
import com.bs.pojo.address;
import com.bs.pojo.goodsInfo;
import com.bs.service.addressService;
import com.bs.service.goodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
@Service
public class goodsInfoServiceImpl extends BaseServiceImpl<goodsInfo> implements goodsInfoService {
    @Autowired
    private goodsInfoMapper mapper;

    @Override
    public BaseMapper<goodsInfo> getBaseMapper(){
        return mapper;
    }

    @Override
    public List<goodsInfo> selectGoodsInfoById(int id) {
        try {
            return mapper.selectGoodsInfoById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteGoodsInfo(int id) {
        try {
           mapper.deleteGoodsInfo(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGoodsInfo(goodsInfo goodsInfo) {
        try {
            mapper.updateGoodsInfo(goodsInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addGoodsInfo(goodsInfo goodsInfo) {
        try {
            mapper.addGoodsInfo(goodsInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
