package com.waveware.mapper;

import com.waveware.dto.StockDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper
{
	public List<StockDTO> selectStock(String symbol, String startTime, String endTime);
	public List<StockDTO> selectMonthOfAllStock(String date);

}
