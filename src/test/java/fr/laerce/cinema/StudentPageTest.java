package fr.laerce.cinema;

import fr.laerce.cinema.dao.StudentPageRepository;
import fr.laerce.cinema.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentPageTest {
    @Autowired
    private StudentPageRepository studentPageRepository;

    @Test
    public void testPage1() { // test the pagination only
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0,pageSize);
        Page<Student> students = studentPageRepository.findAll(pageable);
        assertEquals(students.getSize(),pageSize);
    }

    @Test
    public void testPageAndSort() { // test the pagination with sort
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0,pageSize, Sort.Direction.ASC,"id"); //order by id asc
        Page<Student> students = studentPageRepository.findAll(pageable);
        assertEquals(students.getSize(),pageSize);
        assertEquals(students.getContent().get(0).getId(),1);//the min id should be 1
    }
}
