package com.bs.service;

import com.bs.pojo.brand;

import java.util.List;

public interface brandService extends BaseService<brand> {
    List<brand> SelectBrands();
    void addBrand( String brandName);
    void deleteBrands(int brandId);

}
