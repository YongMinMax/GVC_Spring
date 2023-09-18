package com.waveware.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waveware.dto.UserDTO;
import com.waveware.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userservice;
	
	@GetMapping("/userList")
	public List<UserDTO> userList() {
		List<UserDTO> userList = userservice.userList();		
		System.out.println("user List");
		return userservice.userList();
	}
	
	@GetMapping("/userList/delete")
	public String userDelet(@RequestParam("idx") int idx) {
		System.out.println("delete user");
		userservice.delUser(idx);
		return null;
	}
	
	
	 @PostMapping("/userList/add")
	    public void addUser(@RequestBody Map<String, String> userData) {
	        userservice.addUser(userData);
	
	}
	
//	@GetMapping("/userList/insert")
//	public void userInsert(@RequestParam String path) {
//		System.out.println(path);
//		
//	}
	

	
//	@PostMapping("/upload")
//	public String uploadFile(@RequestParam("file") MultipartFile file) {
//        if (file != null && !file.isEmpty()) {
//            try {
//                // 저장할 경로 설정 (경로는 적절하게 수정)
//                String filePath = "C:/uploads/";
//                String fileName = file.getOriginalFilename();
//                File dest = new File(filePath + fileName);
//                file.transferTo(dest);
//
//                return "File uploaded successfully.";
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "File upload failed.";
//            }
//        } else {
//            return "No file selected.";
//        }
//    }
}