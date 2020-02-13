package com.mall.server.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mall.server.model.Goods;
import com.mall.server.model.Orders;
import com.mall.server.model.Response;
import com.mall.server.model.User;
import com.mall.server.repository.OrdersRepository;
import com.mall.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
@RestController
public class Ordersmanage {
    @Autowired
    private OrdersRepository ordersRepository;
   /* @Autowired
    private GoodsRepository goodsRepository;*/
    @Autowired
    private UserRepository userRepository;
    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/goods/addOrder", method = RequestMethod.POST)
    public Response add(@RequestParam String goodsid,@RequestParam String userid, @RequestParam String orderName, @RequestParam String orderNum, @RequestParam String orderPrice
            , @RequestParam String salePrice,@RequestParam String status) {
        //如果有相同的商品，则增加数量

        Orders order =ordersRepository.findByGoodidAndStatusAndUserid(goodsid,"0",userid);
        if(order!=null){
           /* Goods goods=goodsRepository.findById(Long.parseLong(goodsid));
            //order.setUserid(userid);
            //order.setOrderName(orderName);
            //order.setGoodid(goodsid);
            order.setImg(goods.getImgurl());
            order.setOrderNum((Integer.parseInt(orderNum)+Integer.parseInt(order.getOrderNum()))+"");*/
            //order.setOrderPrice(orderPrice);
            //order.setSalePrice(salePrice);
            order.setOrderSumPrice((Integer.parseInt(orderNum)+Integer.parseInt(order.getOrderNum())*Integer.parseInt(orderPrice)-Integer.parseInt(salePrice))+"");
            order.setCreteTime(convertTimestamp2Date(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
            //order.setStatus(status);
            ordersRepository.save(order);
        }else {
            Orders order1 = new Orders();
           /* Goods goods=goodsRepository.findById(Long.parseLong(goodsid));
            //order.setUserid(userid);
            //order.setOrderName(orderName);
            //order.setGoodid(goodsid);
            order1.setImg(goods.getImgurl());
            order1.setUserid(userid);*/
            order1.setOrderName(orderName);
            order1.setGoodid(goodsid);
            order1.setOrderNum(orderNum);
            order1.setOrderPrice(orderPrice);
            order1.setSalePrice(salePrice);
            order1.setOrderSumPrice((Integer.parseInt(orderNum) * Integer.parseInt(orderPrice) - Integer.parseInt(salePrice)) + "");
            order1.setCreteTime(convertTimestamp2Date(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
            order1.setStatus(status);
            ordersRepository.save(order1);
        }
        //减去库存
     /*  Goods goods= goodsRepository.findById(Long.parseLong(goodsid));
       if(goods!=null){
           goods.setStock((Integer.parseInt(goods.getStock())-Integer.parseInt(orderNum))+"");
           goodsRepository.save(goods);
       }*/
        Response response = new Response();
        response.setCode(200);
        response.setMsg("保存成功");
        response.setT(order);
        return response;
    }

    @RequestMapping(value = "/api/goods/addOrderList", method = RequestMethod.POST)
    public Response add(@RequestParam String orderList,@RequestParam String userid,@RequestParam String salePrice) {
        //如果有相同的商品，则增加数量

        System.out.println("orderList:"+orderList);
        Gson gson=new Gson();
        List<Orders> orders= gson.fromJson(orderList, new TypeToken<List<Orders>>() {}.getType());
        for(int i=0;i<orders.size();i++){
            Orders orders1=ordersRepository.findByGoodidAndStatusAndUserid(orders.get(i).getGoodid(),"0",userid);
            if(i==0){
                orders1.setOrderSumPrice((Integer.parseInt(orders.get(i).getOrderSumPrice())-Integer.parseInt(salePrice))+"");

            }
            orders1.setSalePrice(salePrice);
            orders1.setStatus("1");
            ordersRepository.save(orders1);
        }


        Response response = new Response();
        response.setCode(200);
        response.setMsg("保存成功");
        response.setT(null);
        return response;
    }
    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/user/getOrderByState", method = RequestMethod.GET)
    public Response getOrderByState(@RequestParam String userid,@RequestParam String status) {
        List<Orders> orders=ordersRepository.findByUseridAndStatus(userid,status);
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(orders);
        return response;
    }
    @RequestMapping(value = "/api/user/getAllOrderByState", method = RequestMethod.GET)
    public Response getAllOrderByState(@RequestParam String status) {
        List<Orders> orders=ordersRepository.findByStatus(status);
        for(int i=0;i<orders.size();i++){
            User user=userRepository.findByUserid(orders.get(i).getUserid());
            orders.get(i).setUserName(user.getUsername());
            orders.get(i).setAddress(user.getAddress());
            orders.get(i).setPhone(user.getPhone());
        }
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(orders);
        return response;
    }

    @RequestMapping(value = "/api/user/getOrders", method = RequestMethod.GET)
    public Response getOrders(@RequestParam String userid) {
        List<Orders> orders=ordersRepository.findByUserid(userid);
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(orders);
        return response;
    }
    @RequestMapping(value = "/api/user/getAllOrders", method = RequestMethod.GET)
    public Response getAllOrders() {
        List<Orders> orders=ordersRepository.findAll();
        for(int i=0;i<orders.size();i++){
            User user=userRepository.findByUserid(orders.get(i).getUserid());
            orders.get(i).setUserName(user.getUsername());
            orders.get(i).setAddress(user.getAddress());
            orders.get(i).setPhone(user.getPhone());
        }
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(orders);
        return response;
    }
    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/user/deleteOrder", method = RequestMethod.GET)
    public Response deleteOrder(@RequestParam String id) {
        Orders order=ordersRepository.findById(Long.parseLong(id));
        if(order!=null){
            ordersRepository.delete(order);
        }
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(order);
        return response;
    }
    @RequestMapping(value = "/api/user/confirmReceive", method = RequestMethod.GET)
    public Response confirmReceive(@RequestParam String id) {
        Orders order=ordersRepository.findById(Long.parseLong(id));
        if(order!=null){
            order.setStatus("3");
            ordersRepository.save(order);
        }
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(order);
        return response;
    }

    @RequestMapping(value = "/api/user/sendGoods", method = RequestMethod.GET)
    public Response sendGoods(@RequestParam String id) {
        Orders order=ordersRepository.findById(Long.parseLong(id));
        if(order!=null){
            order.setStatus("2");
            ordersRepository.save(order);
        }
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(order);
        return response;
    }
    public static String convertTimestamp2Date(Long timestamp, String pattern) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(new Date(timestamp));
    }

}
