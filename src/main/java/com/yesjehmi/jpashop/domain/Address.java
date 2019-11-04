package com.yesjehmi.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;

    private String street;

    private String zipcode;

    //생성자 만들면 기본생성자 필요하기 때문에 생성하되 JPA스펙 상 public이나 protected로 열어둬야하기 때문에 최소단위인 protected로 셋팅.
    //JPA가 기본생성자 등의 제약을 두는 이유는 JPA구현 라이브러리가 객체를 생성할 대 리플렉션 같은 기술을 사용할 수 있도록 지원해야 하기 때문.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
