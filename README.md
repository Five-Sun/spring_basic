# 스프링 핵심 원리 - 기본편
---
강의 목차
1. 객체 지향 설계와 스프링
2. 스프링 핵심 원리 이해
3. 스프링 컨테이너와 스프링 빈
4. 싱글톤 컨테이너
5. 컴포넌트 스캔
6. 의존관계 자동 주입
7. 빈 생명주기 콜백
8. 빈 스코프

---
FOR WHO
* 스프링을 처음 접하는 개발자
* 실무에서 스프링을 사용하지만 스프링의 핵심 원리를 제대로 이해하고 사용하고 싶은 개발자*
* 객체 지향 설계에 고민이 많은 개발자

---
### 스프링의 탄생
EJB: Enterprise Java Beans, 예전 자바 진영의 표준 기술
예전 개발자들이 EJB 지옥에 빠지게 되는데
스프링: EJB 컨테이너 대체, 단순함, 현재 사싱실 표준 기술
스프링 이름은 전통적인 J2EE(EJB)라는 겨울을 넘어 새로운 시작이라는 뜻으로 지음

EJB 엔티티 빈 -> 하이버네이트 -> JPA 순으로 발전

### 스프링이란?
필수: 스프링 프레임워크, 스프링 부트
선택: 스프링 데이터, 스프링 세션, 스프링 시큐리티, 스프링 Reset Docs, 스프링 배치,  스프링 클라우드

스프링 프레임워크: 
DI 컨테이너, AOP, 이벤트, 스프링 MVC, 스프링 WebFlux, 트랜잭션, JDBC, ORM 지원 등을 포함


스프링 부트:
최근에는 스프링 부트를 통해서 스프링 프레임워크의 기술들을 편리하게 사용한다.
그래서 최근에는 기본으로 사용한다.
Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨

### 개발자들은 왜 스프링에 열광했는가?
* 스프링은 자바 언어 기반의 프레임 워크
* 자바 언어의 가장 큰 특징 - 객체 지향 언어
* 스프링은 객체 지향 언어가 가진 강력한 특징을 살려내는 프레임워크
* 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크

### 좋은 객체 지향 프로그래밍이란?
객체 지향 특징
* 추상화
* 캡슐화
* 상속
* **다형성**

객체 지향 프로그래밍은 컴퓨터 프로그래밍은 컴퓨터 프로그램을 명령어의 목록으로 보는 시각에서 벗어나 여러개의 독립된 단위, 즉 "객체"들의 모임으로 파악하고자 하는 것이다. 각각의 객체는 메시지를 주고 받고, 데이터를 처리할 수 있다.

유연하고, 변경이 용이? -> **다형성**
* 레고 블럭 조립하듯이
* 키보드, 마우스 갈아 끼우듯이
* 컴퓨터 부품 갈아 끼우듯이
* 컴포넌트를 쉽고 유연하게 변경하면서 개발할 수 있는 방법

역할과 구현을 분리!
역할과 구현으로 구분하면 세상이 단순해지고, 유연해지며 변경도 편리해진다. 클라이언트는 구현 대상의 내부 구조를 몰라도 된다!
역할 = 인터페이스, 구현 = 인터페이스를 구현한 클래스
객체를 설계할 때 역할과 구현을 명확히 분리한다.
역할(인터페이스) 자체가 변하면, 클라이언트, 서버 모두에 큰 변경이 발생한다.
인터페이스를 안정적으로 잘 설계하는 것이 중요하다.

자바 언어의 다형성? -> 오버라이딩을 떠올리자
다형성으로 인터페이스 구현한 객체를 실행 시점에 유연하게 변경할 수 있다.

다형성의 본질? 다형성의 본질을 이해하려면 **협력**이라는 객체 사이의 관계에서 시작해야 하고 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경할 수 있다.

스프링과 객체 지향?
다형성이 가장 중요하다!
스프링에서 이야기하는 제어의 역전, 의존관계 주입은 다형성을 활용해서 역할과 구현을 편리하게 다룰 수 있도록 지원한다.

---
### 좋은 객체 지향 설계의 5가지 원칙(SOLID)
클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리

1. SRP 단일 책임 원칙
1.1한 클래스는 하나의 책임만 가져야 한다.
1.2중요한 기준은 변경이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것이라고 볼 수 있다.

2. OCP 개방-폐쇄 원칙
2.1확장에는 열려 있으나 변경에는 닫혀 있어야한다.
2.2다형성을 활용하라. 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현한다.

