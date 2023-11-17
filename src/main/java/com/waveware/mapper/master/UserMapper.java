package com.waveware.mapper.master;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.waveware.dto.master.UserDTO;

@Mapper
public interface UserMapper {
	
	public List<UserDTO> userList();
	
	public String getCount();
	
	public int delUser(int idx);
	
	public void addUser(Map<String, String> userData);
}
