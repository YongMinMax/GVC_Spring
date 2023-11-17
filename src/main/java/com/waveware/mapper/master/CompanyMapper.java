package com.waveware.mapper.master;

import com.waveware.dto.master.CompanyDTO;
import com.waveware.dto.master.SentimentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper
{
	
	public List<CompanyDTO> getCompanyList(String category);


}
