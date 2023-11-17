package com.waveware.mapper.master;

import com.waveware.dto.master.StockDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper
{
	public List<StockDTO> selectStock(String symbol, String startTime, String endTime);
	public List<StockDTO> selectMonthOfAllStock(String date);
	public List<StockDTO> selectMonthOfStock(String date);
	public StockDTO selectPrevStock(String symbol ,String date);
}
