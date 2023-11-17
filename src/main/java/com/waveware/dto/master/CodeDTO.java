package com.waveware.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeDTO implements Cloneable
{
	private String code;
	private String title;
	private List<CodeDTO> childrenList;
	private CodeDTO children;
	private Long cnt;
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
//		if (o == null || getClass() != o.getClass())
//		{
//			return false;
//		}
//		CodeDTO codeDTO = (CodeDTO) o;
		return Objects.equals(code, o);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(code);
	}

	@Override
	public CodeDTO clone()
	{
		try
		{
			CodeDTO clone = (CodeDTO) super.clone();
			// TODO: 이 복제본이 원본의 내부를 변경할 수 없도록 여기에 가변 상태를 복사합니다
			return clone;
		}
		catch (CloneNotSupportedException e)
		{
			throw new AssertionError();
		}
	}
}
