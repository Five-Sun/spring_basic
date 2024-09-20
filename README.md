# 스프링 핵심 원리 - 기본편
---
### 강의 목차
1. 객체 지향 설계와 스프링
2. 스프링 핵심 원리 이해
3. 스프링 컨테이너와 스프링 빈
4. 싱글톤 컨테이너
5. 컴포넌트 스캔
6. 의존관계 자동 주입
7. 빈 생명주기 콜백
8. 빈 스코프

---
### FOR WHO
* 스프링을 처음 접하는 개발자
* 실무에서 스프링을 사용하지만 스프링의 핵심 원리를 제대로 이해하고 사용하고 싶은 개발자*
* 객체 지향 설계에 고민이 많은 개발자

---
### 스프링의 탄생
EJB: Enterprise Java Beans, 예전 자바 진영의 표준 기술, 예전 개발자들이 EJB 지옥에 빠지게 되는데

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

**역할과 구현을 분리!**

역할과 구현으로 구분하면 세상이 단순해지고, 유연해지며 변경도 편리해진다. 클라이언트는 구현 대상의 내부 구조를 몰라도 된다!

역할 = 인터페이스, 구현 = 인터페이스를 구현한 클래스

객체를 설계할 때 역할과 구현을 명확히 분리한다.

역할(인터페이스) 자체가 변하면, 클라이언트, 서버 모두에 큰 변경이 발생한다.

인터페이스를 안정적으로 잘 설계하는 것이 중요하다.


자바 언어의 다형성? -> 오버라이딩을 떠올리자

다형성으로 인터페이스 구현한 객체를 실행 시점에 유연하게 변경할 수 있다.

다형성의 본질? 

다형성의 본질을 이해하려면 **협력**이라는 객체 사이의 관계에서 시작해야 하고 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경할 수 있다.

스프링과 객체 지향?

다형성이 가장 중요하다!

스프링에서 이야기하는 제어의 역전, 의존관계 주입은 다형성을 활용해서 역할과 구현을 편리하게 다룰 수 있도록 지원한다.

---
### 좋은 객체 지향 설계의 5가지 원칙(SOLID)
클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리

1. SRP 단일 책임 원칙
  - 한 클래스는 하나의 책임만 가져야 한다.
  - 중요한 기준은 변경이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것이라고 볼 수 있다.

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

- @Configuration과 싱글톤

- @Configuration과 바이트코드 조작의 마법:
  스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야 한다. 그래서 스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용한다.
  @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
  덕분에 싱글톤이 보장되는 것이다.
  스프링 설정 정보는 항상 
  **@Configuration을 사용하자!**

---
### Section7
- 컴포넌트 스캔과 의존관계 자동 주입 시작하기:
  스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을 제공한다.
  의존관계도 자동으로 주입하는 @Autowired 라는 기능도 제공한다.

- 탐색 위치와 기본 스캔 대상
  탐색할 패키지의 시작 위치 지정를 지정할 수 있다. default는 설정 정보 클래스의 package가 시작 위치. -> basePackages = "탐색할 위치"
**컴포넌트 스캔 기본 대상**
1. @Component: 컴포넌트 스캔에서 사용
2. @Controller: 스프링 MVC 컨트롤러에서 사용, 스프링 MVC 컨트롤러로 인식
3. @Service: 스프링 비즈니스 로직에서 사용, 사실 @Service는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.
4. @Repository: 스프링 데이터 접근 계층에서 사용, 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
5. @Configuration: 스프링 설정 정보에서 사용,  앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리한다.

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
  대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.
  생성자 주입을 사용하면 주입 데이터를 누락 했을 때 컴파일 오류가 발생한다.
  생성자 주입을 사용하면 필드에 final 키워드를 사용할 수 있다. 그래서 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.
  오직 생성자 주입 방식만 final 키워드를 사용할 수 있다.
  
- 롬복과 최신 트렌드
  //lombok 라이브러리 추가 시작
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    testCompileOnly 'org.projectlombok:lombok'
    testAnnotationProcessor 'org.projectlombok:lombok'
 //lombok 라이브러리 추가 끝

