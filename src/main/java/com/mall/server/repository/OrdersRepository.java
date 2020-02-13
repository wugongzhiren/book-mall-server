package com.mall.server.repository;

import com.mall.server.model.Goods;
import com.mall.server.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    List<Orders> findByUseridAndStatus(@Param("userid") String userid, @Param("status") String status);
    List<Orders> findByUserid(@Param("userid") String userid);
    List<Orders> findByStatus(@Param("status") String status);
    Orders findById(@Param("id") long id);
    Orders findByGoodidAndStatusAndUserid(@Param("goodid") String goodid, @Param("status") String status,@Param("userid") String userid);
    //Goods findById(@Param("id") long id);*/
    /*@Query(value = "update goods u set u.goods_count=u.goods_count-?1 where u.zxing_code=?2 ",nativeQuery = true)
    void updateGoodsCount(@Param("count") int count,@Param("zxing_code") String zxing_code);*/
}
