package com.bs.serviceImpl;

import com.bs.mapper.BaseMapper;
import com.bs.mapper.categoryMapper;
import com.bs.pojo.category;
import com.bs.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
@Service
public class categoryServiceImpl extends BaseServiceImpl<category> implements categoryService {

    @Autowired
    private categoryMapper mapper;

    @Override
    public BaseMapper<category> getBaseMapper(){
        return mapper;
    }

    @Override
    public List<category> selectCategoryByBrandId(int brandId) {
        try {
            return mapper.selectCategoryByBrandId(brandId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deletebrand_Category(int brandId, int categoryId) {
        try {
            mapper.deletebrand_Category(brandId,categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<category> selectCategory() {
        try {
            return mapper.selectCategory();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addbradn_Category(int brandId, int categoryId) {
        try {
            mapper.addbrand_Category(brandId,categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addcategory(String name) {
        try {
            mapper.addcategory(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(int categoryId) {
        try {
            mapper.deletecategory(categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
