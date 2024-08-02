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

import com.study.inflearn.discount.DiscountPolicy;
import com.study.inflearn.discount.FixDiscountPolicy;
import com.study.inflearn.discount.RateDiscountPolicy;

public class ApplicationContextExtendsFindTest {
	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);

	@Test
	@DisplayName("부모 타입 조회 시 자식이 둘 이상 있으면 중복 오류가 발생한다")
	void findBeanByParentTypeDuplicate() {

		assertThrows(NoUniqueBeanDefinitionException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				DiscountPolicy bean = ctx.getBean(DiscountPolicy.class);

			}

		});
	}

	@Test
	@DisplayName("부모 타입으로 조회 시, 자식 이 둘 이상 있으면 빈 이름을 지정하면 된다")
	void findBeanByParentTypeBeanName() {
		DiscountPolicy rateDiscountPolicy = ctx.getBean("rateDiscountPolicy", DiscountPolicy.class);
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}

	@Test
	@DisplayName("특정 하위 타입으로 조회")
	void findBeanBySubtype() {
		RateDiscountPolicy rateDiscountPolicy = ctx.getBean(RateDiscountPolicy.class);
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}

	@Test
	@DisplayName("부모 타입으로 모두 조회하기")
	void findAllBeanByParentType() {
		Map<String, DiscountPolicy> beanOfType = ctx.getBeansOfType(DiscountPolicy.class);
		assertThat(beanOfType.size()).isEqualTo(2);
		for (String key : beanOfType.keySet()) {
			System.out.println("key = " + key + ", value = " + beanOfType.get(key));
		}
	}

	@Configuration
	static class TestConfig {
		@Bean
		public DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}

		@Bean
		public DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
	}

}
