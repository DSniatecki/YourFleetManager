package com.dsniatecki.yourfleetmanager.controllers;

import com.dsniatecki.yourfleetmanager.dto.CarDTO;
import com.dsniatecki.yourfleetmanager.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/company/{companyId}/department/{departmentId}/car")
class CarController {

    private final CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }
    @GetMapping("/{id}/details")
    public String showDetails(@PathVariable String companyId, @PathVariable String id, Model model){
        model.addAttribute("car", carService.getById(Long.valueOf(id)));
        model.addAttribute("companyId", Long.valueOf(companyId));
        return "car/car-details";
    }


    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable String id){
        carService.deleteById(Long.parseLong(id));
        return "redirect:/company/{companyId}/department/{departmentId}/car";
    }

    @GetMapping("/add")
    public String add(@PathVariable String companyId, @PathVariable String departmentId, Model model){
        model.addAttribute("car", new CarDTO());
        model.addAttribute("departmentId", Long.valueOf(departmentId));
        model.addAttribute("companyId", Long.valueOf(companyId));
        return "car/car-form";
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable String companyId,@PathVariable String departmentId, @PathVariable String id, Model model){
        model.addAttribute("car", carService.getById(Long.valueOf(id)));
        model.addAttribute("departmentId", Long.valueOf(departmentId));
        model.addAttribute("companyId", Long.valueOf(companyId));
        return "car/car-form";
    }

    @PostMapping("/save")
    public String save(@PathVariable String companyId, @PathVariable String departmentId,
                       @Valid @ModelAttribute("car") CarDTO carDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "car/car-form";
        }

        model.addAttribute("car", carService.saveWithDepartment(carDTO, Long.valueOf(departmentId)));
        model.addAttribute("companyId", Long.valueOf(companyId));
        return "car/car-details";
    }


}
