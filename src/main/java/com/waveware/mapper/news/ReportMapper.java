package com.waveware.mapper.news;

import com.waveware.dto.news.IndustryReportDTO;
import com.waveware.dto.news.NewsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper
{
	public List<IndustryReportDTO> getReportForDate(String date);

}

