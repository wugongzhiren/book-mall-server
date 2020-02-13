package com.mall.server.controller;

import com.mall.server.model.Ads;
import com.mall.server.model.Response;
import com.mall.server.model.Suggest;
import com.mall.server.repository.AdsRepository;
import com.mall.server.repository.SuggestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/4.
 */
@RestController
public class Suggestmanage {
    @Autowired
    private SuggestRepository suggestRepository;

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/user/saveSuggest", method = RequestMethod.POST)
    public Response saveSuggest(@RequestParam String userid, @RequestParam String content) {
        Suggest suggest=new Suggest();
        suggest.setContent(content);
        suggest.setUserid(userid);
        suggestRepository.save(suggest);
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");

        return response;
    }

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/user/getSuggest", method = RequestMethod.GET)
    public Response getSuggest() {


        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(suggestRepository.findAll());

        return response;
    }

}
