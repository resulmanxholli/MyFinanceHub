package com.example.myfinancehub.controllers;

import com.example.myfinancehub.dtos.CategoryDto;
import com.example.myfinancehub.dtos.TransactionDto;
import com.example.myfinancehub.enums.CategoryType;
import com.example.myfinancehub.enums.PaymentMethod;
import com.example.myfinancehub.services.CategoriesService;
import com.example.myfinancehub.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transcationService;
    private final CategoriesService categoriesService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("transactions", transcationService.findAll());
        return "transactions/list";
    }

    @GetMapping("/new")
    public String newTransaction(Model model) {
        model.addAttribute("transaction", new TransactionDto());
        model.addAttribute("types", CategoryType.values());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("categories", categoriesService.findAll());
        return "transactions/new";
    }

    @PostMapping("/new")
    public String createTransaction(@Valid @ModelAttribute TransactionDto dto, BindingResult result ,Model model){
        if(result.hasErrors()){
            model.addAttribute("types", CategoryType.values());
            model.addAttribute("paymentMethods", PaymentMethod.values());
            model.addAttribute("categories", categoriesService.findAll());
            return "transactions/new";
        }
        transcationService.add(dto);
        return "redirect:/transactions";
    }

    @GetMapping("/type/{type}")
    public String listByType(@PathVariable CategoryType type, Model model) {
        model.addAttribute("transactions", transcationService.findByType(type));
        model.addAttribute("types", type.name());
        return "transactions/list";
    }

    @GetMapping("{id}/edit")
    public String editTransaction(Model model, @PathVariable Long id) {
        model.addAttribute("transaction", transcationService.findOne(id));
        model.addAttribute("types", CategoryType.values());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("categories", categoriesService.findAll());
        return "transactions/edit";
    }

    @PostMapping("/{id}/edit")
    public String postCarEdit(@PathVariable long id, @Valid @ModelAttribute TransactionDto dto,
                              BindingResult bindingResult, Model model) {
        if (dto.getId() != id) {
            dto.setId(id);
            bindingResult.rejectValue("id", "transaction.id", "Id doesn't match");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("type", CategoryType.values());
            model.addAttribute("paymentMethods", PaymentMethod.values());
            model.addAttribute("categories", categoriesService.findAll());
            return "transactions/edit";
        }

        transcationService.modify(id, dto);
        return "redirect:/transactions";
    }

    @GetMapping("/{id}/view")
    public String viewTransaction(Model model, @PathVariable Long id) {
        model.addAttribute("transaction", transcationService.findOne(id));
        return "transactions/view";
    }


    @GetMapping("{id}/delete")
    public String deleteTransaction(@PathVariable Long id) {
        transcationService.remove(id);
        return "redirect:/transactions";
    }




}
