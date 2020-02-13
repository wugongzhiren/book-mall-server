package com.mall.server.controller;

import com.mall.server.model.Collects;
import com.mall.server.model.Goods;
import com.mall.server.model.Response;
import com.mall.server.repository.CollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/4.
 */
@RestController
public class Collectmanage {
    @Autowired
    private CollectRepository collectRepository;
    /*@Autowired
    private GoodsRepository goodsRepository;*/

    /**
     *
     *
     * @return
     */
    @RequestMapping(value = "/api/user/saveCollect", method = RequestMethod.POST)
    public Response saveSuggest(@RequestParam String userid, @RequestParam String goodid, @RequestParam String flag) {
        Response response = new Response();
        System.out.println(flag);
        if(flag.equals("0")) {
            //System.out.println("flag");
            Collects collects = new Collects();
            collects.setGoodid(goodid);
            collects.setUserid(userid);
           /* Goods goods = goodsRepository.findById(Long.parseLong(goodid));
            collects.setGoodName(goods.getGoodsname());
            collects.setImg(goods.getImgurl());
            collects.setUnitPrice(goods.getPrice());*/
            collectRepository.save(collects);
        }else{
            //取消收藏
            Collects collects=collectRepository.findByUseridAndGoodid(userid,goodid);
            collectRepository.delete(collects);
        }
        response.setCode(200);
        response.setMsg("");
        return response;
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/user/getCollect", method = RequestMethod.GET)
    public Response getCollect(@RequestParam String userid) {

        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(collectRepository.findByUserid(userid));

        return response;
    }

    @RequestMapping(value = "/api/user/getgoodCollect", method = RequestMethod.GET)
    public Response getgoodCollect(@RequestParam String userid,@RequestParam String goodid) {

        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(collectRepository.findByUseridAndGoodid(userid,goodid));

        return response;
    }
}
