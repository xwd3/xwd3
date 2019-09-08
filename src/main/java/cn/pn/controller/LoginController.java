package cn.pn.controller;

<<<<<<< HEAD
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
=======
import cn.pn.pojo.User;
import cn.pn.pojo.utils.Dto;
import cn.pn.pojo.utils.ToKenVO;
import cn.pn.service.TokenService;
import cn.pn.service.UserService;
import cn.pn.tools.DtoUtil;
import cn.pn.tools.ErrorCode;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Calendar;

/**
 * Created by dzt66 on 2019/8/10.
 */
@RestController
public class LoginController {
    @Resource
    private UserService userService;
    @Resource
    private TokenService tokenService;


    /**
     * 快捷注册（免密登陆111）
     *
     * @param userAgent
     * @param user
     * @return
     */
    @RequestMapping(value = "/quickLogin", method = RequestMethod.POST)
    public @ResponseBody
    Dto quickLogin(@RequestParam("userAgent") String userAgent, @RequestBody User user) {
        boolean re = false;
        if (userService.duplicateChecking(user.getUserPhone()) == null) {
            re = userService.register(user.getUserPhone(), user.getUserPassword());
        }
        if (re == true) {
            System.out.println("****************注册成功！******************");
        } else {
            System.out.println("****************注册失败！******************");
            return DtoUtil.returnFail("该用户已存在,注册失败！", ErrorCode.AUTH_USER_ALREADY_EXISTS);
        }
        return common(userAgent, user);
    }


    /**
     * 登录
     *
     * @param userAgent
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Dto login(@RequestParam("userAgent") String userAgent, @RequestBody User user) {
        return common(userAgent, user);
>>>>>>> 317391993af2e817f10068eddf4f1251364f51d8
    }

    /**
     * 注册
     *
<<<<<<< HEAD
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/y/sign", method = RequestMethod.POST, produces = "application/json")
    public boolean sign(HttpServletRequest request, @RequestBody User user) {
        System.out.println(user.getUserPhone() + "***************" + user.getUserPassword());
        return loginClient.sign(request.getHeader("User-Agent"), user);
=======
     * @param user
     * @return
     */
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public boolean sign(@RequestParam("userAgent") String userAgent, @RequestBody User user) {
        boolean re = false;
        if (userService.duplicateChecking(user.getUserPhone()) == null) {
            re = userService.register(user.getUserPhone(), user.getUserPassword());
        }
        if (re == true) {
            System.out.println("****************注册成功！******************");
            return true;
        } else {
            System.out.println("****************注册失败！******************");
            return false;
        }
>>>>>>> 317391993af2e817f10068eddf4f1251364f51d8
    }


    /**
<<<<<<< HEAD
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
=======
     * 共用方法体
     *
     * @param userAgent
     * @param user
     * @return
     */
    public Dto common(String userAgent, User user) {
        try {
            System.out.println(user.getUserPhone() + "***************" + user.getUserPassword());
            user = userService.getLoginUser(user.getUserPhone(), user.getUserPassword());
            if (null != user) {
                String token = tokenService.generateToken(userAgent, user);
                tokenService.save(token, user);
                System.out.println (token);
                ToKenVO vo = new ToKenVO(token, Calendar.getInstance().getTimeInMillis() + 2 * 60 * 60 * 1000, Calendar.getInstance().getTimeInMillis());
                return DtoUtil.returnDataSuccess(vo);
            } else {
                return DtoUtil.returnFail("用户密码错误", ErrorCode.AUTH_AUTHENTICATION_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail(e.getMessage(), ErrorCode.AUTH_AUTHENTICATION_FAILED);
        }
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Dto loginout(@RequestParam("userAgent") String userAgent, @RequestParam("token") String token) {
        try {
            boolean result = tokenService.validate(userAgent, token);
            //如果验证成功，证明token是有效的，就可以删除
            if (result) {
                tokenService.delete(token);
                return DtoUtil.returnSuccess();
            } else {
                return DtoUtil.returnFail("token无效", ErrorCode.AUTH_TOKEN_INVALID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoUtil.returnFail("退出失败", ErrorCode.AUTH_TOKEN_INVALID);
        }
>>>>>>> 317391993af2e817f10068eddf4f1251364f51d8
    }
}
