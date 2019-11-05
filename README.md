# jpa-shop
> 김영한님의 jpa 강의 후 개인 복습을 위한 학습용 repository

SpringBoot와 JPA를 활용한 웹어플리케이션 개발
: 간단한 Shopping mall을 위한 도메인 설계 및 기능 구현

## Getting Started

### Prerequisites
* Gradle
* JDK 1.8

### Requirements
#### 회원<br>
 - 회원 등록<br>
 - 회원 조회<br>
#### 상품<br>
 - 상품 등록<br>
 - 상품 수정<br>
 - 상품 조회<br>
#### 주문<br>
 - 상품 주문<br>
 - 주문 내역 조회<br>
 - 주문 취소<br>
 
##### 단순화를 위해 스킵 된 기능은 강의 완료 후 개인적으로 추가할 예정
 
### Build
``` bash
# terminal
./gradlew bootRun

# IntelliJ
1. Sync gradle
2. Run Application

```

### Start

http://localhost:8080/<br>
입력시 최초 입력 화면 

## API Specifications
(추후 추가 예정)

## Dependencies

Dependence         |Version
-------------------|-------
spring-boot       |2.1.9.RELEASE
spring-boot-starter-data-jpa |
spring-boot-starter-thymeleaf |
spring-boot-starter-web |
spring-boot-devtools |
p6spy-spring-boot-starter |1.5.6
spring-boot-starter-test |
com.h2database:h2 |
org.projectlombok:lombok |
