package learn.basic.hello.autowired;

import learn.basic.hello.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        /*
        Intellij 에서는 Member가 애초에 Bean 이 아니기 때문에 에러가 발생한다
        에러문구 -> Autowired members must be defined in valid Spring bean (@Component|@Service|...)
        다만 수행은 되는듯.
         */
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("1 . " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("2 . " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("3 . " + noBean3);
        }
    }
}
