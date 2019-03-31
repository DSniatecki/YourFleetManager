package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.entities.Company;
import com.dsniatecki.yourfleetmanager.dto.CompanyDTO;
import com.dsniatecki.yourfleetmanager.services.CompanyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/company")
class CompanyController {

    private final CompanyService companyService;

    @Value("${companyList.page.size}")
    private int PAGE_SIZE;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
        PAGE_SIZE = 5;
    }

    @GetMapping("/")
    public String showPage(@RequestParam("page") Optional<Integer> page, Model model ){
        int currentPage = page.orElse(1);
        Page<CompanyDTO> companyPage = companyService.getAllPageable(PageRequest.of(currentPage - 1, PAGE_SIZE));
        model.addAttribute("companiesPage", companyPage);
        model.addAttribute("prevCompaniesNumber", (currentPage-1)*PAGE_SIZE );
        return "company/company-list-page";
    }


    @GetMapping("/all")
    public String showAll(Model model){
        model.addAttribute("companies", companyService.getAll());
        return "company/company-list";
    }

    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable String id, Model model){
        model.addAttribute("company", companyService.getById(Long.parseLong(id)));
        return "company/company-details";
    }

    @GetMapping("/{id}/department")
    public String showCompanyDepartmentList(@PathVariable String id, Model model){
        model.addAttribute("company", companyService.getById(Long.valueOf(id)));
        return "company/company-department-list";
    }

    @GetMapping("/add")
    public String addNew(Model model){
        model.addAttribute("company", new Company());
        return "company/company-form";
    }


    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id){
        companyService.deleteById(Long.parseLong(id));
        return "redirect:/company/";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable String id, Model model){
        model.addAttribute("company", companyService.getById(Long.parseLong(id)) );
        return "company/company-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("company") CompanyDTO companyDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "company/company-form";
        }
        model.addAttribute("company", companyService.save(companyDTO));
        return "company/company-details";
    }
}
