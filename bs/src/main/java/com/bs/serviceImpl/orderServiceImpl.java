package com.bs.serviceImpl;


import com.bs.mapper.BaseMapper;
import com.bs.mapper.addressMapper;
import com.bs.mapper.orderMapper;
import com.bs.pojo.address;
import com.bs.pojo.goods;
import com.bs.pojo.order;
import com.bs.service.addressService;
import com.bs.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class orderServiceImpl extends BaseServiceImpl<order> implements orderService {
    @Autowired
    private orderMapper mapper;

    @Override
    public BaseMapper<order> getBaseMapper(){
        return mapper;
    }

    @Override
    public order selectOrderbyId(int id) {
        try {
           return mapper.selectOrderbyId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectOrders() {
        try {
            return mapper.selectOrders();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addOrder(Map<String,Object> obj) {
        try {
            mapper.addOrder(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void orderPay(int id, String payId) {
        try {
            mapper.orderPay(id,payId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try {
            mapper.deleteOrder(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> selectNopay(int id) {
        try {
            return mapper.selectNopay(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> selectOrderInfo(Map<String, Object> obj) {
        try {
            return mapper.selectOrderInfo(obj);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public goods selectgood(int id) {
        try {
            return mapper.selectgood(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectOrdersStateOne() {
        try {
            return mapper.selectOrdersStateOne();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void fahuo(int id, String logisticsId) {
        try {
            mapper.fahuo(id,logisticsId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> selectNoReceipt(int id) {
        try {
            return mapper.selectNoReceipt(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectAll(int id) {
        try {
            return mapper.selectAll(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectDailyPrice() {
        try {
            return mapper.selectDailyPrice();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectPriceByCategory() {
        try {
            return mapper.selectPriceByCategory();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> selectGoodsByPriceTop() {
        try {
            return mapper.selectGoodsByPriceTop();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void Shouhuo(int id) {
        try {
            mapper.Shouhuo(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, Object>> selectFinsh(int id) {
        try {
            return mapper.selectFinsh(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> noFa(int id) {
        try {
            return mapper.noFa(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
