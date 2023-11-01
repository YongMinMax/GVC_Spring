package com.waveware.mapper;

import com.waveware.dto.CountryDTO;
import com.waveware.dto.TrandeHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeMapper
{
	public List<CountryDTO> selectCountryList();
	public List<TrandeHistoryDTO> selectTradeList(String country);
}

