package com.vanhoang.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.baokim.BaoKimAPI;
import com.vanhoang.demo.baokim.model.responses.BankListResponse;
import com.vanhoang.demo.baokim.model.responses.BpmBankResponse;
import com.vanhoang.demo.request.ProviderRequest;
import com.vanhoang.demo.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProviderController {
    @Autowired
    ProviderService providerService;
   @GetMapping("/register")
    ModelAndView registerView(){
       ModelAndView view = new ModelAndView("register");
       return view;
   }
    @PostMapping("/register")
    ModelAndView register(@ModelAttribute ProviderRequest providerRequest){
        ModelAndView view = new ModelAndView("register");
        if (providerService.register(providerRequest)!=null){
            view.addObject("status",true);
        }else {
            view.addObject("status",false);
        }
        return view;
    }
}
