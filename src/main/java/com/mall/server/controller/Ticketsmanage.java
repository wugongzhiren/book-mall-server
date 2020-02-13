package com.mall.server.controller;

import com.mall.server.model.Orders;
import com.mall.server.model.Response;
import com.mall.server.model.Ticket;
import com.mall.server.repository.OrdersRepository;
import com.mall.server.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
@RestController
public class Ticketsmanage {
    @Autowired
    private TicketsRepository ticketsRepository;

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/goods/addTicket", method = RequestMethod.POST)
    public Response add(@RequestParam String userid, @RequestParam String money) {
        Ticket ticket=ticketsRepository.findByUseridAndMoney(userid,money);
        if(ticket==null) {
            ticket = new Ticket();

            ticket.setMoney(money);
            ticket.setUserid(userid);
            ticket.setStatus("1");
            ticket.setCounts("1");
            ticketsRepository.save(ticket);

        }else{
            ticket.setCounts((Integer.parseInt(ticket.getCounts())+1)+"");
            ticketsRepository.save(ticket);
        }
        Response response = new Response();
        response.setCode(200);
        return response;
    }

    @RequestMapping(value = "/api/goods/getTickets", method = RequestMethod.GET)
    public Response add(@RequestParam String userid) {
        List<Ticket> tickets =ticketsRepository.findByUserid(userid);
        Response response = new Response();
        response.setT(tickets);
        response.setCode(200);
        return response;
    }
    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/user/getAllTicket", method = RequestMethod.GET)
    public Response getAllTicket() {
        List<Ticket> tickets = ticketsRepository.findAll();
        Response response = new Response();
        response.setCode(200);
        response.setT(tickets);
        return response;
    }
    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/user/deleteTicket", method = RequestMethod.GET)
    public Response deleteTicket(@RequestParam String id) {

        Ticket ticket = ticketsRepository.findById(Long.parseLong(id));
        if(ticket!=null){
            if(ticket.getCounts().equals("1")) {
                ticketsRepository.delete(ticket);
            }else{
                ticket.setCounts((Integer.parseInt(ticket.getCounts())-1)+"");
                ticketsRepository.save(ticket);
            }
        }
        Response response = new Response();
        response.setCode(200);
        response.setT(null);
        return response;
    }
    @RequestMapping(value = "/api/user/deleteTicketByMoney", method = RequestMethod.GET)
    public Response deleteTicketByMoney(@RequestParam String money) {

        Ticket ticket = ticketsRepository.findByMoney(money);
        if(ticket!=null){
            ticketsRepository.delete(ticket);
        }
        Response response = new Response();
        response.setCode(200);
        response.setT(null);
        return response;
    }
}
