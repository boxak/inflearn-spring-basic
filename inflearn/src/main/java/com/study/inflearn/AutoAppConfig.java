package com.study.inflearn;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.study.inflearn.member", excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

	/*
	 * @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다. Bean 이름은 기본적으로 Class 명을
	 * 사용하되 맨 앞글자만 소문자로 변한다. Bean 이름을 직접 지정하려면 @Component("memberService2") 이런 식으로
	 * 부여하면 된다. 모든 자바 코드를 찾아서 빈으로 등록하게 하지 않고 필요한 패키지만 찾게 하고 싶으면 basePackages 를 지정하면
	 * 된다.
	 */

}
