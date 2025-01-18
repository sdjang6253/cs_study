package learn.basic.hello.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = context.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class , ClientBean.class);
        ClientBean clientBean = context.getBean(ClientBean.class);
        int count1 = clientBean.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = context.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean{

        //private final ObjectProvider<PrototypeBean> prototypeBeanProvider;  //ObjectProvider 사용법
        private final Provider<PrototypeBean> prototypeBeanProvider; // provider.get() 을 하면 스프링 컨테이너를 통해서 해당 빈을 반환한다. , 자바 표준

        public int logic(){
            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject();  //ObjectProvider 사용법
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("Prototype.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("Prototype.destroy");
        }
    }

}
