package com.example.mybatis.dao;

import java.util.List;

import com.example.mybatis.bean.Employee;

public interface EmployeeMapperDynamicSQL {
    
	//Я�����Ǹ��ֶβ�ѯ�����ʹ�������ֶ�
	public List<Employee> getEmpsByComditionIf(Employee employee);
}
