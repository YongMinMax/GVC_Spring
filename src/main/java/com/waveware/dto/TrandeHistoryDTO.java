package com.waveware.dto;


import com.fasterxml.jackson.annotation.JsonInclude;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrandeHistoryDTO
{
	private String hscode;
	private double amounts;
	private double kg;
	private double price;
	private double totalPrice; // 총금액
	private double totalRatio; // HS코드 별 수입 의존 비율 ( x100) 해야함..
	private double totalDependence; // 해당국가의 총 수출중 차지하는 비율  (x100) 적용됨

	private CodeDTO code;

	public CodeDTO getCode()
	{
		return code;
	}

	public void setCode(CodeDTO code)
	{
		this.code = code;
	}


	public String getHscode()
	{
		return hscode;
	}

	public void setHscode(String hscode)
	{
		this.hscode = hscode;
	}

	public double getAmounts()
	{
		return amounts;
	}

	public void setAmounts(double amount)
	{
		this.amounts = amount;
	}

	public double getKg()
	{
		return kg;
	}

	public void setKg(double kg)
	{
		this.kg = kg;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public double getTotalRatio()
	{
		return totalRatio;
	}

	public void setTotalRatio(double totalRatio)
	{
		this.totalRatio = totalRatio;
	}

	public double getTotalDependence()
	{
		return totalDependence;
	}

	public void setTotalDependence(double totalDependence)
	{
		this.totalDependence = totalDependence;
	}
}
