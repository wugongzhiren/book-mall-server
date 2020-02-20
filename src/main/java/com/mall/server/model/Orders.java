package com.mall.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 订单
 */
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//自增ID
    private String userid;
    private String goodid;
    private String orderName;
    private String orderNum;
    private String orderPrice;
    private String orderSumPrice;
    private String creteTime;
    private String userName;
    private String phone;
    private String address;
    private String img;
    //0表示购物车中，未结算。1表示已经付款，2表示已经发货，3表示交易完成
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderSumPrice() {
        return orderSumPrice;
    }

    public void setOrderSumPrice(String orderSumPrice) {
        this.orderSumPrice = orderSumPrice;
    }


    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }


    public String getCreteTime() {
        return creteTime;
    }

    public void setCreteTime(String creteTime) {
        this.creteTime = creteTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
