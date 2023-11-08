package com.waveware.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrandeHistoryDTO
{
	private String hscode;
	private String title;
	private double amounts;
	private double kg;
	private double price;
	private double totalPrice; // 총금액
	private double totalRatio; // HS코드 별 수입 의존 비율 ( x100) 해야함..
	private double totalDependence; // 해당국가의 총 수출중 차지하는 비율  (x100) 적용됨

	private CodeDTO code;
}
