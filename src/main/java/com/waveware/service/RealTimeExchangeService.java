package com.waveware.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RealTimeExchangeService
{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private Map<String, Double> exchangeMap = new HashMap<>();

	{
		exchangeMap.put("US",null);
		exchangeMap.put("RU",null);
		exchangeMap.put("CN",null);
		exchangeMap.put("JP",null);
		exchangeMap.put("EU",null);
	}
	private JSONParser parser = new JSONParser();

	public Map<String, Double> getExchangeMap()
	{
		return exchangeMap;
	}

	@Scheduled(fixedDelay = 1000 * 60*5)
	private void crwalingExchange() throws IOException, ParseException
	{
		Calendar calendar= Calendar.getInstance();
		Date date = calendar.getTime();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour < 11){
			 calendar.add(Calendar.DAY_OF_MONTH,-1);
			 date = calendar.getTime();
		}

//		URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=Nb4He5yfDgtHMG1uT7W5Al2QZBHjIo59&searchdate="+sdf.format(new Date())+"&data=AP01");
//		URLConnection connection = url.openConnection();
//		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//		JSONArray arr = (JSONArray) parser.parse(br);
//		try{
//			for (Object obj : arr)
//			{
//				JSONObject exchange = (JSONObject) obj;
//				String country = (String) exchange.get("cur_unit");
//				Double value = Double.parseDouble(((String) exchange.get("kftc_deal_bas_r")).replaceAll(",",""));
//
//				switch (country){
//					case"USD":
//						exchangeMap.put("US",value);
//						break;
//					case"CNH":
//						exchangeMap.put("CN",value);
//						break;
//					case"RUB":
//						exchangeMap.put("RU",value);
//						break;
//					case"EUR":
//						exchangeMap.put("EU",value);
//						break;
//					case"JPY(100)":
//						exchangeMap.put("JP",value/100);
//						break;
//				}
//			}
//		}catch (NullPointerException e){
//			System.err.println("[ERR] 사용가능한 횟수를 초과했습니다. : "+arr.toString());
//
//		}

	}


}
