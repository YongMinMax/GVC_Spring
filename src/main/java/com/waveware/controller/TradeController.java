package com.waveware.controller;

import com.waveware.dto.CodeDTO;
import com.waveware.dto.CountryDTO;
import com.waveware.dto.TrandeHistoryDTO;
import com.waveware.mapper.TradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController
{
	@Autowired
	TradeMapper mapper;



	@GetMapping("/trade/list")
	public ResponseEntity<List<CountryDTO>> getTradeCountry()
	{
		return ResponseEntity.ok(mapper.selectCountryList());
	}
	//	GetMapping("trade")

	@GetMapping("/trade/history")
	public ResponseEntity<List<TrandeHistoryDTO>> getTradeInfoList(@RequestParam(name = "country",required = true) String country)
	{

		 List<TrandeHistoryDTO> lit =mapper.selectTradeList(country);

		return ResponseEntity.ok(lit);
	}
}
