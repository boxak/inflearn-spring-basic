package com.study.inflearn.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.study.inflearn.AppConfig;

public class BeanDefinitionTest {

	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

	GenericXmlApplicationContext xmlContext = new GenericXmlApplicationContext("appConfig.xml");

	@Test
	@DisplayName("빈 설정 메타정보 확인")
	void findApplicationBean() {
		String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = ctx.getBeanDefinition(beanDefinitionName);

			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out
						.println("beanDefinitionName = " + beanDefinitionName + " BeanDefinition = " + beanDefinition);
			}
		}
	}

	@Test
	@DisplayName("빈 설정 메타정보 확인")
	void findApplicationBeanInXmlFormat() {
		String[] beanDefinitionNames = xmlContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition = xmlContext.getBeanDefinition(beanDefinitionName);

			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out.println(
						"XML - beanDefinitionName = " + beanDefinitionName + " BeanDefinition = " + beanDefinition);
			}
		}
	}
}