- 조회 빈이 2개 이상 - 문제
  NoUniqueBeanDefinitionException가 발생한다. 오류메시지를 통해 친절하게 알려준다!
  스프링 빈을 수동 등록해서 문제를 해결해도 되지만, 의존 관계 자동 주입에서 해결하는 여러 방법이 있다.

- @Autowired 필드명, @Qualifier, @Primary
  @Autowired 필드 명 매칭: 파라미터 이름으로 빈 이름을 추가 매칭한다, 필드 명 매칭은 먼저 타입 매칭을 시도하고 그 결과에 여러 빈이 있을 때 추가로 동작하는 기능이다.
  @Qualifier 사용: 추가 구분자를 붙여주는 방법이다. @Qualifier를 붙여주고 등록한 이름을 적어준다.
  @Primary 사용: 우선순위를 점하는 방법이다.
  **@Primary 보다 @Qualifier가 우선권이 높다.**

- 애노테이션 직접 만들기

- 조회한 빈이 모두 필요할 때, List, Map
  

- 자동, 수동의 올바른 실무 운영 기준
  **편리한 자동 기능을 기본으로 사용하자**
  스프링이 나오고 시간이 갈수록 점점 자동을 선호하는 추세이다. 그렇다면 수동 빈 등록은 언제 사용하면 좋을까?
  애플리케이션은 크게 업무 로직과 기술 지원 로직으로 나눌 수 있다.
  업무 로직 빈: 웹을 지원하는 컨트롤러, 핵심 비즈니스 로직이 있는 서비스, 데이터 계층의 로직을 처리하는 리포지토리 등이 모두 업무 로직이다.
  기술 지원 빈: 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용된다. DB 연결이나 공통 로그 처리 처럼 업무 로직을 지원하기 위한 하부 기술이나 공통 기술이다.
  애플리케이션에 광범위하게 영향을 미치는 기술 지원 객체는 수동 빈으로 등록해서 딱! 설정 정보에 바로 나타나게 하는 것이 유지보수 하기 좋다.

---
### Section9
- 빈 생명주기 콜백 시작
  - 데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면, 객체의 초기화와 종료 작업이 필요하다.
  - 스프링 빈은 간단하게 다음과 같은 라이프사이클을 가진다. 객체 생성 -> 의존관계 주입
  - **객체의 생성과 초기화를 분리하자.**
  - 생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다. 반면에 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는등 무거운 동작을 수행한다.
  - **스프링 빈의 이벤트 라이프 사이클**
  - 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 스프링 종료
  - 스프링은 크게 아래서 학습할 3가지 방법으로 빈 생명주기 콜백을 지원한다.
  
- 인터페이스 initializingBean, DisposalbleBean
  - InitializingBean 은 afterPropertiesSet() 메서드로 초기화를 지원한다.
  - DisposableBean 은 destroy() 메서드로 소멸을 지원한다.
    
- 빈 등록 초기화, 소멸 메서드 지정
  - @Bean(initMethod = "init", destroyMethod = "close") 다음과 같이 사용한다.
  - 메서드 이름을 자유롭게 줄 수 있꼬 스프링 빈이 스프링 코드에 의존하지 않는다. 코드를 고칠 수 없는 외부라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
  - destroyMethod 는 기본값이(inferred)(추론)으로 등록되어 있다.
  - 이 추론 기능은 close, shutdown 라는 이름의 메서드를 자동으로 호출해준다.
 
- 애노테이션 @PostConstruct, @PreDestory
  - 두 애노테이션을 사용하면 가장 편리하게 초기화와 종료를 실행할 수 있다.
  - 최신 스프링에서 가장 권장하는 방법이다.
  - 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 동작한다.
  - 유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것이다. 외부 라이브러리를 초기화, 종료해야 하면 @Bean 기능을 사용하자.
 
