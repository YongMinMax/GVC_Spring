package com.waveware.mapper;

import com.waveware.dto.CodeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper
{
	public List<CodeDTO> selectAllHSList(String code,int length);
	public List<CodeDTO> selectAllMTIList(String code,int length);
	public List<CodeDTO> selectAllSITCList(String code,int length);
	public List<CodeDTO> selectAllICIOList(String code,int length);
	public List<CodeDTO> selectAllISICList(String code,int length);
	public List<CodeDTO> selectAllKSICList(String code,int length);

	public List<CodeDTO> selectPartHSList(int sourceLength, String sourceCode,String targetSymbol, int targetLength, String targetCode);
	public List<CodeDTO> selectPartMTIList(int sourceLength, String sourceCode,String targetSymbol, int targetLength, String targetCode);
	public List<CodeDTO> selectPartSITCList(int sourceLength, String sourceCode,String targetSymbol, int targetLength, String targetCode);
	public List<CodeDTO> selectPartICIOList(int sourceLength, String sourceCode,String targetSymbol, int targetLength, String targetCode);
	public List<CodeDTO> selectPartISICList(int sourceLength, String sourceCode,String targetSymbol, int targetLength, String targetCode);
//	public List<CodeDTO> selectPartKSICList(String code,int length);


}

