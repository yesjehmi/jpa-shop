package com.yesjehmi.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;


    //연관관계의 주인이 orders.member라는 것을 알려줌. (읽기전용임을 명시)
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
