package com.waveware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waveware.dto.UserDTO;
import com.waveware.service.UserService;

@RestController
public class HelloController {

	@Autowired
	UserService service;
	
    @GetMapping("/api/test")
    public String Hello(){
        return "스프링 api test1";
    }
    @GetMapping("/api/test2")
    public List<UserDTO> Hello2(){
    	System.out.println(service.userList());
    	
        return service.userList();
    }
}
