package com.waveware.controller;


import com.waveware.dto.news.IndustryReportDTO;
import com.waveware.mapper.news.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReportController
{
	@Autowired
	ReportMapper mapper;

	@GetMapping("report")
	public ResponseEntity<List<IndustryReportDTO>> getNewsForDate(@RequestParam(required = true) @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date)
	{
		String strDate = String.format("%s%02d%02d", date.getYear(), date.getMonthValue(),date.getDayOfMonth());
		return ResponseEntity.ok(mapper.getReportForDate(strDate));
	}
}
