package cn.youyou.apollo.demo.apollo1;

import cn.youyou.apollo.demo.apollo2.Config1;
import cn.youyou.apollo.demo.apollo2.Config2;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.properties.ConfigurationPropertiesRebinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@EnableApolloConfig
@Import({Config2.class})
public class ApolloDemoApplication {

    // 这个功能和EnableApolloConfig注解有一部分能力是重叠的
    @Bean
    ApolloChangedListener listener(){
        return new ApolloChangedListener();
    }

    @Autowired
    Demo demo;

    @Autowired
    Config1 conf;

    @Autowired
    Environment environment;


    public static void main(String[] args) {
        SpringApplication.run(ApolloDemoApplication.class, args);
    }


    @GetMapping("/cf")
    public String cf() {
        System.out.println(demo.getA());
        System.out.println(conf);
        System.out.println(System.identityHashCode(conf));
        System.out.println(environment.getProperty("p.a"));
        return demo.getA() + ",\n" + conf+ ",\n" + System.identityHashCode(conf);
//        return demo.getA();
    }

    @Bean
    ApplicationRunner applicationRunner(@Autowired ApplicationContext context) {
        return x -> {
            System.out.println(" >>> demo.a:" + demo.getA());
            System.out.println(" >>> config1:" + conf);

            String pa = environment.getProperty("p.a"); // 远程注册中心把本地的environment的变量都改掉了
            System.out.println(" >>> environment p.a:" + pa);

            ConfigurationPropertiesRebinder rebinder = context.getBean(ConfigurationPropertiesRebinder.class);
            System.out.println(rebinder);
        };
    }

}
