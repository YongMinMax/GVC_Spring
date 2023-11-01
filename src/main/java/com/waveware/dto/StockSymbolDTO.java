package com.waveware.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockSymbolDTO
{
	public StockSymbolDTO(String prefix, String symbol,String name)
	{
		this.prefix = prefix;
		this.symbol = symbol;
		this.name = name;

		if(prefix.toLowerCase().equals("etf")|| prefix.toLowerCase().equals("kvix")){
			type = "ETF";
		}else{
			type = "환율";
		}
	}

	public StockSymbolDTO()
	{

	}

	private String prefix;
	private String symbol;
	private String type;
	private String name;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;


	}

	public String getSymbol()
	{
		return symbol;
	}

	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}
	@JsonIgnore

	static private List<StockSymbolDTO> list;
	static public List<StockSymbolDTO> getList(){
		if(list == null){
			list = new ArrayList<>();
			list.add(new StockSymbolDTO("eft","battery","배터리"));
			list.add(new StockSymbolDTO("eft","car","자동차"));
			list.add(new StockSymbolDTO("eft","gold","금"));
			list.add(new StockSymbolDTO("eft","oil","에너지"));
			list.add(new StockSymbolDTO("eft","petrochemical","화학"));
			list.add(new StockSymbolDTO("eft","semiconductor","반도체"));
			list.add(new StockSymbolDTO("eft","steel","철강"));
			list.add(new StockSymbolDTO("krw","cny","중국/한국"));
			list.add(new StockSymbolDTO("krw","jpy","일본/한국"));
			list.add(new StockSymbolDTO("krw","rub","러시아/한국"));
			list.add(new StockSymbolDTO("krw","usd","미국/한국"));
			list.add(new StockSymbolDTO("krw","eur","유럽/한국"));
			list.add(new StockSymbolDTO("kvix","exchange","KOSIP Volatility"));
		}


		return list;
	}

}
