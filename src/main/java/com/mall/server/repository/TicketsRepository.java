package com.mall.server.repository;

import com.mall.server.model.Orders;
import com.mall.server.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
public interface TicketsRepository extends JpaRepository<Ticket,Long> {

    Ticket findById(@Param("id") long id);
    Ticket findByMoney(@Param("money") String money);
    Ticket findByUseridAndMoney(@Param("userid") String userid,@Param("money") String money);
    List<Ticket> findByUserid(@Param("userid") String userid);
    /*Goods findByGoodsname(@Param("goodsname") String goodsname);
    Goods findById(@Param("id") long id);*/
    /*@Query(value = "update goods u set u.goods_count=u.goods_count-?1 where u.zxing_code=?2 ",nativeQuery = true)
    void updateGoodsCount(@Param("count") int count,@Param("zxing_code") String zxing_code);*/
}
