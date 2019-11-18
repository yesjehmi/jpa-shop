package com.yesjehmi.jpashop.service;

import com.yesjehmi.jpashop.domain.Address;
import com.yesjehmi.jpashop.domain.Member;
import com.yesjehmi.jpashop.domain.Order;
import com.yesjehmi.jpashop.domain.OrderStatus;
import com.yesjehmi.jpashop.domain.item.Book;
import com.yesjehmi.jpashop.exception.NotEnoughStockException;
import com.yesjehmi.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook("지금 JPA", 10000, "이혜진", 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10_000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 10-orderCount, book.getStockQuantity());
    }

    private Book createBook(String bookName, int price, String author, int stockQuantity) {
        Book book = new Book();
        book.setName(bookName);
        book.setPrice(price);
        book.setAuthor(author);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "감가", "123-123"));
        em.persist(member);
        return member;
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("지금 JPA", 10000, "이혜진", 10);

        int orderCount = book.getStockQuantity()+1;

        //when
        orderService.order(member.getId(), book.getId(), orderCount);

        //then
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Book item = createBook("test", 10000, "leehyejin", 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancleOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCLE이다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("취소된 상품은 그만큼 재고 상승해야함.", 10, item.getStockQuantity());
    }
}
