package com.mall.server.controller;

import com.mall.server.model.Book;
import com.mall.server.model.Goods;
import com.mall.server.model.Response;
import com.mall.server.repository.BookRepository;
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
public class Bookmanage {
    @Autowired
    private BookRepository bookRepository;

    /**
     * 添加
     *
     * @return
     */
    @RequestMapping(value = "/api/goods/add", method = RequestMethod.POST)
    public Response add(@RequestParam String id, @RequestParam String flag, @RequestParam String name, @RequestParam String type, @RequestParam String imgUrl, @RequestParam String description
            , @RequestParam String stock, @RequestParam String unitPrice, @RequestParam String author, @RequestParam String publish) {
        System.out.println("description:" + description);
        Response response = new Response();
        if (flag.equals("A")) {
            Book book = bookRepository.findByName(name);
            if (book != null) {
                response.setCode(201);
                response.setMsg("商品已经存在");
                response.setT(book);
            } else {
                book = new Book();
                book.setName(name);
                book.setType(type);
                book.setDescription(description);
                book.setImgurl(imgUrl);
                book.setStock(stock);
                book.setUnitPrice(unitPrice);
                book.setAuthor(author);
                book.setPublish(publish);
                bookRepository.save(book);
                response.setCode(200);
                response.setMsg("");
                response.setT(book);
            }
        } else {
            Book book1 = bookRepository.findById(Long.parseLong(id));
            if (book1 != null) {
                book1.setName(name);
                book1.setType(type);
                book1.setDescription(description);
                book1.setImgurl(imgUrl);
                book1.setStock(stock);
                book1.setUnitPrice(unitPrice);
                book1.setAuthor(author);
                book1.setPublish(publish);
                bookRepository.save(book1);
                response.setCode(200);
                response.setMsg("");
                response.setT(book1);
            }
        }
        return response;
    }

    @RequestMapping(value = "/api/goods/getAll", method = RequestMethod.GET)
    public Response getAll() {
        List<Book> goods = bookRepository.findAll();
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(goods);
        return response;
    }

    @RequestMapping(value = "/api/goods/getOne", method = RequestMethod.GET)
    public Response getOne(@RequestParam String id) {
        Book book = bookRepository.findById(Long.parseLong(id));
        Response response = new Response();
        if (book != null) {
            response.setCode(200);
            response.setMsg("");
            response.setT(book);
        }
        return response;
    }

    @RequestMapping(value = "/api/goods/getByType", method = RequestMethod.GET)
    public Response getGoods(@RequestParam String type) {
        List<Goods> goods;
      /*  if(type.equals("0")){
            goods=goodsRepository.findAll();
        }else{
            goods = goodsRepository.findByType(type);
        }
        Response response = new Response();
        if(goods!=null){
            response.setCode(200);
            response.setMsg("");
            response.setT(goods);
        }*/
        return null;
    }

    @RequestMapping(value = "/api/goods/getByKeyWord", method = RequestMethod.GET)
    public Response getGoodsByKey(@RequestParam String keyword) {
        List<Book> books;
        List<Book> books1;
        List<Book> books2;
        System.out.println(keyword);
        books = bookRepository.findByNameLike("%" + keyword + "%");
        books1 = bookRepository.findByAuthorLike("%" + keyword + "%");
        if (keyword.equals("国学")) {
            keyword = "1";
        }
        if (keyword.equals("教辅")) {
            keyword = "2";
        }
        if (keyword.equals("专业类")) {
            keyword = "3";
        }
        if (keyword.equals("小说")) {
            keyword = "4";
        }
        if (keyword.equals("计算机")) {
            keyword = "5";
        }
        if (keyword.equals("漫画")) {
            keyword = "6";
        }
        books2 = bookRepository.findByType(keyword);
        books.addAll(books1);
        books.addAll(books2);
        Response response = new Response();

        response.setCode(200);
        response.setMsg("");
        response.setT(books);

        return response;
    }

    @RequestMapping(value = "/api/goods/delete", method = RequestMethod.DELETE)
    public Response getAll(@RequestParam String id) {
        Book book = bookRepository.findById(Long.parseLong(id));
        Response response = new Response();
        if (book != null) {
            bookRepository.delete(book);
            response.setCode(200);
            response.setMsg("");
            response.setT(book);
        } else {
            response.setCode(201);
            response.setMsg("商品不存在");
            response.setT(null);
        }
        return response;
    }


}