3. LSP 리스코프 치환 원칙
프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스를 바꿀 수 있어야 한다.
인터페이스르르 구현한 구현체를 믿고 사용하려면, 이 원칙이 필요하다

4. ISP 인터페이스 분리 원칙
특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
인터페이스가 명확해지고, 대체 가능성이 높아진다.

5. DIP 의존관계 역전 원칙
프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다."
역할에 의존하게 해야 한다는 의미이다.
클라이언트가 인터페이스에 의존해야 유연하게 구체를 변경할 수 있다. 구현체에 의존하게 되면 변경이 매우 어려워진다.

**정리**
객체 지향의 핵심은 다형성, 그러나 이것만으로는 OCP, DIP를 지킬 수 없다. 무언가가 더 필요하다!

---
### 객체 지향 설계와 스프링
스프링은 `DI`,`DI 컨테이너`를 통해 다형성 + OCP, DIP를 가능하게 지원한다.
클라이언트 코드의 변경 없이 기능을 확장할 수 있다.
마치 부품을 교체하듯이 개발할 수 있다.
하지만 실무에서는 인터페이스를 도입하면 추상화라는 비용이 발생한다. 기능을 확장할 가능성을 파악하여 구체 클래스를 직접 사용할지 인터페이스를 도입할지 결정하는 것도 방법이다.

---
**비즈니스 요구사항과 설계**
1. 회원
회원을 가입하고 조회할 수 있다.
회원은 일반과 VIP 두 가지 등급이 있다.
회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다.
  
2. 주문과 할인 정책
회원은 상품을 주문할 수 있다.
회원 등급에 따라 할인 정책을 적용할 수 있다.
할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라
할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다.

---
### Section3
1. 회원 도메인 설계(회원 도메인 협력 관계, 회원 클래스 다이어그램, 회원 객체 다이어그램)
2. 회원 도메인 개발
3. 회원 도메인 실행과 테스트
4. 주문과 할인 도메인 설계(역할과 구현을 분리하기)
5. 주문과 할인 도메인 개발
6. 주문과 할인 도메인 실행과 테스트
---
### Section4
1. 새로운 할인 정책 개발

2. 새로운 할인 정책 적용과 문제점

3. 관심사의 분리

4. AppConfig 리팩터링: **사용 영역과 구현 영역을 분리 -> 역할이 잘 들어나고 중복 제거**

5. 새로운 구조와 할인 정책 적용

6. 전체 흐름 정리

7. 좋은 객체 지향 설계의 5가지 원칙의 적용

8. IoC, DI, 그리고 컨테이너
제어의 역전(IoC): 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 말한다.
의존관계 주입(DI): 실행 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 의존관계 주입이라 한다.
AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC 컨테이너 또는 DI 컨테이너라고 한다.

9. 스프링으로 전환하기
@Configuration: 설정을 구성한다는 뜻
@Bean: 스프링 컨테이너에 스프링 빈으로 등록
ApplicationContext를 스프링 컨테이너라 한다.

---
### Section5
1. 스프링 컨테이너 생성: 요즘은 애노테이션 기반으로 설정 클래스를 만든다.
스프링 컨테이너 생성: 구성 정보 활용
스프링 빈 등록  
스프링 빈 의존관계 설정 - 준비 > 완료(설정 정보를 참고해서 의존관계를 주입한다.)

2. 컨테이너 등록된 모든 빈 조회

3. 스프링 빈 조회: 상속 관계(부모 타입으로 조회하면, 자식 타입도 함께 조회)

4. BeanFactory와 ApplicationContext
BeamFactory: 스프링 컨테이너의 최상위 인터페이스, 스프링 빈을 관리하고 조회하는 역할을 담당
ApplicationContext: BeanFactory 기능을 모두 상속받아서 제공, 빈을 관리하고 조회하는 기능 + 수많은 부가 기능을 제공

