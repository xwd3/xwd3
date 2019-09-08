package cn.pn.controller;

import cn.pn.client.UserClient;
import cn.pn.pojo.utils.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by dzt66 on 2019/8/12.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserClient userClient;

    /**
     * Token置换
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/n/reloadToken", method = RequestMethod.POST, produces = "application/json", headers = "token")
    public @ResponseBody
    Dto reloadToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userAgent = request.getHeader("User-Agent");
        return userClient.reloadToken(userAgent, token);
    }

    /**
     * 查询余额
     * @param id
     * @return
     */
    @RequestMapping(value = "/n/getBalance", method = RequestMethod.POST)
    public BigDecimal getUserBalance(@RequestParam("id") int id) {
        return userClient.getUserBalance(id);
    }


}
