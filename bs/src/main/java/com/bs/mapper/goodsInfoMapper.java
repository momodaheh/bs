package com.bs.mapper;



import com.bs.pojo.goodsInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface goodsInfoMapper extends BaseMapper<goodsInfo>{

    @Select("select * from good_info where isDel=0 and goodsId=#{id}")
    List<goodsInfo> selectGoodsInfoById(int id)throws SQLException;

    @Update("update good_info set isDel=1 where id=#{id}")
    void deleteGoodsInfo(int id)throws SQLException;

    @Update("update good_info set attribute=#{attribute},price=#{price},number=#{number} where id=#{id}")
    void updateGoodsInfo(goodsInfo goodsInfo)throws SQLException;

    @Insert("insert into good_info (goodsId,attribute,price,number,isDel) values(#{goodsId},#{attribute},#{price},#{number},0)")
    void addGoodsInfo(goodsInfo goodsInfo)throws SQLException;
}
