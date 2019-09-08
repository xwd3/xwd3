package cn.pn.client;

import cn.pn.fullback.RestLoginControllerFullBack;
import cn.pn.pojo.utils.Dto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;

/**
 * Created by dzt66 on 2019/8/12.
 */
@FeignClient(name = "pn-user-provider", fallback = RestLoginControllerFullBack.class)
public interface UserClient {

    /**
     * Token置换
     * @param userAgent
     * @param token
     * @return
     */
    @RequestMapping(value = "/reloadToken", method = RequestMethod.POST)
    @ResponseBody
    Dto reloadToken(@RequestParam("userAgent") String userAgent, @RequestParam("token") String token);

    /**
     * 查询余额
     * @param id
     * @return
     */
    @RequestMapping(value = "/getBalance", method = RequestMethod.POST)
    public BigDecimal getUserBalance(@RequestParam("id") int id);
}
