package com.yesjehmi.jpashop.controller;

import com.yesjehmi.jpashop.domain.Member;
import com.yesjehmi.jpashop.domain.Order;
import com.yesjehmi.jpashop.domain.item.Item;
import com.yesjehmi.jpashop.repository.OrderSearch;
import com.yesjehmi.jpashop.service.ItemService;
import com.yesjehmi.jpashop.service.MemberService;
import com.yesjehmi.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "orders/orderForm";
    }

    @PostMapping("order")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count) {

        orderService.order(memberId,itemId,count);
        return "redirect:/orders";
    }

    @GetMapping("orders")
    public String list(Model model, @ModelAttribute("orderSearch") OrderSearch orderSearch) {

        List<Order> orders = orderService.findOrders(orderSearch);

        model.addAttribute("orders", orders);

        return "orders/orderList";
    }

    @PostMapping("orders/{orderId}/cancle")
    public String cancleOrder(@PathVariable Long orderId) {

        orderService.cancleOrder(orderId);

        return "redirect:/orders";
    }
}
