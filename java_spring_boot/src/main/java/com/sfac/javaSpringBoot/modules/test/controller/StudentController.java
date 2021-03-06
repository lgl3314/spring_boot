package com.sfac.javaSpringBoot.modules.test.controller;

import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import com.sfac.javaSpringBoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     *
     * 127.0.0.1/api/student ---- post
     * {"studentName":"lgl1","studentCard":{"cardId":"1"}}
     */
    @PostMapping(value = "student", consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentService.insertStudent(student);
    }

    @GetMapping("/student/{studentId}")
    public Student getstudentByid( @PathVariable int studentId){
        return studentService.getstudentByid(studentId);
    }

    /**
     * 127.0.0.1/api/students ---- post
     * {"currentPage":"1","pageSize":"5","keyWord":"l","orderBy":"studentName","sort":"desc"}
     */
    @PostMapping(value = "/students", consumes = "application/json")
    public Page<Student> getstudentByvo(@RequestBody SearchVo searchVo){
        return studentService.getstudentByvo(searchVo);
    }

    /**
     * 127.0.0.1/api/students?studentName=lgl------- post
     *
     */
    @GetMapping("/students")
    public List<Student> getStudentByParams(
            @RequestParam String studentName,
            @RequestParam(required = false,defaultValue = "1") Integer cardId){
        return studentService.getStudentByName(studentName,cardId);
    }
}
