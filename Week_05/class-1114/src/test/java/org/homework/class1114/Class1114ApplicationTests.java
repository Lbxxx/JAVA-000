package org.homework.class1114;

import org.homework.class1114.domain.Klass;
import org.homework.class1114.domain.School;
import org.homework.class1114.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Class1114ApplicationTests {

	@Autowired
	School school;

	@Test
	void contextLoads() {
		// 测试从 starter 中获取自动配置的 bean
		assertNotNull(this.school);
		Klass klass = this.school.getKlass();
		assertNotNull(klass);
		List<Student> students = klass.getStudents();
		assertNotNull(students);
		assertNotEquals(0, students.size());
		Student student = students.get(0);
		assertNotNull(student);
		assertEquals(1, student.getId());
		assertEquals("linboxuan", student.getName());
	}



		@Resource
		private DataSource dataSource;

		@Test
		void contextLoads2() {
			System.out.println(dataSource);
		}




}
