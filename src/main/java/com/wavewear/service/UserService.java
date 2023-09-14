package com.wavewear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wavewear.dto.UserDTO;
import com.wavewear.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private  UserMapper userMapper;
	
	
	public List<UserDTO> userList(){	
		
		List<UserDTO> userList = userMapper.userList();
		return userList;
	}	
}
