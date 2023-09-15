package com.waveware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waveware.dto.UserDTO;
import com.waveware.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private  UserMapper userMapper;
	
	
	public List<UserDTO> userList(){	
		
		List<UserDTO> userList = userMapper.userList();
		return userList;
	}	
}
