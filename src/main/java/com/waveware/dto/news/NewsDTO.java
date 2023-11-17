package com.waveware.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO
{
	private int rank;
	private String title;
	private String contents;
	private String img;
	private String url;
	private String category;
}
