package com.example.myfinancehub.controllers;

import com.example.myfinancehub.dtos.CategoryDto;
import com.example.myfinancehub.enums.CategoryType;
import com.example.myfinancehub.services.CategoriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoriesService categoryService;


    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @GetMapping("/new")
    public String newCategory(Model model) {
        model.addAttribute("category", new CategoryDto());
        model.addAttribute("type", CategoryType.values());
        return "categories/new";
    }

    @PostMapping("/new")
    public String createCategory(@Valid @ModelAttribute CategoryDto dto , BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("type", CategoryType.values());
            return "categories/new";
        }
        categoryService.add(dto);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/view")
    public String getCarViewPage(Model model, @PathVariable Long id) {
        model.addAttribute("category", categoryService.findOne(id));
        return "categories/view";
    }

    @GetMapping("/{id}/edit")
    public String getCarEditPage(Model model, @PathVariable Long id) {
        model.addAttribute("category", categoryService.findOne(id));
        model.addAttribute("type", CategoryType.values());


        return "categories/edit";
    }

    @PostMapping("/{id}/edit")
    public String postCarEdit(@PathVariable long id, @Valid @ModelAttribute CategoryDto dto,
                              BindingResult bindingResult, Model model) {
        if (dto.getId() != id) {
            dto.setId(id);
            bindingResult.rejectValue("id", "cate.id", "Id doesn't match");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("type", CategoryType.values());
            return "categories/edit";
        }

        categoryService.modify(id, dto);
        return "redirect:/categories";
    }
    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.remove(id);
        return "redirect:/categories";
    }


    @PostMapping("/{id}/delete")
    public String deleteCar(@PathVariable long id) {
        categoryService.remove(id);
        return "redirect:/categories";
    }


}
