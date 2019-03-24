package com.dsniatecki.yourfleetmanager.controllers;


import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(Exception exception, Model model){
        log.info("NotFoundException( " + exception.getMessage() + " )  was properly handled");
        model.addAttribute("exceptionMessage", exception.getMessage());
        return "error/404error";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(Exception exception, Model model){
        log.info("handleNumberFormatException( " + exception.getMessage() + " )  was properly handled");
        model.addAttribute("exceptionMessage", exception.getMessage());
        return "error/400error";
    }

}
