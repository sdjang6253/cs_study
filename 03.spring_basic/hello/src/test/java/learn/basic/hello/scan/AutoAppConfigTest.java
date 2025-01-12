package learn.basic.hello.scan;

import learn.basic.hello.AutoAppConfig;
import learn.basic.hello.member.MemberService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService bean = context.getBean(MemberService.class);
        Assertions.assertThat(bean).isInstanceOf(MemberService.class);

    }
}
