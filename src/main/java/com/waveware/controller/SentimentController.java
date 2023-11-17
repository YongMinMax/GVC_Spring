package com.waveware.controller;


import com.waveware.dto.master.CompanyDTO;
import com.waveware.mapper.master.SentimentMapper;
import com.waveware.dto.master.SentimentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class SentimentController
{

	@Autowired
	SentimentMapper mapper;

	@GetMapping("/sentiment/day/list")
	public ResponseEntity<List<SentimentDTO>> getDayList(@RequestParam(required = true) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date)
	{
		return ResponseEntity.ok(mapper.selectDayList(date.toString()));
	}

	@GetMapping("/sentiment/day/rank")
	public ResponseEntity<List<SentimentDTO>> getDayRank(@RequestParam(required = true) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date, @RequestParam(required = false, defaultValue = "true")boolean isDesc ,@RequestParam(required = false,defaultValue = "4") int limit)
	{
		return ResponseEntity.ok(mapper.selectDayRank(date.toString(),isDesc,limit));
	}


	@GetMapping("/sentiment/country")
	public ResponseEntity<List<SentimentDTO>> getCountrySentiment(@RequestParam(required = true) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date, @RequestParam(required = true) String country, @RequestParam(required = false, defaultValue = "30") int num)
	{
		return ResponseEntity.ok(mapper.selectDayCountry(date.toString(), num, country));
	}




}
