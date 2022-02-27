package com.example.tx.controller;

import com.example.tx.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @date 2022/02/27 20:51
 **/
@RestController
public class HelloController {

    @Autowired
    MsgService msgService;

    @GetMapping("/send")
    public void hello(){
        msgService.send();
    }
}