package com.waveware.controller;


import com.waveware.dto.master.WordDTO;
import com.waveware.mapper.master.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class WordController
{
	@Autowired
	WordMapper mapper;

	@GetMapping("/word/country")
	public ResponseEntity<Map<String,Object>> getCountryWord(@RequestParam(required = true) String word){
		Integer idx = mapper.selectWordIndex(word,1);
		if(idx == null) return ResponseEntity.badRequest().build();
		List<WordDTO> w = mapper.selectCountryWord(idx);

		AtomicInteger integer = new AtomicInteger();
		Map<String,Object> map = parse(w,integer);
		return ResponseEntity.ok(map);
	}
	@GetMapping("/word/all")
	public ResponseEntity<Map<String,Object>> getAllWord(@RequestParam(required = true) String word){
		Integer idx = mapper.selectWordIndex(word,0);
		if(idx == null) return ResponseEntity.badRequest().build();
		List<WordDTO> w =mapper.selectAllWord(idx);

		AtomicInteger integer = new AtomicInteger();
		Map<String,Object> map = parse(w,integer);
		return ResponseEntity.ok(map);
	}


	private Map<String, Object> parse(List<WordDTO> list, AtomicInteger idx){
		Map<String,Object> map = new HashMap<>();

		if(list== null || list.size() == 0){
			return map;
		}
		Map<String,Integer> nodes = new HashMap<>();
		List<WordDTO> linkes = new ArrayList<>();
		map.put("nodes",nodes);
		map.put("links",linkes);


		for(WordDTO word :list){
			nodes.computeIfAbsent(word.getWord2(), word2 -> (new Integer(idx.get())));
			linkes.add(word);
		}
		nodes.computeIfAbsent(list.get(0).getWord1(), word1 -> (new Integer(idx.getAndIncrement())));


		for(WordDTO word :list){
			Map<String,Object> childMap = parse( word.getChildrenList(),idx);
			Map<String,Integer> chNodes = (Map<String, Integer>) childMap.get("nodes");
			if(chNodes != null){
				for(Map.Entry<String,Integer> entry :chNodes.entrySet()){
					if(!nodes.containsKey(entry.getKey())){
						nodes.put(entry.getKey(), entry.getValue());
					}
				}
			}
			List<WordDTO> chLinks = (List<WordDTO>) childMap.get("links");
			if(chLinks != null){
				linkes.addAll(chLinks);
			}
		}

		return map;
	}


}
