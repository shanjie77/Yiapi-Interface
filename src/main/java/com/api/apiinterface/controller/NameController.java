package com.api.apiinterface.controller;


import cn.hutool.json.JSONUtil;
import com.api.apiclientsdk.model.User;
import icu.qimuu.qiapisdk.exception.ApiException;
import icu.qimuu.qiapisdk.model.params.RandomWallpaperParams;
import icu.qimuu.qiapisdk.model.response.RandomWallpaperResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import static com.api.apiinterface.utils.RequestUtils.buildUrl;
import static com.api.apiinterface.utils.RequestUtils.get;

/**
 * 名称api
 * @author api
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getnamebyGET(String name)
    {
        return "GET 你的名字为"+name;
    }

//    @PostMapping ("/post")
//    public String getnamebyPOST(@RequestParam String name)
//    {
//        return "POST 你的名字为"+name;
//    }

    @PostMapping ("/user")
    public String getnamebyPOST(@RequestBody User user, HttpServletRequest request)
    {
//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader("nonce");
//        String timestamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//        String body = request.getHeader("body");
//        if(!accessKey.equals("shanyi"))
//        {
//            throw new RuntimeException("无权限");
//        }
//        if(Long.parseLong(nonce)>10000)
//        {
//            throw new RuntimeException("无权限");
//        }
//        String serverSign = SignUtils.genSign(body, "qweasd");
         return "POST 你的名字为"+user.getUsername();
    }
    // TODO 接口校验请求头（是否包含 API 网关染色的请求头）

    /**
     * 2. 随机毒鸡汤
     *
     * @return
     */
    @GetMapping("/poisonousChickenSoup")
    public String getPoisonousChickenSoup() {
        return get("https://api.btstu.cn/yan/api.php?charset=utf-8&encode=json");// 真实的第三方接口地址
    }

    /**
     * 3. 随机壁纸
     *
     * @param randomWallpaperParams
     * @return
     * @throws ApiException
     */
    @GetMapping("/randomWallpaper")
    public RandomWallpaperResponse randomWallpaper(RandomWallpaperParams randomWallpaperParams) throws ApiException {
//        String baseUrl = "https://api.btstu.cn/sjbz/api.php";
        String baseUrl = "https://api.vvhan.com/api/bing?type=json";
        String url = buildUrl(baseUrl, randomWallpaperParams);
        if (StringUtils.isAllBlank(randomWallpaperParams.getLx(), randomWallpaperParams.getMethod())) {
            url = url + "?format=json";
        } else {
            url = url + "&format=json";
        }
        return JSONUtil.toBean(get(baseUrl), RandomWallpaperResponse.class);
    }

    /**
     * 4. 随机土味情话
     *
     * @return
     */
    @GetMapping("/loveTalk")
    public String randomLoveTalk() {
        return get("https://api.vvhan.com/api/text/love");
    }

    /**
     * 5. 每日一句励志英语
     *
     * @return
     */
    @GetMapping("/en")
    public String dailyEnglish() {
        return get("https://api.vvhan.com/api/dailyEnglish");
    }

    /**
     * 6. 每天60s读懂世界
     *
     * @return
     */
    @GetMapping("/60s")
    public String randomJoke() {
        return get("https://api.vvhan.com/api/60s");
    }



}
