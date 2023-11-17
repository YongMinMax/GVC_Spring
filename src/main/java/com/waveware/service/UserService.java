package com.waveware.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waveware.dto.master.UserDTO;
import com.waveware.mapper.master.UserMapper;

@Service
public class UserService {

	@Autowired
	private  UserMapper userMapper;
	
	
	public List<UserDTO> userList(){	
		
		List<UserDTO> userList = userMapper.userList();
		return userList;
	}	
	public void delUser(int idx) {
		System.out.println("del servic "+ idx);
		userMapper.delUser(idx);
	}
	
	 public void addUser(Map<String, String> userData) {
	        // 현재 날짜를 가져옵니다.
	        Date currentDate = new Date();

	        // 날짜를 원하는 형식으로 포맷합니다. 예: "yyyy-MM-dd"
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String formattedDate = dateFormat.format(currentDate);
        	String role = userData.get("user_role");
        	if(role == "관리자") {
        		userData.put("user_role","1");
    		}else {
    			userData.put("user_role","2");
    		}
        	
	        userData.put("user_joindate", formattedDate);
	        userData.put("user_update", formattedDate);

	        userMapper.addUser(userData);
	    }
	
}
