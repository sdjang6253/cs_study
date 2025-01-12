package learn.basic.hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "learn.basic.hello",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION , classes = Configuration.class) // 예제를 안전하게 유지하기 위해서 사용
)
public class AutoAppConfig {
}
