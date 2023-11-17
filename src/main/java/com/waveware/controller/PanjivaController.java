package com.waveware.controller;

import com.waveware.dto.master.CodeDTO;
import com.waveware.mapper.master.PanjivaMapper;
import com.waveware.service.PanjivaService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

@RestController
public class PanjivaController
{

	@Autowired
	PanjivaMapper mapper;

	@Autowired
	PanjivaService service;
	@GetMapping("/industry")
	public ResponseEntity<Object> getIndustry(@RequestParam(required = true) String classify,@RequestParam(required = false,defaultValue ="20") int topk) throws IOException, ParseException
	{
		return ResponseEntity.ok(service.getPanjivaCallIndustry(classify,topk));
	}

	@GetMapping("/industry/part")
	public ResponseEntity<Object> getIndustryPartList(@RequestParam(required = true) String mti ,@RequestParam(required = false,defaultValue ="20") int topk) throws IOException, ParseException
	{
		List<CodeDTO> codes = mapper.selectIndustryMIT2HSList(mti);

		JSONObject requestParams = new JSONObject();
		JSONArray mtiArr = new JSONArray();
		for(CodeDTO code : codes){
			mtiArr.add(code.getCode());
		}
		requestParams.put("mti",mtiArr);
		requestParams.put("topk",topk);

		return ResponseEntity.ok(service.getPanjivaCallMti(requestParams));
	}


	@GetMapping("/industry/list")
	public ResponseEntity getIndustryMITList(@RequestParam(required = true) String classify){

		switch (classify.toLowerCase()){
			case "car": case"steel":
			case "energy":
			case "semiconductor":
			case "chemistry":
			case "battery":
				break;
			default:
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{header : 400,contents:\"잘못된 요청입니다.(classify)\"}");
		}

		return ResponseEntity.ok( mapper.selectIndustryMITList(classify));
	}

}
