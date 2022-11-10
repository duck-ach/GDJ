package com.gdu.app11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.EmpDTO;

@Mapper
public interface EmpMapper {
	public int selectAllEmployeesCount(); // mapper.xml의 resultType이 int, id가 methodName
	public List<EmpDTO> selectEmployeesByPage(Map<String, Object> map);
	public int selectFindEmployeesCount(Map<String, Object> map);
	public List<EmpDTO> selectFindEmployees(Map<String, Object> map);
	public List<EmpDTO> selectAutoCompleteList(String param);
}
