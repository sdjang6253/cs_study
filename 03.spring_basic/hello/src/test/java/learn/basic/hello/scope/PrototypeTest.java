package learn.basic.hello.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class); // 이 문장으로 인해 PrototypeBean 함수에 @Component 가 붙는것 처럼 핻동이 된다.
        System.out.println("find bean");
        PrototypeBean bean = context.getBean(PrototypeBean.class);
        System.out.println("find bean2");
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);

        Assertions.assertThat(bean).isNotSameAs(bean2);
        context.close();

    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }

    }
}
