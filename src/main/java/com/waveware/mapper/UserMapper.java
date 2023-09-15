package com.waveware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.waveware.dto.UserDTO;

@Mapper
public interface UserMapper {
	public List<UserDTO> userList();
	public String getCount();
}
