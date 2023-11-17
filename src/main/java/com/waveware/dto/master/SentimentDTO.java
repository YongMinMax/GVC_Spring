package com.waveware.dto.master;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentimentDTO
{
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date date;
	private String country;
	private Double score;
	private Integer rank;
}
