package org.homework.class1114;

import org.homework.class1114.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
public class TestJdbcTeample {

    //HikariDataSource，默认
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DataSource dataSource;

    @Test
    void contextLoads2() {
        System.out.println("数据源："+dataSource);
        Student student = new Student();
        student.setName("linboxuan");
        insertStudent(student);
        List<Student> student1 = findStudent(5023);
        Student student2 = student1.get(0);
        System.out.println(student2);
        student2.setName("1231231");
        updateStudent(student2);
        deleteStudent(1);
    }

    public int insertStudent(Student student) {
        return  jdbcTemplate.update("insert into student(name) values(?)",student.getName());
    }


    public int updateStudent(Student student) {
        return jdbcTemplate.update("update student set name =? where id= ?",student.getName(),student.getId());
    }


    public int deleteStudent(Integer id) {
        return jdbcTemplate.update("delete from student where id =?",id);
    }


    public List<Student> findStudent(Integer id) {
        List<Student> list = jdbcTemplate.query("select * from student where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Student.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
