package singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.study.inflearn.AppConfig;
import com.study.inflearn.member.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureDIContainer() {
		AppConfig appConfig = new AppConfig();

		// 1. 조회 : 호출할 때 마다 객체 생성
		MemberService memberService1 = appConfig.memberService();

		// 2. 조회 : 호출할 때 마다 객체 생성
		MemberService memberService2 = appConfig.memberService();

		// 참조 값이 다른 것을 확인
		System.out.println("MemberService1 = " + memberService1);
		System.out.println("MemberService2 = " + memberService2);

		assertThat(memberService1).isNotSameAs(memberService2);
	}

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletonServiceTest() {
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();

		System.out.println("singletonService1 = " + singletonService1);
		System.out.println("singletonService2 = " + singletonService2);

		assertThat(singletonService1).isSameAs(singletonService2);

		// isSameAs : ==
		// isEqualTo : equals
	}
}
