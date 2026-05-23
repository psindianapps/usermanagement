package com.example.UserManagement.config;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

@ControllerAdvice
public class TrimStringAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        // true => empty string becomes null
        StringTrimmerEditor stringTrimmerEditor =
                new StringTrimmerEditor(true);

        binder.registerCustomEditor(
                String.class,
                stringTrimmerEditor
        );
    }
}