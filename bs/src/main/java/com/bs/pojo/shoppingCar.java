package com.bs.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class shoppingCar {
    private int id;
    private int userId;
    private int goodId;
    private int goodInfoId;
    private int amount;
}