![image](https://github.com/user-attachments/assets/fb19d8d1-06b8-43f7-9bef-78e1696a7cea)
![image](https://github.com/user-attachments/assets/025910f0-d1a8-4803-9514-53ef9793ebcd)

6. 다양한 설정 형식 지원 - 자바 코드, XML

7. 스프링 빈 설정 메타 정보 - BeanDefinition
BeanDefinition이라는 추상화가 존재한다. 스프링 컨테이너는 자바 코드인지, XML인지 몰라도 된다. 오직 BeanDefinition만 알면 된다.
빈당 각각 하나씩 메타 정보가 생성된다.
![image](https://github.com/user-attachments/assets/ff265f99-c910-46da-b873-e414dadc11ee)
![image](https://github.com/user-attachments/assets/29750a00-10b9-4f9a-a0df-647d7e1f7d51)

---
### Section6
1. 웹 애플리케이션과 싱글톤: 스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때 마다 객체를 새로 생성
2. 싱글톤 패턴: 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다

**싱글톤 패턴 문제점**
- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
- 의존관계상 클라이언트가 구체 클래스에 의존한다. ->DIP를 위반한다.
- 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
- 테스트하기 어렵다.
- 내부 속성을 변경하거나 초기화 하기 어렵다.
- private 생성자로 자식 클래스를 만들기 어렵다.
- 결론적으로 유연성이 떨어진다.
- 안티패턴으로 불리기도 한다.

- 싱글톤 컨테이너:
스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다.
스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용할 수 있다.

- 싱글통 방식의 주의점: 하나의 같은 객체 인스턴스를 공유하기 때문에 객체는 상태를 유지하게 설계하면 안된다. 무상태로 설계해야 한다.
- 
- @Configuration과 싱글톤
- 
- @Configuration과 바이트코드 조작의 마법:
스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야 한다. 그래서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다.
@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
덕분에 싱글톤이 보장되는 것이다.
스프링 설정 정보는 항상 
**@Configuration을 사용하자!**

---
### Section7
- 컴포넌트 스캔과 의존관계 자동 주입 시작하기: 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을 제공한다.
의존관계도 자동으로 주입하는 @Autowired 라는 기능도 제공한다.

- 탐색 위치와 기본 스캔 대상
탐색할 패키지의 시작 위치 지정를 지정할 수 있다. default는 설정 정보 클래스의 package가 시작 위치. -> basePackages = "탐색할 위치"
**컴포넌트 스캔 기본 대상**
@Component: 컴포넌트 스캔에서 사용
@Controller: 스프링 MVC 컨트롤러에서 사용, 스프링 MVC 컨트롤러로 인식
@Service: 스프링 비즈니스 로직에서 사용, 사실 @Service는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.
@Repository: 스프링 데이터 접근 계층에서 사용, 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
@Configuration: 스프링 설정 정보에서 사용,  앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리한다.

- 필터
includeFilters: 컴포넌트 스캔 대상을 추가로 지정한다.
excludeFilters: 컴포넌트 스캔에서 제외할 대상을 지정한다.

- 중복 등록과 충돌
컴포넌트 스캔에서 같은 빈 이름을 등록하면 어떻게 될까?
1. 자동 빈 등록 vs 자동 빈 등록: 이 경우 스프링은 오류를 발생시킵니다.
2. 수동 빈 등록 vs 자동 빈 등록: 수동 빈 등록이 우선권을 가진다. 수동 빈이 자동 빈을 오버라이딩 해버린다.
  -> 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.

---
### Section8
- 다양한 의존고나계 주입 방법
생성자 주입: 생성자를 통해서 의존 관계를 주입 받는 방법, 생성자 호출 시점에 딱 1번만 호출되는 것이 보장되어 불편, 필수 의존관계에 사용한다. 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다.
수정자 주입(setter 주입): setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법, 선택, 변경 가능성이 있는 의존관계에 사용한다.
필드 주입: 필드에 바로 주입하는 방법, 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있고 DI 프레임워크가 없으면 아무것도 할 수 없기 때문에 사용하지 말자!
일반 메서드 주입: 한번에 여러 필드를 주입 받을 수 있지만 일반적으로 잘 사용하지 않는다.
**의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.**
  
- 옵션 처리
주입할 스프링 빈이 없어도 동작해야 할 때가 있는데 @Autowired만 사용하면 required 옵션의 기본값이 true로 되어 있어서 자동 주입 대상이 없으면 오류가 발생한다.
자동 주입 대상을 옵션 처리하는 방법은 다음과 같다.
1. @Autowired(required = false): 자동 주입할 대상이 없으면 메서드 자체가 호출 안됨
2. org.springframework.lang.@Nullable: 자동 주입할 대상이 없으면 null이 입력된다.

- 생성자 주입을 선택해라!
- 롬복과 최신 트렌드
- 조회 빈이 2개 이상 - 문제
- @Autowired 필드명, @Qualifier, @Primary
- 애노테이션 직접 만들기
- 조회한 빈이 모두 필요할 때, List, Map
- 자동, 수동의 올바른 실무 운영 기준
