package com.wavewear.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wavewear.dto.UserDTO;
import com.wavewear.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@GetMapping("/userList")
	public List<UserDTO> userList() {
		List<UserDTO> userList = service.userList();
		System.out.println(userList);
		
		return service.userList();
	}
	
	@GetMapping("/userList/insert")
	public void userInsert(@RequestParam String path) {
		System.out.println(path);
		
	}
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // 저장할 경로 설정 (경로는 적절하게 수정)
                String filePath = "C:/uploads/";
                String fileName = file.getOriginalFilename();
                File dest = new File(filePath + fileName);
                file.transferTo(dest);

                return "File uploaded successfully.";
            } catch (IOException e) {
                e.printStackTrace();
                return "File upload failed.";
            }
        } else {
            return "No file selected.";
        }
    }
}