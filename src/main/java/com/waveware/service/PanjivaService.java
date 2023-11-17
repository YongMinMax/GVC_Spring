package com.waveware.service;


import com.waveware.dto.master.CodeDTO;
import com.waveware.mapper.master.PanjivaMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

@Service
public class PanjivaService
{
	@Autowired
	PanjivaMapper mapper;

	public JSONArray getPanjivaCallMti(JSONObject requestParams) throws IOException, ParseException
	{

		JSONParser parser = new JSONParser();
		URL url = new URL("http://220.89.167.202:46230/mtiCompany");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST"); // 전송 방식
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초)
		conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
		conn.setDoOutput(true);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(requestParams.toJSONString());
		bw.flush();
		bw.close();

		System.out.println("getContentType():" + conn.getContentType()); // 응답 콘텐츠 유형 구하기
		System.out.println("getResponseCode():" + conn.getResponseCode()); // 응답 코드 구하기
		System.out.println("getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

		Charset charset = Charset.forName("UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		JSONArray obj = (JSONArray) parser.parse(br);
		insertHS(obj);
		return obj;
	}
	public JSONArray getPanjivaCallIndustry(String industry,int topk) throws IOException, ParseException
	{

		JSONParser parser = new JSONParser();
		URL url = new URL("http://220.89.167.202:46230/industryCompany?industry="+industry+"&topk="+topk);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET"); // 전송 방식
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초)
		conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
		conn.setDoOutput(true);

		Charset charset = Charset.forName("UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
		JSONArray obj = (JSONArray) parser.parse(br);
		insertHS(obj);
		return obj;
	}

	private void insertHS (JSONArray arr){

		for(Object obj : arr){
			JSONObject data	 = (JSONObject)obj;
			JSONArray list = (JSONArray) data.get("hscode_data");

			Set<String> hsSet = new HashSet<>();
			for(Object temp : list ){
				JSONObject codeObj = (JSONObject) temp;
				String hscode = (String) codeObj.get("hscode");
				hsSet.add(hscode);
			}

			List<CodeDTO> codeList = mapper.selectHSList(hsSet);

			JSONArray save = new JSONArray();
			for(Object temp :list){
				JSONObject jsonObj  = (JSONObject) temp;
				String hscode = (String) jsonObj.get("hscode");
				String code2 = hscode.substring(0,2);
				String code4 = hscode.substring(0,4);
				String code6 = hscode.substring(0,6);
				Long cnt = (Long) jsonObj.get("cnt");
				for(CodeDTO code2DTO : codeList){
					 if(code2DTO.equals(code2)){
						 Long code2_cnt = code2DTO.getCnt();
						 if(code2_cnt == null){
							 code2DTO.setCnt(cnt);
						 }else{
							 code2DTO.setCnt(cnt+code2_cnt);
						 }

						 for(CodeDTO code4DTO : code2DTO.getChildrenList())
						 {
							 if(code4DTO.equals(code4)){
								 Long code4_cnt = code4DTO.getCnt();
								 if(code4_cnt == null){
									 code4DTO.setCnt(cnt);
								 }else{
									 code4DTO.setCnt(cnt+code4_cnt);
								 }

								 for(CodeDTO code6DTO : code4DTO.getChildrenList())
								 {
									 if(code6DTO.equals(code6)){
										 Long code6_cnt = code6DTO.getCnt();
										 if(code6_cnt == null){
											 code6DTO.setCnt(cnt);
										 }else{
											 code6DTO.setCnt(cnt+code6_cnt);
										 }
									 }
								 }
							 }

						 }

					 }
				}
			}

			data.remove("hscode_data");
			data.put("treemap",codeList);
			System.out.println();
		}






//		System.out.println(codeList);
	}

	static class HsList {

		JSONArray list;

		public HsList(JSONArray list){
			this.list = list;


			codeMap= new HashMap<>();
  			hsSet = new HashSet<>();

		}
		Map<String,CodeDTO> codeMap;
		Set<String> hsSet;
	}




}
