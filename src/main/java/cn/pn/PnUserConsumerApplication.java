package cn.pn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 启动类配置注解：注意如果加了Feign等要加下面的注解
 *
 * @EnableFeignClients
 * @EnableEurekaClient
 */

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class PnUserConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PnUserConsumerApplication.class, args);
	}

}
