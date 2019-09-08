package cn.pn.client;

import cn.pn.fullback.RestLoginControllerFullBack;
import cn.pn.pojo.User;
import cn.pn.pojo.utils.Dto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Created by dzt66 on 2019/8/12.
 * feign客户端
 * 用fallback属性指定回退类
 */

@FeignClient(name = "pn-user-provider", fallback = RestLoginControllerFullBack.class)
public interface LoginClient {

    /**
     * 快捷注册（免密登陆）
     *
     * @param userAgent
     * @param user
     * @return
     */
    @RequestMapping(value = "/quickLogin", method = RequestMethod.POST)
    @ResponseBody
    Dto quickLogin(@RequestParam("userAgent") String userAgent, @RequestBody User user);

    /**
     * 登录
     *
     * @param userAgent
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    Dto login(@RequestParam("userAgent") String userAgent, @RequestBody User user);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    boolean sign(@RequestParam("userAgent") String userAgent, @RequestBody User user);

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    Dto loginout(@RequestParam("userAgent") String userAgent, @RequestParam("token") String token);
}
