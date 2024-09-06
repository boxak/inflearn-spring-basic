package com.study.inflearn.scan.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/*
 * FilterType 옵션
 * 
 * ANNOTATION : 기본값, 애노테이션을 인식해서 동작
 * ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작
 * ASPECTJ : AspectJ 패턴 사용
 * REGEX : 정규표현식
 * CUSTOM : TypeFilter 라는 인터페이스를 구현해서 처리
 */

public class ComponentFilterAppConfigTest {
	@Test
	void filterScan() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		BeanA beanA = ctx.getBean("beanA", BeanA.class);
		assertThat(beanA).isNotNull();

		Assertions.assertThrows(NoSuchBeanDefinitionException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				ctx.getBean("beanB", BeanB.class);

			}
		});
	}

	@Configuration
	@ComponentScan(includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
	static class ComponentFilterAppConfig {

	}
}
