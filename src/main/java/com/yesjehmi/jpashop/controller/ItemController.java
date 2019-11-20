package com.yesjehmi.jpashop.controller;

import com.yesjehmi.jpashop.domain.item.Book;
import com.yesjehmi.jpashop.domain.item.Item;
import com.yesjehmi.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("new")
    public String createForm(Model model) {

        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("new")
    public String create(BookForm form) {

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/";
    }

    @GetMapping
    public String list(Model model) {

        List<Item> items = itemService.findItems();

        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("{id}/edit")
    public String updateForm(@PathVariable Long id, Model model) {

        Book item = (Book)itemService.findOne(id);

        BookForm form = new BookForm();
        form.setAuthor(item.getAuthor());
        form.setId(item.getId());
        form.setIsbn(item.getIsbn());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("{id}/edit")
    public String update(@ModelAttribute("form") BookForm book) {

        Book item = new Book();
        item.setIsbn(book.getIsbn());
        item.setAuthor(book.getAuthor());
        item.setStockQuantity(book.getStockQuantity());
        item.setPrice(book.getPrice());
        item.setName(book.getName());
        item.setId(book.getId());

        itemService.saveItem(item);

        return "redirect:/items";
    }
}
