package com.bs.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class order {
    private String id;
    private int userId;
    private int goodId;
    private int goodsInfoId;
    private int number;
    private int price;
    private int state;
    private int addressId;
    private String createTime;
    private int isDel;
    private String payId;
    private String logisticsId;

}
