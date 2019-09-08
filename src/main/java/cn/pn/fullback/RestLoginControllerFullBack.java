package cn.pn.fullback;

import cn.pn.client.LoginClient;
import cn.pn.client.UserClient;
import cn.pn.pojo.User;
import cn.pn.pojo.utils.Dto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


/**
 * Created by dzt66 on 2019/8/12.
 */
@Component

public class RestLoginControllerFullBack implements LoginClient, UserClient {
    @Override
    public Dto quickLogin(String userAgent, User user) {
        return null;
    }

    @Override
    public Dto login(String userAgent, User user) {
        return null;
    }

    @Override
    public boolean sign(String userAgent, User user) {
        return false;
    }

    @Override
    public Dto loginout(String userAgent, String token) {
        return null;
    }

    @Override
    public Dto reloadToken(String userAgent, String token) {
        return null;
    }

    @Override
    public BigDecimal getUserBalance(int id) {
        return null;
    }
}
