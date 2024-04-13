package cn.youyou.apollo.demo.apollo2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 用来测试ConfigurationProperties注解
 * 这种注解的配置参数对象需要注册监听才能本地更新
 */
@Data
@ConfigurationProperties(prefix = "p")
public class Config1 {
    String a;
    String b;
    Map<String,String> c;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
