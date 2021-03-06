package com.mall.server.repository;

import com.mall.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUseridAndPassword(@Param("userid") String userid,@Param("password") String password);
    User findByUserid(@Param("userid") String userid);
    /*@Query(value = "update goods u set u.goods_count=u.goods_count-?1 where u.zxing_code=?2 ",nativeQuery = true)
    void updateGoodsCount(@Param("count") int count,@Param("zxing_code") String zxing_code);*/
}
