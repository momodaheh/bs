package com.bs.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userInfor {
    private int id;
    private String telephone;
    private int sex;
    private String nickName;
    private String email;
    private int isDel;
}
