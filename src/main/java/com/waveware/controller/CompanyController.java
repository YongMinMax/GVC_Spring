package com.waveware.controller;

import com.waveware.dto.master.CompanyDTO;
import com.waveware.mapper.master.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController
{
	@Autowired
	private CompanyMapper mapper;

	@GetMapping("/company")
	public ResponseEntity<List<CompanyDTO>> getCompanyList(@RequestParam(required = true) String classify)
	{
		return ResponseEntity.ok(mapper.getCompanyList(classify));
	}
}
