package com.study.inflearn.bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.inflearn.AppConfig;
import com.study.inflearn.member.MemberService;
import com.study.inflearn.member.MemberServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationContextBasicFindTest {
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("빈 이름으로 조회")
	@Order(1)
	void findBeanByName() {
		MemberService memberService = ctx.getBean("memberService", MemberService.class);

		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("이름 없이 타입으로만 조회")
	@Order(2)
	void findBeanByType() {
		MemberService memberService = ctx.getBean(MemberService.class);
		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
	}

	@Test
	@DisplayName("구체적 타입으로 조회")
	@Order(3)
	void findBeanByName2() {
		MemberServiceImpl memberService = ctx.getBean("memberService", MemberServiceImpl.class);

		assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

	}

	@Test
	@DisplayName("빈 이름이 없을 경우")
	void cannotFindBeanByName() {

		assertThrows(NoSuchBeanDefinitionException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				ctx.getBean("xxxx", MemberService.class);
			}
		});
	}
}
