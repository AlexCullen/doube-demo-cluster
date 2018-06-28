#测试
##跨域请求的测试
- `demojsonprequestserver`：服务端
- `demojsonprequestclient`：客户端

###jsop前台实现跨域
- 当使用`jsonp`来请求的时候，后台必须实现`callback`的包装
```java
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
}
```

###CORS后端实现跨域
- CORS（Cross-Origin Resource Sharing 跨源资源共享），当一个请求url的协议、域名、端口三者之间任意一与当前页面地址不同即为跨域。

####使用CORS的过滤器的jar包来实现
- 在`web.xml`中添加以下配置
```xml
    <!-- 跨域请求 -->
    <filter>
        <filter-name>corsFilter</filter-name>
        <filter-class>com.thetransactioncompany.cors.CORSFilter</filter-class>
        <init-param>
            <param-name>cors.allowOrigin</param-name>
            <param-value>*</param-value>
        </init-param>
        <init-param>
            <param-name>cors.supportedMethods</param-name>
            <param-value>GET, POST, HEAD, PUT, DELETE</param-value>
        </init-param>
        <init-param>
            <param-name>cors.supportedHeaders</param-name>
            <param-value>Accept, Origin, X-Requested-With, Content-Type, Last-Modified</param-value>
        </init-param>
        <init-param>
            <param-name>cors.exposedHeaders</param-name>
            <param-value>Set-Cookie</param-value>
        </init-param>
        <init-param>
            <param-name>cors.supportsCredentials</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>corsFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

- 添加jar包依赖
```xml
    <dependency>
      <groupId>com.thetransactioncompany</groupId>
      <artifactId>cors-filter</artifactId>
      <version>2.6</version>
    </dependency>
```

####自己实现CORS跨域
- (跨域关键设置)允许指定的端口 域 协议 通过跨域请求 `response.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");`
- 允许携带cookies的跨域请求`response.setHeader("Access-Control-Allow-Credentials", "true");`
- 非简单请求的预检：`response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");`
- 预检缓存：`response.setHeader("Access-Control-Max-Age", "3600");`
- 浏览器可以获取的更多的头部：`response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");`
```java
@Controller
public class AppController {
        @RequestMapping(value = "getUserCors")
        @ResponseBody
        public Object getUserCors(HttpServletRequest request, HttpServletResponse response){
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
```