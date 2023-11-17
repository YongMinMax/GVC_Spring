package com.waveware.controller;

import com.waveware.dto.master.CompanyDTO;
import com.waveware.dto.master.TrandeHistoryDTO;
import com.waveware.mapper.master.TradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TradeController
{
	@Autowired
	TradeMapper mapper;




	@GetMapping("/trade/history")
	public ResponseEntity<List<TrandeHistoryDTO>> getTradeInfoList(@RequestParam(name = "country",required = true) String country)
	{

		 List<TrandeHistoryDTO> list =mapper.selectTradeList(country);

		return ResponseEntity.ok(list);
	}
	@GetMapping("/trade/industry/ratio")
	public ResponseEntity<List<TrandeHistoryDTO>> getIndustryCountryRatio(@RequestParam(name = "country",required = true) String country)
	{
		 List<TrandeHistoryDTO> list =mapper.selectIndustryCountryRatio(country);

		return ResponseEntity.ok(list);
	}
	@GetMapping("/trade/industry")
	public ResponseEntity<List<TrandeHistoryDTO>> getTradeIndustryList(@RequestParam(name = "industry",required = true) String industry)
	{

		 List<TrandeHistoryDTO> lit =mapper.selectIndustryTradeList(industry);

		return ResponseEntity.ok(lit);
	}


	@GetMapping("/trade/risk")
	public ResponseEntity<List<TrandeHistoryDTO>> getCompanyList(@RequestParam(required = true) String classify,@RequestParam(required = true) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date,@RequestParam(required = false, defaultValue = "10")int limit)
	{
		String strDate = String.format("%s%02d%02d", date.getYear(), date.getMonthValue(),date.getDayOfMonth());
		return ResponseEntity.ok(mapper.selectIndustryRiskProductList(classify,strDate,limit));
	}
}
