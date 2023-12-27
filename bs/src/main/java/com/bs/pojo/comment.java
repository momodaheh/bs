package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class comment {
    private int id;
    private int usreId;
    private int goodId;
    private String content;
    private String createTime;
    private int isDel;
    private int commentType;
}
