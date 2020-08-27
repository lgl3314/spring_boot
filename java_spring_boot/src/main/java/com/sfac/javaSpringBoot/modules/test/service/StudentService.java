package com.sfac.javaSpringBoot.modules.test.service;

import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Result<Student> insertStudent(Student student);

    Student getstudentByid(int studentId);

    //分页
    Page<Student> getstudentByvo(SearchVo searchVo);

    List<Student> getStudentByName(String studentName,int cardId);
}
