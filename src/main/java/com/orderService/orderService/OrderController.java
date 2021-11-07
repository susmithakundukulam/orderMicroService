package com.orderService.orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Value(("${service.welcome.message}"))
    private String message; 

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @RequestMapping("/init")
    public String getOrder(){
        return "Hello Order Service, " + message + " : "+ env.getProperty("local.server.port");
    }

    @RequestMapping("/orderFrmPayment")
    public String getOrderFromPayment(){
        
        String paymentStr = restTemplate.getForObject("http://ApiGateway/payment/init", String.class);
        return "Output from payment Service: " + paymentStr;
    }
}
