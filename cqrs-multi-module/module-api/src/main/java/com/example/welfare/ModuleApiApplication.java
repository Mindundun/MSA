package com.example.welfare;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Bean은 @SpringBootApplication 클래스의 패키지를 기준으로 찾아서 가져옵니다.
// 즉, com.example.welfare 패키지에 있는 Bean만 찾을 수 있습니다.

// 실제로 가져와야 하는 Bean은 다른 패키지에 있습니다.
// JPA    : com.example.member
// MyBatis: com.example.point

// 따라서, 상위 패키지 com.example 이용해 Bean을 찾을 패키지 위치를 바꿔 줘야 합니다. (스캔 명시)

// 1. 일반 빈(Service, Component) 스캔 범위 확장
@ComponentScan(basePackages = {"com.example"}) 

// 2. JPA 엔티티(@Entity) 스캔 범위 확장 (Member 엔티티 찾기 위해)
@EntityScan(basePackages = {"com.example"})

// 3. JPA 리포지토리(Repository) 스캔 범위 확장 (MemberRepository 찾기 위해) 
@EnableJpaRepositories(basePackages = {"com.example"})

// 4. MyBatis 설정 (Mapper 찾기 위해)
@MapperScan(basePackages = {"com.example.point.mapper"}, annotationClass = Mapper.class)

@SpringBootApplication
public class ModuleApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(ModuleApiApplication.class, args);
  }
}