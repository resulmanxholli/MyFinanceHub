package com.example.myfinancehub.controllers;

import com.example.myfinancehub.dtos.CategoryDto;
import com.example.myfinancehub.enums.TransactionType;
import com.example.myfinancehub.services.CategoryService;
import com.example.myfinancehub.services.impls.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class HomeController {
    private final CategoryService categoryService;

//    @GetMapping
//    public String home() {
//        return "home";
//    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        model.addAttribute("category", new CategoryDto());
        model.addAttribute("type", TransactionType.values());
        return "categories/new";
    }

    @PostMapping("/new")
    public String createCategory(@Valid @ModelAttribute CategoryDto dto , BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("type", TransactionType.values());
            return "categories/new";
        }
        categoryService.add(dto);
        return "redirect:/categories";
    }

}
