package com.bs.serviceImpl;



import com.bs.mapper.BaseMapper;
import com.bs.mapper.brandMapper;
import com.bs.pojo.brand;
import com.bs.service.brandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
@Service
public class brandServiceImpl extends BaseServiceImpl<brand> implements brandService {
    @Autowired
    private brandMapper mapper;

    @Override
    public BaseMapper<brand> getBaseMapper(){
        return mapper;
    }

    @Override
    public List<brand> SelectBrands() {
        try {
            return mapper.selectBrands();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addBrand(String brandName) {
        try {
            mapper.addBrand(brandName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBrands(int brandId) {
        try {
            mapper.deleteBrand(brandId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
