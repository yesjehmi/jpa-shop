package com.yesjehmi.jpashop.service;

import com.yesjehmi.jpashop.domain.Delivery;
import com.yesjehmi.jpashop.domain.Member;
import com.yesjehmi.jpashop.domain.Order;
import com.yesjehmi.jpashop.domain.OrderItem;
import com.yesjehmi.jpashop.domain.item.Item;
import com.yesjehmi.jpashop.repository.ItemRepository;
import com.yesjehmi.jpashop.repository.MemberRepository;
import com.yesjehmi.jpashop.repository.OrderRepository;
import com.yesjehmi.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order (Long mumberId, Long itemId, int count) {
        //entity 조회
        Member member = memberRepository.findOne(mumberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    //취소
    @Transactional
    public void cancleOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancle();
    }

    //검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
