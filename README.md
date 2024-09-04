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
3.1프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스를 바꿀 수 있어야 한다.
3.2인터페이스르르 구현한 구현체를 믿고 사용하려면, 이 원칙이 필요하다

4. ISP 인터페이스 분리 원칙
4.1특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
4.2인터페이스가 명확해지고, 대체 가능성이 높아진다.

5. DIP 의존관계 역전 원칙
5.1프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다."
5.2역할에 의존하게 해야 한다는 의미이다.
5.3클라이언트가 인터페이스에 의존해야 유연하게 구체를 변경할 수 있다. 구현체에 의존하게 되면 변경이 매우 어려워진다.

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
1.1회원을 가입하고 조회할 수 있다.
1.2회원은 일반과 VIP 두 가지 등급이 있다.
1.3회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다.
  
2. 주문과 할인 정책
2.1회원은 상품을 주문할 수 있다.
2.2회원 등급에 따라 할인 정책을 적용할 수 있다.
2.3할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라
2.4할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다.

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
8.1제어의 역전(IoC): 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 말한다.
8.2의존관계 주입(DI): 실행 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 의존관계 주입이라 한다.
8.3AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC 컨테이너 또는 DI 컨테이너라고 한다.

9. 스프링으로 전환하기
9.1@Configuration: 설정을 구성한다는 뜻
9.2@Bean: 스프링 컨테이너에 스프링 빈으로 등록
9.3ApplicationContext를 스프링 컨테이너라 한다.

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

5. 다양한 설정 형식 지원 - 자바 코드, XML

6. 스프링 빈 설정 메타 정보 - BeanDefinition

