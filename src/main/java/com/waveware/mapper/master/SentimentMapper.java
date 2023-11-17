package com.waveware.mapper.master;

import com.waveware.dto.master.SentimentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SentimentMapper
{
	
	public List<SentimentDTO> selectDayList(String date);
	public List<SentimentDTO> selectDayCountry(String date, int num, String country);
	public List<SentimentDTO> selectDayRank(String date,boolean isDesc,int limit);


}
