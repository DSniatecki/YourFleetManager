package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.services.CompanyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"/","/list"})
    public String showAll(@RequestParam("page") Optional<Integer> page, Model model ){
        int currentPage = page.orElse(1);
        Page<Company> companyPage = companyService.getAllPageable(PageRequest.of(currentPage - 1, PAGE_SIZE));
        int totalPages = companyPage.getTotalPages();
        model.addAttribute("companiesPage", companyPage);

        if (totalPages > 0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for(int i=1; i<=totalPages; i++){
                pageNumbers.add(i);
            }
            model.addAttribute("prevCompaniesNumber", (currentPage-1)*PAGE_SIZE );
            model.addAttribute("pageNumbers", pageNumbers);
        }
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
    public String save(@ModelAttribute Company company, Model model){
        model.addAttribute("company", companyService.save(company));
        return "company/company-details";
    }
}
