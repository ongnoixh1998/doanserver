package com.vanhoang.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.baokim.BaoKimAPI;
import com.vanhoang.demo.baokim.model.responses.BaoKimOrderResponse;
import com.vanhoang.demo.baokim.model.responses.BpmBankResponse;
import com.vanhoang.demo.config.WebConstants;
import com.vanhoang.demo.entity.OrderEntity;
import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.exceptions.authentication.JWTException;
import com.vanhoang.demo.repository.OrderRepository;
import com.vanhoang.demo.repository.ProviderRepository;
import com.vanhoang.demo.security.JwtToken;
import com.vanhoang.demo.security.TokenModel;
import com.vanhoang.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PaymentController {
    @Autowired
    BaoKimAPI baoKimAPI;
    @Autowired
    JwtToken jwtToken;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    OrderService orderService;
    @GetMapping("/payment")
    ModelAndView providerRegister(@RequestParam("token") String token)  {
        ModelAndView view = new ModelAndView("index");
        try {
            TokenModel tokenModel = jwtToken.decode(token);
            Integer orderId = (Integer) tokenModel.getPayload().get("orderId");
            ProviderEntity providerEntity = providerRepository.findByClientId(String.valueOf(tokenModel.getPayload().get("user")));
            jwtToken.verifyJWT(token,providerEntity.getKeyAPI());
            BpmBankResponse bpmBankList = baoKimAPI.getBpmBankList();
            OrderEntity orderEntity = orderService.findById(orderId);
            view.addObject("order",orderEntity);
            view.addObject("token",token);
            view.addObject("bpm",bpmBankList.getData());
            view.addObject("success",true);
        } catch (JWTException jwtException) {
            view.addObject("success",false);
        } catch (JsonProcessingException e) {
            view.addObject("success",false);
        } catch (NoSuchAlgorithmException e) {
            view.addObject("success",false);
        } catch (InvalidKeyException e) {
            view.addObject("success",false);
        }
        return view;
    }
    @GetMapping("/payment/submit")
    RedirectView submit(@RequestParam("token") String token,
                        @RequestParam("description") String description,
                        @RequestParam("email") String email,
                        @RequestParam("phoneNumber") String phoneNumber,
                        @RequestParam("bpm_id") Integer bpm_id) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        BaoKimOrderResponse baoKimOrderResponse = orderService.submitBaokim(token,description,email,phoneNumber,bpm_id);
        if (baoKimOrderResponse.getCode().equals(0)){
            String urlRedicrect = baoKimOrderResponse.getData().getPayment_url().replace("/payment/","/payment/by-atm-card").replace("oid","order_id")+"&bpm_id="+bpm_id;
            return new RedirectView(urlRedicrect);
        }else {
            return new RedirectView("/error");
        }
    }
    @GetMapping("/payment/success/{token}/")
    ModelAndView paymentSuccess(@PathVariable("token") String token){
        ModelAndView view = new ModelAndView("error");
        view.addObject("success",true);
        try {
            jwtToken.verifyJWT(token, WebConstants.KEY_TOKEN);
            TokenModel tokenModel = jwtToken.decode(token);
            Integer orderId = (Integer) tokenModel.getPayload().get("orderId");
            orderService.changeStatus(orderId,"success");
            view.setViewName("redirect:"+orderService.getReturnSuccess(orderId));
        } catch (JWTException jwtException) {
            view.addObject("success",false);
        } catch (InvalidKeyException e) {
            view.addObject("success",false);
        } catch (NoSuchAlgorithmException e) {
            view.addObject("success",false);
        } catch (JsonProcessingException e) {
            view.addObject("success",false);
        }
        return view;
    }
    @GetMapping("/payment/error/{token}")
    ModelAndView paymentError(@PathVariable("token") String token){
        ModelAndView view = new ModelAndView("error");
        view.addObject("success",true);
        try {
            jwtToken.verifyJWT(token, WebConstants.KEY_TOKEN);
            TokenModel tokenModel = jwtToken.decode(token);
            Integer orderId = (Integer) tokenModel.getPayload().get("orderId");
            orderService.changeStatus(orderId,"cancel");
            view.setViewName("redirect:"+orderService.getReturnError(orderId));
        } catch (JWTException jwtException) {
            view.addObject("success",false);
        } catch (InvalidKeyException e) {
            view.addObject("success",false);
        } catch (NoSuchAlgorithmException e) {
            view.addObject("success",false);
        } catch (JsonProcessingException e) {
            view.addObject("success",false);
        }
        return view;
    }
    @PostMapping("/payment/ipn")
    ResponseEntity<Map> paymentIPN(){
        Map map = new HashMap();
        return ResponseEntity.ok(map);
    }

}
