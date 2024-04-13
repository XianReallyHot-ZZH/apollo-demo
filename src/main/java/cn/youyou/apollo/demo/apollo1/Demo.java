package cn.youyou.apollo.demo.apollo1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Demo {

    /**
     * 测试@Value注解
     */
    @Value("${p.a}")
    String a;

}
