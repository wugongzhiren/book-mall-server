package com.mall.server.repository;

import com.mall.server.model.Book;
import com.mall.server.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByName(@Param("name") String name);
    Book findById(@Param("id") long id);
    //Goods findById(@Param("id") long id);
   /* Goods findById(@Param("id") long id);
    List<Goods> findByType(@Param("type") String type);
    List<Goods> findByGoodsnameLike(@Param("goodsname") String goodsname);*/
    /*@Query(value = "update goods u set u.goods_count=u.goods_count-?1 where u.zxing_code=?2 ",nativeQuery = true)
    void updateGoodsCount(@Param("count") int count,@Param("zxing_code") String zxing_code);*/
}
