package com.bs.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class address {
    private int id;
    private int userId;
    private String contact;
    private String telephone;
    private String address;
}
