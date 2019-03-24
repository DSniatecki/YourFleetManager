package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.domains.Department;
import com.dsniatecki.yourfleetmanager.services.DepartmentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping("/company/{companyId}/department")
class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping("/add")
    public String addNew(@PathVariable String companyId, Model model){
        model.addAttribute("department", new Department());
        model.addAttribute("companyId", Long.valueOf(companyId));
        return "department/department-form";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable String companyId, @PathVariable String id, Model model){
        model.addAttribute("department", departmentService.getById(Long.valueOf(id)));
        model.addAttribute("companyId", Long.valueOf(companyId));
        return "department/department-form";
    }

    @PostMapping("/save")
    public String save(@PathVariable String companyId, @ModelAttribute Department department, Model model){
        departmentService.save(department, Long.valueOf(companyId));
        model.addAttribute("companyId", Long.valueOf(companyId));
        return "department/department-details";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id){
        departmentService.deleteById(Long.valueOf(id));
        return "redirect:/company/{companyId}/department";
    }

    @GetMapping("/{id}/car")
    public String showCars(@PathVariable String companyId, @PathVariable String id, Model model){
        model.addAttribute("department", departmentService.getById(Long.valueOf(id)));
        model.addAttribute("companyId", Long.valueOf(companyId));
        return "department/department-car-list";
    }


}
