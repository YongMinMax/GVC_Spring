package com.waveware.dto.master;


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
	private String country;
	private String classify;
	private String title;
	private Double amounts;
	private Double kg;
	private Double price;
	private Double totalPrice; // 총금액
	private Double totalRatio;
	private Double totalScore;
	private Double totalDependence;

	private CodeDTO code;
}
