package com.codegym.c0624g1repository.controller;

import com.codegym.c0624g1repository.model.Province;
import com.codegym.c0624g1repository.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @GetMapping("")
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("/province/list");
        Iterable<Province> provinces = provinceService.findAll();
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }
}
