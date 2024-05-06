package com.bs.mapper;

import com.bs.pojo.address;
import com.bs.pojo.goods;
import com.bs.pojo.order;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface orderMapper extends BaseMapper<order>{

    @Select("select * from orders where id=#{id}")
    order selectOrderbyId(String id) throws SQLException;

    @Select("select o.*,ui.*,a.*,g.*,gi.* from orders o join user_infor ui on o.userId=ui.id\n" +
            "join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId\n" +
            "join address a on a.id=o.addressId\n" +
            "where o.isDel=0 order by o.createTime DESC")
    List<Map<String,Object>> selectOrders() throws SQLException;

    @Insert("insert into orders (id,userId,goodId,goodsInfoId,number,price,state,addressId,createTime,updateTime,isDel)values(#{id},#{userId},#{goodId},#{goodInfoId},#{amount},#{totalPrice},0,#{addressId},now(),now(),0)")
    void addOrder(Map<String,Object> obj) throws SQLException;

    @Update("update orders set payId=#{payId},state=1 ,updateTime=now() where id=#{id}")
    void orderPay(String id,String payId) throws SQLException;

    @Update("update orders set isDel=1,updateTime=now() where id=#{id} ")
    void deleteOrder(String id) throws SQLException;

    @Select("select o.*, g.*,gi.*,a.* from orders o join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId \n" +
            "join address a on a.id=o.addressId\n" +
            "where o.state=0 and o.isDel=0 and o.userId=#{id} order by o.createTime DESC")
    List<Map<String,Object>> selectNopay(int id) throws SQLException;

    @Select("select g.* ,gi.* from goods g JOIN good_info gi on gi.goodsId=g.id\n" +
            "where g.id=#{goodId} and gi.id=#{goodInfoId}")
    Map<String,Object> selectOrderInfo(Map<String,Object> obj)throws SQLException;

    @Select("select * from goods where id=#{id}")
    goods selectgood(int id)throws SQLException;

    @Select("select o.*,ui.*,a.*,g.*,gi.* from orders o join user_infor ui on o.userId=ui.id\n" +
            "join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId\n" +
            "join address a on a.id=o.addressId\n" +
            "where o.isDel=0 and o.state=1")
    List<Map<String,Object>> selectOrdersStateOne()throws SQLException;

    @Update("update orders set logisticsId=#{logisticsId} ,state=2 ,updateTime=now() where id=#{id}")
    void fahuo(String id,String logisticsId )throws SQLException;

    @Select("select o.*, g.*,gi.*,a.* from orders o join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId \n" +
            "join address a on a.id=o.addressId\n" +
            "where o.state=2 and o.isDel=0 and o.userId=#{id} order by o.createTime DESC")
    List<Map<String,Object>> selectNoReceipt(int id)throws SQLException;

    @Select("select o.*, g.*,gi.*,a.* from orders o join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId \n" +
            "join address a on a.id=o.addressId\n" +
            "where  o.isDel=0 and o.userId=#{id} order by o.state ASC")
    List<Map<String,Object>> selectAll(int id) throws SQLException;

    @Select("select o.*, g.*,gi.*,a.* from orders o join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId \n" +
            "join address a on a.id=o.addressId\n" +
            "where o.state=3 and o.isDel=0 and o.userId=#{id} order by o.createTime DESC")
    List<Map<String,Object>> selectFinsh(int id)throws SQLException;

    @Select("select o.*, g.*,gi.*,a.* from orders o join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId \n" +
            "join address a on a.id=o.addressId\n" +
            "where o.state=1 and o.isDel=0 and o.userId=#{id} order by o.createTime DESC")
    List<Map<String,Object>> noFa(int id)throws SQLException;

    @Select("SELECT \n" +
            "    d.date,\n" +
            "    COALESCE(SUM(o.price), 0) AS dailyPrice\n" +
            "FROM (\n" +
            "    SELECT CURDATE() - INTERVAL n DAY AS date\n" +
            "    FROM (\n" +
            "        SELECT a.N + b.N * 10 + 1 n\n" +
            "        FROM (SELECT 0 AS N UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) a\n" +
            "        CROSS JOIN (SELECT 0 AS N UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) b\n" +
            "        ORDER BY n\n" +
            "        LIMIT 30\n" +
            "    ) numbers\n" +
            ") d\n" +
            "LEFT JOIN orders o ON DATE(o.updateTime) = d.date AND o.state = 3\n" +
            "GROUP BY d.date;")
    List<Map<String,Object>> selectDailyPrice()throws SQLException;

    @Select("SELECT \n" +
            "    category.category_name,\n" +
            "    SUM(orders.price) AS totalPrice\n" +
            "FROM \n" +
            "    orders\n" +
            "JOIN \n" +
            "    goods ON goods.id = orders.goodId\n" +
            "JOIN \n" +
            "    category ON category.id = goods.categoryId\n" +
            "WHERE \n" +
            "    orders.state = 3 AND\n" +
            "    orders.updateTime >= CURDATE() - INTERVAL 1 MONTH\n" +
            "GROUP BY \n" +
            "    category.category_name;")
    List<Map<String,Object>> selectPriceByCategory()throws SQLException;

    @Select("SELECT \n" +
            "    goods.id,\n" +
            "    goods.goodsName,\n" +
            "\t\tgoods.imgShow1,\n" +
            "    SUM(orders.price) AS totalPrice\n" +
            "FROM \n" +
            "    orders\n" +
            "JOIN \n" +
            "    goods ON goods.id = orders.goodId\n" +
            "WHERE \n" +
            "    orders.state = 3 AND\n" +
            "    orders.updateTime >= CURDATE() - INTERVAL 30 DAY\n" +
            "GROUP BY \n" +
            "    goods.id, goods.goodsName\n" +
            "ORDER BY \n" +
            "    totalPrice DESC\n" +
            "LIMIT 3;")
    List<Map<String,Object>> selectGoodsByPriceTop()throws SQLException;

    @Update("update orders set state=3 , updateTime=now() where id=#{id}")
    void Shouhuo(String id)throws SQLException;

    @Select("select o.*,ui.*,a.*,g.*,gi.* from orders o join user_infor ui on o.userId=ui.id\n" +
            "join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId\n" +
            "join address a on a.id=o.addressId\n" +
            "where o.isDel=0 and o.id like CONCAT('%', #{id},'%') order by o.createTime DESC")
    List<Map<String,Object>> selectOrdresById(String id)throws SQLException;

    @Select("select o.*,ui.*,a.*,g.*,gi.* from orders o join user_infor ui on o.userId=ui.id\n" +
            "join goods g on g.id=o.goodId\n" +
            "join good_info gi on gi.id=o.goodsInfoId\n" +
            "join address a on a.id=o.addressId\n" +
            "where o.isDel=0 and o.state=1 and o.id like CONCAT('%', #{id},'%') order by o.createTime DESC")
    List<Map<String,Object>> selectOrdresStateOneById(String id)throws SQLException;
}
