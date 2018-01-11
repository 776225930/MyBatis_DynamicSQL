package com.example.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.mybatis.bean.Employee;

public interface EmployeeMapperDynamicSQL {

	public List<Employee> getEmpsTestInnerParameter(Employee employee);
	// Я�����Ǹ��ֶβ�ѯ�����ʹ�������ֶ�
	public List<Employee> getEmpsByComditionIf(Employee employee);

	public List<Employee> getEmpsByComditionTrim(Employee employee);

	public List<Employee> getEmpsByComditionChoose(Employee employee);

	public void updateEmp(Employee employee);

	public List<Employee> getEmpsByComditionForeach(@Param("ids") List<Integer> ids);

	public void addEmps(@Param("emps")List<Employee> emps);
}
