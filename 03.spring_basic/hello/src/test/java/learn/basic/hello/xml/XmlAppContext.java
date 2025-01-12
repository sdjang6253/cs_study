package learn.basic.hello.xml;

import learn.basic.hello.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

    @Test
    void xmlAppContext(){
        ApplicationContext context = new GenericXmlApplicationContext("appConfig.xml");

        MemberService memberService = (MemberService)context.getBean("memberService" , MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
