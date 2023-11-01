package com.waveware.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;

import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeDTO
{
	private String code;
	private String title;

	private CodeDTO children;

	public CodeDTO getChildren()
	{
		return children;
	}

	public void setChildren(CodeDTO children)
	{
		this.children = children;
	}

	public List<CodeDTO> getChildrenList()
	{
		return childrenList;
	}

	public void setChildrenList(List<CodeDTO> childrenList)
	{
		this.childrenList = childrenList;
	}

	private List<CodeDTO> childrenList;


	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
}
