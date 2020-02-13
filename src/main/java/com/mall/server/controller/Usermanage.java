package com.mall.server.controller;

import com.mall.server.model.Response;
import com.mall.server.model.User;
import com.mall.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
@RestController
public class Usermanage {
    @Autowired
    private UserRepository userRepository;
    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/user/signup", method = RequestMethod.POST)
    public Response signup(@RequestParam String userid,@RequestParam String username,@RequestParam String pwd,@RequestParam String address,@RequestParam String phone) {
        User user = userRepository.findByUserid(userid);
        Response response=new Response();
        if (user != null) {
            response.setCode(201);
            response.setMsg("");
            response.setT(user);
        } else {
            user=new User();
            user.setUserid(userid);
            user.setUsername(username);
            user.setPassword(pwd);
            user.setAddress(address);
            user.setPhone(phone);
            userRepository.save(user);
            response.setCode(200);
            response.setMsg("");
            response.setT(user);
        }
        return response;
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/api/user/login", method = RequestMethod.POST)
    public Response login(@RequestParam String userid,@RequestParam String pwd) {
        User user = userRepository.findByUseridAndPassword(userid,pwd);
        Response response=new Response();
        if (user != null) {
            response.setCode(200);
            response.setMsg("");
            response.setT(user);
        } else {
            response.setCode(200);
            response.setMsg("");
            response.setT(null);
        }
        return response;
    }

    /**
     * 更新个人信息
     *
     * @return
     */
    @RequestMapping(value = "/api/user/updateUserInfo", method = RequestMethod.POST)
    public Response updateUserInfo(@RequestParam String userid,@RequestParam String username,@RequestParam String address,@RequestParam String phone) {
        User user = userRepository.findByUserid(userid);
        Response response=new Response();
        if (user != null) {
            //user.setUserid(userid);
            user.setUsername(username);
            //user.setPassword(pwd);
            user.setAddress(address);
            user.setPhone(phone);
            userRepository.save(user);
            response.setCode(200);
            response.setMsg("");
            response.setT(user);
        }
        return response;
    }
    /**
     * 获取个人信息
     *
     * @return
     */
    @RequestMapping(value = "/api/user/getUserInfo", method = RequestMethod.GET)
    public Response getUserInfo(@RequestParam String userid) {
        User user = userRepository.findByUserid(userid);
        Response response=new Response();
        if (user != null) {
            response.setCode(200);
            response.setMsg("");
            response.setT(user);
        } else {
            response.setCode(200);
            response.setMsg("");
            response.setT(null);
        }
        return response;
    }

    /**
     * 获取个人信息
     *
     * @return
     */
    @RequestMapping(value = "/api/user/getUserAll", method = RequestMethod.GET)
    public Response getUserAll() {
        List<User> users = userRepository.findAll();
        Response response=new Response();
        if (users != null) {
            response.setCode(200);
            response.setMsg("");
            response.setT(users);
        } else {
            response.setCode(200);
            response.setMsg("");
            response.setT(null);
        }
        return response;
    }
    /**
     * 更新密码
     *
     * @return
     */
    @RequestMapping(value = "/api/user/updatePassword", method = RequestMethod.POST)
    public Response updatePassword(@RequestParam String userid,@RequestParam String pwd) {
        User user = userRepository.findByUserid(userid);
        Response response=new Response();
        if (user != null) {
            //user.setUserid(userid);
            user.setPassword(pwd);
            userRepository.save(user);
            response.setCode(200);
            response.setMsg("");
            response.setT(user);
        }
        return response;
    }
}
