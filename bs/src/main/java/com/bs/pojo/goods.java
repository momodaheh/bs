package com.bs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class goods {
    private int id;
    private String goodsName;
    private int brandId;
    private int categoryId;
    private String imgShow1;
    private String imgShow2;
    private String imgShow3;
    private String imgDetail;
    private int isDel;

}
