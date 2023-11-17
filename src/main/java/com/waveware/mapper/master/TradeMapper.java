package com.waveware.mapper.master;

import com.waveware.dto.master.TrandeHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TradeMapper
{
	public List<TrandeHistoryDTO> selectTradeList(String country);

	public List<TrandeHistoryDTO> selectIndustryTradeList(String industry);

	public List<TrandeHistoryDTO> selectIndustryCountryRatio(String country);

	public List<TrandeHistoryDTO> selectIndustryRiskProductList(String classify, String date, int limit);
}

