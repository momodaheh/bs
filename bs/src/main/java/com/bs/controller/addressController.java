package com.bs.controller;


import com.bs.pojo.address;
import com.bs.service.addressService;
import com.bs.util.ResultCode;
import com.bs.util.ResultEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*" , maxAge=3600)
@RestController
@RequestMapping("address")
public class addressController {

    @Resource
    private addressService service;

    @RequestMapping("selectUserAddress")
    public ResultEntity<List<address>> selectUserAddress(@RequestBody String userId){
        int id =Integer.parseInt(userId);
        List<address> addressList =service.selectUserAddress(id);
        return ResultEntity.infor(ResultCode.SUCCESS,addressList);
    }

    @RequestMapping("addAddress")
    public ResultEntity addAddress(@RequestBody address address){

        service.addAddress(address);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("deleteAddress")
    public ResultEntity deleteAddress(@RequestBody String addressId){
        int id=Integer.parseInt(addressId);
        service.deleteAddress(id);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }

    @RequestMapping("updateAddress")
    public ResultEntity updateAddress(@RequestBody address address){
        service.updateAddress(address);
        return ResultEntity.infor(ResultCode.SUCCESS);
    }
}
