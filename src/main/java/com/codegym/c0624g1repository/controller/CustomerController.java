package com.codegym.c0624g1repository.controller;

import com.codegym.c0624g1repository.exception.DuplicateEmailException;
import com.codegym.c0624g1repository.model.Customer;
import com.codegym.c0624g1repository.model.Province;
import com.codegym.c0624g1repository.service.ICustomerService;
import com.codegym.c0624g1repository.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("counter")
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> listProvinces() {
        return provinceService.findAll();
    }


    @GetMapping("")
    public ModelAndView listCustomer() {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        try {
            Iterable<Customer> customers = customerService.findAllCustomer();
            modelAndView.addObject("customers", customers);
            return modelAndView;
        }catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/customer/inputs-not-acceptable");
    }

    @PostMapping("/save")
    public ModelAndView saveCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) throws DuplicateEmailException {

            if (bindingResult.hasFieldErrors()) {
                ModelAndView modelAndView = new ModelAndView("/customer/create");
                return modelAndView;
            }
            //customerService.save(customer);
            customerService.saveCustomer(customer);

            ModelAndView modelAndView = new ModelAndView("/customer/create");
            modelAndView.addObject("customer", new Customer());
            return modelAndView;
    }

    @GetMapping("/{firstName}")
    public String showCustomer(@PathVariable String firstName) {
        List<Customer> customers = customerService.findTop3ByFirstName(firstName);
        return "";
    }

    @GetMapping("/store/{_lastName}")
    public String showCustomerStore(@PathVariable String _lastName) {
        List<Customer> customers = customerService.findCustomerByLastName(_lastName);
        return "";
    }

    @ModelAttribute("userName")
    public String login(HttpServletRequest request, Model model) {
        // get all cookies
        Cookie[] cookies = request.getCookies();
        // iterate each cookie
        for (Cookie ck : cookies) {
            // display only the cookie with the name 'setUser'
            if (ck.getName().equals("usernameCookie")) {
                return ck.getValue();
            }
        }
        return "";
    }

    @GetMapping("/find/{id}")
    public ModelAndView showInformation(@PathVariable Long id) {
        try {
            ModelAndView modelAndView = new ModelAndView("/customer/info");
            Optional<Customer> customerOptional = customerService.findCustomerById(id);
            modelAndView.addObject("customer", customerOptional.get());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/customers");
        }
    }
}
