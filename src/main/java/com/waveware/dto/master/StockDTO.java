package com.waveware.dto.master;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDTO
{
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date date;

	private Double open;
	private Double close;
	private Double low;
	private Double high;
	private Double anomaly;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Double getOpen()
	{
		return open;
	}

	public void setOpen(Double open)
	{
		this.open = open;
	}

	public Double getClose()
	{
		return close;
	}

	public void setClose(Double close)
	{
		this.close = close;
	}

	public Double getLow()
	{
		return low;
	}

	public void setLow(Double low)
	{
		this.low = low;
	}

	public Double getHigh()
	{
		return high;
	}

	public void setHigh(Double high)
	{
		this.high = high;
	}

	public Double getAnomaly()
	{
		return anomaly;
	}

	public void setAnomaly(Double anomaly)
	{
		this.anomaly = anomaly;
	}
}
