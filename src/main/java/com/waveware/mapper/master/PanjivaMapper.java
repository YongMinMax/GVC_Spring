package com.waveware.mapper.master;

import com.waveware.dto.master.CodeDTO;
import com.waveware.dto.master.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface PanjivaMapper
{
	public List<CodeDTO> selectIndustryMITList(String classify);
	public List<CodeDTO> selectIndustryMIT2HSList(String code);
	public List<CodeDTO> selectHSList(Set<String> codes);
}
