package com.gdu.app1.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {
	@Bean
	public Student stud() { // <bean id="stud" class="Student">
		// List
		List<Integer> scores = new ArrayList<Integer>();
		for(int cnt = 0; cnt < 5; cnt++) {
			scores.add((int)(Math.random() * 101) + 1); // 101���� ������ �߻� (0~100)
		}
		
		// Set
		Set<String> awards = new HashSet<String>();
		awards.add("���ٻ�");
		awards.add("�����");
		awards.add("�����");
		
		// Map
		Map<String, String> contact = new HashMap<String, String>();
		contact.put("address", "����");
		contact.put("tel", "010-123-2342");
	
		// ��ü ������ ����
		Student student = new Student();
		student.setScores(scores);
		student.setAwards(awards);
		student.setContact(contact);
		
		return student;
	}
}
