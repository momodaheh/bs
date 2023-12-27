package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class goodsInfo {
    private int id;
    private int goodsId;
    private String attribute;
    private int price;
    private int number;
}
