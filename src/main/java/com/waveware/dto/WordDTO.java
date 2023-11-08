package com.waveware.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WordDTO
{
	private String word1;
	private String word2;
	@JsonIgnore
	private List<WordDTO> childrenList;


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		WordDTO wordDTO = (WordDTO) o;
		return (word1.equals(wordDTO.word1) && word2.equals(wordDTO.word2))||(word2.equals(wordDTO.word1) && word1.equals(wordDTO.word2));
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(word1, word2);
	}
}
