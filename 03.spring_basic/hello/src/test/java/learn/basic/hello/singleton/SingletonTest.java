package learn.basic.hello.singleton;

import learn.basic.hello.AppConfig;
import learn.basic.hello.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SingletonTest {

    @Test
    @DisplayName(" 스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //isNotSameAs 와 isNotEqualsTo 는 무슨 차이일까 -> isSameAs 는 == , isEqualsTo 는 equals() 입니다.
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        Assertions.assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = context.getBean("memberService", MemberService.class);

        MemberService memberService2 = context.getBean("memberService", MemberService.class);

        //isNotSameAs 와 isNotEqualsTo 는 무슨 차이일까 -> isSameAs 는 == , isEqualsTo 는 equals() 입니다.
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
