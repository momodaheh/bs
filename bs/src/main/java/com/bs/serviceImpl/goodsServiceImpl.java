package com.bs.serviceImpl;



import com.bs.mapper.BaseMapper;
import com.bs.mapper.addressMapper;

import com.bs.mapper.goodsMapper;
import com.bs.pojo.goods;

import com.bs.service.goodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class goodsServiceImpl extends BaseServiceImpl<goods> implements goodsService {
    @Autowired
    private goodsMapper mapper;

    @Override
    public BaseMapper<goods> getBaseMapper(){
        return mapper;
    }

    @Override
    public void addGoods(goods good) {
        try {
            mapper.addGoods(good);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> selectGoods() {
        try {
           return mapper.selectGoods();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteGood(int id) {
        try {
            mapper.deleteGood(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> selectGoodsPhone() {
        try {
            return mapper.selectGoodsPhone();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectGoodsCom() {
        try {
            return mapper.selectGoodsCom();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectGoodsByCategory(int id) {
        try {
            return mapper.selectGoodsByCategory(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectGoodsByName(String name) {
        try {
            return mapper.selectGoodsByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> selectGoodsById(int id) {
        try {
            return mapper.selectGoodsById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
