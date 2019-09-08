package cn.pn.controller;

import cn.pn.client.LoginClient;
import cn.pn.pojo.User;
import cn.pn.pojo.utils.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dzt66 on 2019/8/12.
 */
@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private LoginClient loginClient;

    /**
     * 快捷注册（免密登陆）
     *
     * @param request
     * @param userPhone
     * @return
     */
    @RequestMapping(value = "/y/quickLogin", method = RequestMethod.POST)
    public @ResponseBody
    Dto quickLogin(HttpServletRequest request, @RequestParam("userPhone") String userPhone) {
        System.out.println("***************" + userPhone + "***************");
        User user = new User();
        user.setUserPhone(userPhone);
        user.setUserPassword("admin");
        return loginClient.quickLogin(request.getHeader("User-Agent"), user);
    }

    /**
     * 登录
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/y/login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Dto login(HttpServletRequest request, @RequestBody User user) {
        System.out.println(user.getUserPhone() + "***************" + user.getUserPassword());
        return loginClient.login(request.getHeader("User-Agent"), user);
    }

    /**
     * 注册
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/y/sign", method = RequestMethod.POST, produces = "application/json")
    public boolean sign(HttpServletRequest request, @RequestBody User user) {
        System.out.println(user.getUserPhone() + "***************" + user.getUserPassword());
        return loginClient.sign(request.getHeader("User-Agent"), user);
    }


    /**
     * 退出登陆
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/n/logout", method = RequestMethod.GET, produces = "application/json", headers = "token")
    public @ResponseBody
    Dto logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userAgent = request.getHeader("User-Agent");
        return loginClient.loginout(userAgent, token);
    }
}
