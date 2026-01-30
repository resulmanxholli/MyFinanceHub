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
        model.addAttribute("selectedType", null);
        return "transactions/list";
    }

    @GetMapping("/new")
    public String newTransaction(
            @RequestParam(required = false) CategoryType type,
            Model model) {

        model.addAttribute("transaction", new TransactionDto());
        model.addAttribute("paymentMethods", PaymentMethod.values());

        if (type == CategoryType.INCOME) {
            model.addAttribute("categories",
                    categoriesService.findByType(CategoryType.INCOME));
            model.addAttribute("types", CategoryType.INCOME);
        } else {
            model.addAttribute("categories",
                    categoriesService.findByType(CategoryType.EXPENSE));
            model.addAttribute("types", CategoryType.EXPENSE);
        }

        return "transactions/new";
    }


    @PostMapping("/new")
    public String createTransaction(
            @RequestParam(required = false) CategoryType type,
            @Valid @ModelAttribute("transaction") TransactionDto dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute("paymentMethods", PaymentMethod.values());

            if (type == CategoryType.INCOME) {
                model.addAttribute("categories",
                        categoriesService.findByType(CategoryType.INCOME));
                model.addAttribute("types", CategoryType.INCOME);
            } else {
                model.addAttribute("categories",
                        categoriesService.findByType(CategoryType.EXPENSE));
                model.addAttribute("types", CategoryType.EXPENSE);
            }

            return "transactions/new";
        }

        transcationService.add(dto);
        return "redirect:/transactions/" + type.name();
    }


    @GetMapping("/{type}")
    public String listByType(@PathVariable CategoryType type, Model model) {
        model.addAttribute("transactions", transcationService.findByType(type));
        model.addAttribute("types", type.name());
        return "transactions/list";
    }

    @GetMapping("{id}/edit")
    public String editTransaction(Model model, @PathVariable Long id) {
        TransactionDto transaction = transcationService.findOne(id);

        model.addAttribute("transaction", transaction);
        model.addAttribute("types", transaction.getType());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        model.addAttribute("categories", categoriesService.findByType(transaction.getType()));
        return "transactions/edit";
    }

    @PostMapping("/{id}/edit")
    public String postTransactionEdit(@PathVariable long id,
                                      @Valid @ModelAttribute("transaction") TransactionDto dto,
                                      BindingResult bindingResult,
                                      Model model) {

        if (dto.getId() != id) {
            dto.setId(id);
            bindingResult.rejectValue("id", "transaction.id", "Id doesn't match");
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("transaction", dto);

            // vetëm një type
            model.addAttribute("types", dto.getType());

            model.addAttribute("paymentMethods", PaymentMethod.values());
            model.addAttribute("categories",
                    categoriesService.findByType(dto.getType()));

            return "transactions/edit";
        }

        transcationService.modify(id, dto);

        // redirect sipas type
        return "redirect:/transactions/" + dto.getType().name();
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
