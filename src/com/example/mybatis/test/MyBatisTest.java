package com.example.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.mybatis.bean.Department;
import com.example.mybatis.bean.Employee;
import com.example.mybatis.dao.DepartmentMapper;
import com.example.mybatis.dao.EmployeeMapper;
import com.example.mybatis.dao.EmployeeMapperAnnotation;
import com.example.mybatis.dao.EmployeeMapperDynamicSQL;
import com.example.mybatis.dao.EmployeeMapperPlus;

/**
 * 1���ӿ�ʽ��� ԭ���� Dao ====> DaoImpl mybatis�� Mapper ====> xxMapper.xml
 * 
 * 2��SqlSession��������ݿ��һ�λỰ���������رգ�
 * 3��SqlSession��connectionһ�����Ƿ��̰߳�ȫ��ÿ��ʹ�ö�Ӧ��ȥ��ȡ�µĶ���
 * 4��mapper�ӿ�û��ʵ���࣬����mybatis��Ϊ����ӿ�����һ��������� �����ӿں�xml���а󶨣� EmployeeMapper
 * empMapper = sqlSession.getMapper(EmployeeMapper.class); 5��������Ҫ�������ļ���
 * mybatis��ȫ�������ļ����������ݿ����ӳ���Ϣ�������������Ϣ��...ϵͳ���л�����Ϣ sqlӳ���ļ���������ÿһ��sql����ӳ����Ϣ��
 * ��sql��ȡ������
 * 
 * @author JHao
 *
 */
public class MyBatisTest {
	private SqlSessionFactory sessionFactory = null;
	// ���̰߳�ȫ������д����,���ڴ���ô��
	private SqlSession session = null;

	@Before
	public void init() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		sessionFactory = new SqlSessionFactoryBuilder().build(in);
		session = sessionFactory.openSession();
	}

	@After
	public void destroy() {
		session.commit();
		session.close();
	}

	/**
	 * ��������: һ������:(���ػ���)sqlSession����Ļ���,һ��������һֻ������
	 *     �����ݿ�ͬһ�λỰ�ڼ�����ݻ���ڱ��ػ�����
	 *     �Ժ����Ҫ��ȡ��ͬ������,ֱ�Ӵӻ�����ȥ��û��Ҫ�ٲ�ѯ���ݿ�
	 * 
	 */
	@Test
	public void testFirstLevelCache() {
		EmployeeMapper mapper=session.getMapper(EmployeeMapper.class);
		Employee emp1=mapper.getEmpById(1);
		Employee emp2=mapper.getEmpById(1);
		System.out.println(emp1==emp2);
		System.out.println(emp1);
	}
	@Test
	public void testSecondLevelCache() {
		SqlSession session=sessionFactory.openSession();
		SqlSession session2=sessionFactory.openSession();
		EmployeeMapper mapper=session.getMapper(EmployeeMapper.class);
		Employee emp1=mapper.getEmpById(1);
		System.out.println(emp1);
		session.close();
		
		mapper=session2.getMapper(EmployeeMapper.class);
		Employee emp2=mapper.getEmpById(1);
		System.out.println(emp2);
		session2.close();
	}

}
