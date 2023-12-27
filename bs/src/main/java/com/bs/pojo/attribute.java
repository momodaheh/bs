package com.bs.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class attribute {
    private int id;
    private int category_id;
    private String attribute_name;
}
