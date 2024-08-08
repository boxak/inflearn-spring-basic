package com.study.inflearn.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);

		StatefulService statefulService1 = ctx.getBean(StatefulService.class);
		StatefulService statefulService2 = ctx.getBean(StatefulService.class);

		// ThreadA : 사용자A 10000원 주문
		int userAPrice = statefulService1.order("userA", 10000);

		// ThreadB : 사용자 20000원 주문
		int userBPrice = statefulService2.order("userB", 20000);

		System.out.println("price = " + userAPrice);

		((ConfigurableApplicationContext) ctx).close();

//		assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}

}
