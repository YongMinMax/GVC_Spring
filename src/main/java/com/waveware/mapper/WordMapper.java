package com.waveware.mapper;

import com.waveware.dto.CodeDTO;
import com.waveware.dto.WordDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordMapper
{
	public List<WordDTO> selectCountryWord(Integer index);
	public List<WordDTO> selectAllWord(Integer index);
	public Integer selectWordIndex(String word,Integer type);

//	public List<CodeDTO> selectPartKSICList(String code,int length);


}

