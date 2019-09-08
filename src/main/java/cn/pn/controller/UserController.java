package cn.pn.controller;

<<<<<<< HEAD
import cn.pn.client.UserClient;
import cn.pn.pojo.utils.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
=======
import cn.pn.pojo.utils.Dto;
import cn.pn.pojo.utils.ToKenVO;
import cn.pn.service.TokenService;
import cn.pn.service.UserService;
import cn.pn.tools.DtoUtil;
import cn.pn.tools.ErrorCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
>>>>>>> 317391993af2e817f10068eddf4f1251364f51d8
import java.math.BigDecimal;
import java.util.Calendar;

/**
<<<<<<< HEAD
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


=======
 * Created by dzt66 on 2019/8/14.
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;

    @RequestMapping(value = "/reloadToken", method = RequestMethod.POST)
    public @ResponseBody
    Dto reloadToken(@RequestParam("userAgent") String userAgent, @RequestParam("token") String token) {
        try {
            token = tokenService.reloadToken(userAgent, token);
            ToKenVO vo = new ToKenVO(token, Calendar.getInstance().getTimeInMillis() + 2 * 60 * 60 * 1000, Calendar.getInstance().getTimeInMillis());
            return DtoUtil.returnDataSuccess(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_UNKNOWN);
        }
    }

    @RequestMapping(value = "/getBalance", method = RequestMethod.POST)
    public BigDecimal getUserBalance(@RequestParam("id") int id) {
        return userService.balance(id);
    }
>>>>>>> 317391993af2e817f10068eddf4f1251364f51d8
}
