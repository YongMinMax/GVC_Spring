package com.waveware.dto.master;


import lombok.Data;

@Data
public class CompanyDTO
{
	private String company_name;
	private String large_category;
	private String middle_category;
	private String small_category;
	private String detail_category;
	private String small_detail_category;
	private Double income;
	private String main_products;
	private String region;
	private String address;

}
