package com.douby.demo.controller;

import com.alibaba.fastjson.support.spring.messaging.MappingFastJsonMessageConverter;
import com.douby.demo.dto.ResultDto;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodType;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *                _ooOoo_
 *                o8888888o
 *                88" . "88
 *                (| -_- |)
 *                O\ = /O
 *              ____/`---'\____
 *            .   ' \\| |// `.
 *              / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *              | | \\\ - /// | |
 *              | \_| ''\---/'' | |
 *            \ .-\__ `-` ___/-. /
 *            ___`. .' /--.--\ `. . __
 *          ."" '< `.___\_<|>_/___.' >'"".
 *         | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *          \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                `=---='
 * .............................................
 *      佛祖镇楼                  BUG辟易
 * 佛曰:
 *       写字楼里写字间，写字间里程序员；
 *       程序人员写程序，又拿程序换酒钱。
 *       酒醒只在网上坐，酒醉还来网下眠；
 *       酒醉酒醒日复日，网上网下年复年。
 *       但愿老死电脑间，不愿鞠躬老板前；
 *       奔驰宝马贵者趣，公交自行程序员。
 *       别人笑我忒疯癫，我笑自己命太贱；
 *       不见满街漂亮妹，哪个归得程序员？
 *
 * @Author: cpzh
 * @Date: 2018/6/27 11:42
 * TODO:
 */
@Controller
public class AppController {

    @RequestMapping(value = "getUser")
    @ResponseBody
    public Object getUser(String callback){
        ResultDto resultDto = new ResultDto("张三", "24", "男");
        if (callback != null){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(resultDto);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return resultDto;
    }

    @RequestMapping(value = "page")
    public Object page(){
        return "index";
    }

    @RequestMapping(value = "getUserCors")
    @ResponseBody
    public Object getUserCors(HttpServletRequest request, HttpServletResponse response){
        System.out.println("111111111111111111111111");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date date1 = new Date();
        System.out.println(simpleDateFormat.format(date1));

        System.out.println("===================================");
        //允许指定的端口 域 协议 通过跨域请求
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");

        //允许携带cookies的跨域请求
        response.setHeader("Access-Control-Allow-Credentials", "true");

        //非简单请求的预检
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

        //预检缓存
        response.setHeader("Access-Control-Max-Age", "3600");

        //浏览器可以获取的更多的头部
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");

        ResultDto resultDto = new ResultDto("张三", "24", "男");
        return resultDto;
    }
}
