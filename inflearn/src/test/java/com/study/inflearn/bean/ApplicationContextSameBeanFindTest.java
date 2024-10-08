package com.study.inflearn.bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.inflearn.member.MemberRepository;
import com.study.inflearn.member.MemoryMemberRepository;

public class ApplicationContextSameBeanFindTest {

	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SameBeanConfig.class);

	@Test
	@DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면 중복 오류 발생")
	void findBeanByTypeDuplicate() {

		assertThrows(NoUniqueBeanDefinitionException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				ctx.getBean(MemberRepository.class);

			}

		});

	}

	@Test
	@DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다")
	void findBeanByName() {
		MemberRepository memberRepository1 = ctx.getBean("memberRepository1", MemberRepository.class);
		assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
	}

	@Test
	@DisplayName("특정 타입 모두 조회하기")
	void findAllBeanByType() {
		Map<String, MemberRepository> beansOfType = ctx.getBeansOfType(MemberRepository.class);

		for (String beanName : beansOfType.keySet()) {
			System.out.println("key = " + beanName + ", bean = " + beansOfType.get(beanName));
		}

		System.out.println("beansOfType = " + beansOfType);
		assertThat(beansOfType.size()).isEqualTo(2);

	}

	@Test
	@DisplayName("모든 스프링 빈 조회하기")
	void findAllSpringBeans() {
		Map<String, Object> beansOfType = ctx.getBeansOfType(Object.class);

		for (String beanName : beansOfType.keySet()) {
			System.out.println("key = " + beanName + ", bean = " + beansOfType.get(beanName));
		}

		System.out.println("beansOfType = " + beansOfType);
		assertThat(beansOfType.size()).isGreaterThan(2);

	}

	@Configuration
	static class SameBeanConfig {
		@Bean
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}

		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}
	}
}
