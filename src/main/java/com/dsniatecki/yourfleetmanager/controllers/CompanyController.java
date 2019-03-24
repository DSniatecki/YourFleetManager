package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.services.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/company")
class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }

    @GetMapping({"/","/list"})
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
    public String save(@ModelAttribute Company company, Model model){
        model.addAttribute("company", companyService.save(company));
        return "company/company-details";
    }
}
