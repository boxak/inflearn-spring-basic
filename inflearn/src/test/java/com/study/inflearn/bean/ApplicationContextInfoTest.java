package com.study.inflearn.bean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.inflearn.AppConfig;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

	@Test
	@DisplayName("모든 빈 출력하기")
	@Order(1)
	void findAllBean() {
		String[] beanNames = ctx.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			Object bean = ctx.getBean(beanName);
			System.out.println("name = " + beanName + ", object = " + bean);
		}
	}

	@Test
	@DisplayName("애플리케이션 빈 출력하기")
	@Order(2)
	void findApplicationBean() {
		String[] beanNames = ctx.getBeanDefinitionNames();
		for (String beanName : beanNames) {

			BeanDefinition beanDefinition = ctx.getBeanDefinition(beanName);

			/*
			 * ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
			 * ROLE_INFRASTRUCTURE : 스프링에서 자체적으로 생성한 빈
			 */
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean = ctx.getBean(beanName);
				System.out.println("name = " + beanName + ", object = " + bean);
			}
		}
	}

}
