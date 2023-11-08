package com.waveware.controller;

import com.waveware.dto.CodeDTO;
import com.waveware.mapper.CodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CodeController
{

	@Autowired
	private CodeMapper mapper;



	boolean isNotIncludeCode(String code){

		switch (code){
			case "hs" :
			case "mti" :
			case "sitc" :
			case "icio" :
			case "isic" :
				return false;
			default: return true;
		}
	}

	@GetMapping("/code/list/part/{sourceSymbol}/{targetSymbol}")
	public ResponseEntity<List<CodeDTO>> getCodeList(@PathVariable(name = "sourceSymbol",required = true) String sourceSymbol,@RequestParam(required = false) String sourceCode,@PathVariable(name = "targetSymbol",required = true) String targetSymbol, @RequestParam(required = true)String targetCode){
		targetSymbol = targetSymbol.toLowerCase();
		sourceSymbol = sourceSymbol.toLowerCase();
		if(sourceSymbol.equals(targetSymbol) || isNotIncludeCode(sourceSymbol) || isNotIncludeCode(targetSymbol)){
			return ResponseEntity.badRequest().build();
		}
		int sourceLength = sourceCode != null ? sourceCode.length() : 0;
		int targetLength = targetCode.length();

		List<CodeDTO> list = null;
		switch (sourceSymbol){
			case "hs" :
				list = mapper.selectPartHSList(sourceLength,sourceCode,targetSymbol,targetLength,targetCode);
				break;
			case "mti":
				list = mapper.selectPartMTIList(sourceLength,sourceCode,targetSymbol,targetLength,targetCode);
				break;
			case "sitc":
				list = mapper.selectPartSITCList(sourceLength,sourceCode,targetSymbol,targetLength,targetCode);
				break;
			case "icio":
				list = mapper.selectPartICIOList(sourceLength,sourceCode,targetSymbol,targetLength,targetCode);
				break;
			case "isic":
				list = mapper.selectPartISICList(sourceLength,sourceCode,targetSymbol,targetLength,targetCode);
				break;
			default: list = new ArrayList<>();
		}


		return ResponseEntity.ok(list);
	}
	@GetMapping("/code/list/all/{symbol}")
	public ResponseEntity<List<CodeDTO>> getCodeAllList(@PathVariable(name = "symbol",required = true) String symbol, @RequestParam(required = false,name = "code")String code)
	{
		List<CodeDTO> list = null;
		symbol = symbol.toLowerCase();
		 int length = code == null ? 0 : code.length();
		switch (symbol){
			case "hs":
				list = mapper.selectAllHSList(code,length);
				break;
			case "mti":
				list = mapper.selectAllMTIList(code,length);
				break;
			 case "sitc":
				list = mapper.selectAllSITCList(code,length);
				break;
			 case "icio":
				list = mapper.selectAllICIOList(code,length);
				break;
			 case "isic":
				list = mapper.selectAllISICList(code,length);
				break;
			 case "ksic":
				list = mapper.selectAllKSICList(code,length);
				break;
			default:
				return ResponseEntity.badRequest().build();

		}
		return ResponseEntity.ok(list);
	}
}
