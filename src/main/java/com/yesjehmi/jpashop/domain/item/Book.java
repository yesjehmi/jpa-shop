package com.yesjehmi.jpashop.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
//@Setter(AccessLevel.PRIVATE)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item{

    private String author;

    private String isbn;

    /* TODO :: 추후 코드 리팩토링에 적용
    public static final Book createBookInstance() {
        Book book = new Book();
        return
    }
     */
}
