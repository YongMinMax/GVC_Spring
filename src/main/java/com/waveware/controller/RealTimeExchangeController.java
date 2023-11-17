package com.waveware.controller;

import com.waveware.dto.master.StockDTO;
import com.waveware.mapper.master.StockMapper;
import com.waveware.service.RealTimeExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class RealTimeExchangeController
{
	@Autowired
	RealTimeExchangeService service;
	@Autowired
	StockMapper mapper;

	@GetMapping("/realtime")
	public ResponseEntity<Map<String,Double>> getRealTimeData(){
		return ResponseEntity.ok(service.getExchangeMap());
	}
	@GetMapping("/realtime/{prefix}/{symbol}")
	public ResponseEntity<StockDTO> getRealTimePrevData(@PathVariable("prefix") String prefix, @PathVariable("symbol") String symbol, @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date){
		String fullSymbol = String.format("%s_%s", prefix, symbol);

		return ResponseEntity.ok(mapper.selectPrevStock(fullSymbol,date.toString()));
	}
}
