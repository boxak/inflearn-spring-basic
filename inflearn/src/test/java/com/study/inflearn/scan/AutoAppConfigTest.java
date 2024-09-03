package com.study.inflearn.scan;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.inflearn.AutoAppConfig;
import com.study.inflearn.member.MemberService;

public class AutoAppConfigTest {

	@Test
	void basicScan() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AutoAppConfig.class);

		MemberService memberService = ctx.getBean(MemberService.class);

		assertThat(memberService).isInstanceOf(MemberService.class);

		ctx.getBeansOfType(Object.class);
		for (Map.Entry<String, Object> entry : ctx.getBeansOfType(Object.class).entrySet()) {
			String key = entry.getKey();
			Object beanObj = entry.getValue();

			System.out.println("bean name : " + key + " and bean object : " + beanObj);
		}
	}

}
