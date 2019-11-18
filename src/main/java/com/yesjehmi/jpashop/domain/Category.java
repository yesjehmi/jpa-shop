package com.yesjehmi.jpashop.domain;

import com.yesjehmi.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name="category_id"),
            //중간테이블에 있는 카테고리 ID
            inverseJoinColumns = @JoinColumn(name="item_id"))
            //이 테이블의 item쪽에 들어가는 항목
    //다대다 관계의 경우 중간테이블을 조인하여 맵핑해주어야한다. 실무에서 거의 사용하지 않는다.
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="parant_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //연관관계 메서드
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