---
### Section10
- 빈 스코프란?
  - 지금까지 우리는 스프링 빈이 스프링 컨테이너의 시작과 함께 생성되어서 종료될 때까지 유지된다고 학습했다. 그 이유는 싱글톤 스코프로 생성되기 때문이다.
  - 싱글톤: 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프이다.
  - 프로토 타입: 프로토타입 빈의 생성과 의존고나계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프이다.
  - 웹 관련 스코프
      - request: 웹 요청이 들어오고 나갈떄 까지 유지되는 스코프이다.
      - session: 웹 세션이 생성되고 종료될 떄 까지 유지되는 스코프이다.
      - application: 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프이다.
  
- 프로토타입 스코프
  1. 프로토타입 스코프의 빈을 컨테이너에 요청한다.
  2. 컨테이너는 이 시점에 프로토타입 빈을 생성하고, 필요한 의존관계를 주입한다.
  3. 컨테이너는 생성한 프로토타입 빈을 클라이언트에 반환한다.
  4. 이후 같은 요청이 오면 항상 새로운 빈을 생성해서 반환한다.
  - 핵심은 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다는 것이다. 그래서 @PreDestroy는 호출되지 않는다.
  
- 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점
  - 싱글톤 빈이 의존관계 주입을 통해서 프로토타입 빈을 사용하는 경우, 싱글톤 빈과 프로토타입 빈이 함께 계속 유지되는 것이 문제다.
  
- 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 Provider로 문제 해결
  - ac.getBean()을 통해서 빈을 생성하면 컨테이너에 종속적인 코드가 되고 단위 테스트도 어려워진다.
  - 의존관계를 외부에서 주입(DI) 받는게 아니라 직접 필요한 의존관계를 찾는 것을 Dependency Lookup(DL) 의존관계 조회 라고 한다.
  - 그러면 프로토타입 빈을 언제 사용할까? 매번 사용할 때 마다 의존관계 주입이 완료된 새로운 객체가 필요하면 사용하면 된다. 하지만 직접적으로 사용하는 일은 매우 드물다.
  1. ObjectFactory: 과거에 지정한 빈을 컨테이너에서 대신 찾아주는 DL 서비스, 기능이 단순하고 별도의 라이브러리가 필요 없음, 스프링에 의존
  2. ObjectProvider: ObjectFactory에 편의 기능이 추가된 현재 사용되는 DL 서비스, 별도의 라이브러리 필요 없음, 스프링에 의존
  3. javax.inject.Provider라는 JSR-330 자바 표준을 사용하는 방법, 라이브러리 추가 필요(jakarta.inject:jakarta.inject-api:2.0.1, 3.0이상)
  - 참고: 스프링을 사용하다 보면 이 기능 뿐만 아니라 다른 기능들도 자바 표준과 스프링이 제공하는 기능이 겹칠때가 많이 있다. 대부분 스프링이 더 다양하고 편리한 기능을 제공해주기 때문에, 특별히 다른 컨테이너를 사용할 일이 없다면, 스프링이 제공하는 기능을 사용하면 된다.

- 웹 스코프
  - 웹 스코프는 웹 환경에서만 동작한다
  - 프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리한다. 따라서 종료 메서드가 호출된다.
  1. request: HTTP 요청 하나가 들어오고 나갈 때까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고 관리된다.
  2. session: HTTP Session과 동일한 생명주기를 가지는 스코프
  3. application: 서블릿 컨텍스트와 동일한 생명주기를 가지는 스코프
  4. websocket: 웹 소켓과 동일한 생명주기를 가지는 스코프
  
- request 스코프 예제 만들기
  -스프링 애플리케이션을 실행 시키면 오류가 발생한다. 스프링 애플리케이션을 실행하는 시점에 싱글톤 빈은 생성해서 주입이 가능하지만, request 스코프 빈은 아직 생성되지 않는다. 이 빈은 실제 고객의 요청이 와야 생성할 수 있다!
  
- 스코프와 Provider

  
- 스코프와 프록시
  -@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) 사용시, MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다
  -사실 Provider를 사용하든, 프록시를 사용하든 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다.



