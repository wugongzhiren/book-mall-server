package com.mall.server.controller;

import com.mall.server.model.Ads;
import com.mall.server.model.Response;
import com.mall.server.model.User;
import com.mall.server.repository.AdsRepository;
import com.mall.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
@RestController
public class Adsmanage {

    @Autowired
    private AdsRepository adsRepository;

    /**
     * 注册
     *
     * @return
     */
    @RequestMapping(value = "/api/ads/getAds", method = RequestMethod.GET)
    public Response getAds() {
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(adsRepository.findAll());
        return response;
    }

    /**
     * 公告
     *
     * @return
     */
    @RequestMapping(value = "/api/ads/add", method = RequestMethod.POST)
    public Response add(@RequestParam String id, @RequestParam String tips, @RequestParam String img) {

        System.out.println(id);
        Ads ads ;
        if (id .equals("")) {
            ads = new Ads();
            ads.setTips(tips);
            ads.setImg(img);
            adsRepository.save(ads);

        } else {
            ads = adsRepository.findById(Long.parseLong(id));
            ads.setImg(img);
            ads.setTips(tips);
            adsRepository.save(ads);
        }
        Response response = new Response();
        response.setCode(200);
        response.setMsg("");
        response.setT(ads);

        return response;
    }

    @RequestMapping(value = "/api/ads/getIP", method = RequestMethod.GET)
    public Response getIP() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        String hostname = addr.getHostName();
        System.out.println("Local host name: "+hostname);

        Response response = new Response();
        response.setCode(200);
        response.setMsg(getIpAddress());
        response.setT(adsRepository.findAll());
        return response;
    }
    @RequestMapping(value = "/api/sys/backupDb", method = RequestMethod.GET)
    public Response backupDb() throws IOException, InterruptedException {
        String pathSql = "C:\\bookmall\\data\\";
        File filePath = new File(pathSql);
        //创建备份sql文件
        if (!filePath.exists()){
            filePath.mkdirs();
        }
        File dbFile=new File(filePath,"book.sql");
        if(!dbFile.exists()){
            dbFile.createNewFile();
        }
        //mysqldump -hlocalhost -uroot -p123456 db > /home/back.sql
        StringBuffer sb = new StringBuffer();
        sb.append("mysqldump");
        sb.append(" -u"+"root");
        sb.append(" -p"+"password");
        sb.append(" "+"bookmall"+" > ");
        sb.append(filePath);
        System.out.println("cmd命令为："+sb.toString());
       // Runtime runtime = Runtime.getRuntime();
        System.out.println("开始备份：bookmall");
        Process process = Runtime.getRuntime().exec(new String[] { "cmd", "/c", "mysqldump -uroot  -ppassword bookmall > D:\\backup.sql" }); // windows
        System.out.println( process.waitFor());
       // Process process = runtime.exec("cmd /c mysqldump -uroot  -ppassword bookmall > F:backup.sql");
        System.out.println("备份成功!");
        Response response = new Response();
        response.setCode(200);
        response.setMsg("备份成功，备份数据存储在D:\\backup.sql");
        response.setT(null);
        return response;
    }

    @RequestMapping(value = "/api/sys/resetDb", method = RequestMethod.GET)
    public Response resetDb() throws IOException, InterruptedException {

        // Runtime runtime = Runtime.getRuntime();
        System.out.println("开始备份：bookmall");
        Process process = Runtime.getRuntime().exec(new String[] { "cmd", "/c", "mysql -uroot -ppassword bookmall < D:\\backup.sql" }); // windows
        System.out.println( process.waitFor());
        // Process process = runtime.exec("cmd /c mysqldump -uroot  -ppassword bookmall > F:backup.sql");
        System.out.println("恢复成功!");
        Response response = new Response();
        response.setCode(200);
        response.setMsg("恢复成功");
        response.setT(null);
        return response;
    }

    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            if(ip.getHostAddress().contains("192.168")) {
                                return ip.getHostAddress();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IP地址获取失败" + e.toString());
        }
        return "";
    }
}
