package com.sfac.javaSpringBoot.modules.test.service.impl;

import com.sfac.javaSpringBoot.modules.common.vo.Result;
import com.sfac.javaSpringBoot.modules.common.vo.SearchVo;
import com.sfac.javaSpringBoot.modules.test.entity.Student;
import com.sfac.javaSpringBoot.modules.test.repository.StudentRepository;
import com.sfac.javaSpringBoot.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,
                "Insert success", student);
    }

    @Override
    public Student getstudentByid(int studentId) {

        return studentRepository.findById(studentId).get();
    }

    @Override
    public Page<Student> getstudentByvo(SearchVo searchVo) {
        //确定排序顺序
        Sort.Direction direction="desc"
                .equalsIgnoreCase(searchVo.getSort())?
                Sort.Direction.DESC:Sort.Direction.ASC;

        //创建sort对象   三目表达式
        Sort sort=new Sort(direction,
                StringUtils.isBlank(searchVo.getOrderBy())?
                "studebtId":searchVo.getOrderBy());

        /*
        *  String orderBy = StringUtils.isBlank(searchVo.getOrderBy()) ?
                "studentId" : searchVo.getOrderBy();
        Sort.Direction direction = StringUtils.isBlank(searchVo.getSort()) ||
                searchVo.getSort().equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = new Sort(direction, orderBy);
        * */
        //-1使它从第一页开始显示
        Pageable pageable= PageRequest.of(
                searchVo.getCurrentPage()-1,searchVo
                        .getPageSize(),sort);

        //如果keyword为null，则设置student Name不参与查询条件
        Student student=new Student();
        student.setStudentName(searchVo.getKeyWord());
        //实例化
        ExampleMatcher matcher=ExampleMatcher.matching()
                //全部模糊查询，即%{studentName}%
                .withMatcher("studentName",match->match.contains())
          .withIgnorePaths("studentId");
        //实例化
        Example<Student> example=Example.of(student,matcher);
        //findAll()里面没有参数，无参构造，全查
        return studentRepository.findAll(example, pageable);
    }

    @Override
    public List<Student> getStudentByName(String studentName,int cardId) {
        if (cardId>0){
            return studentRepository.getStudentsByParms(studentName,cardId);
        }else {
           /*return Optional.ofNullable(studentRepository
                .findByStudentNameLike(String
                        .format("%s%S%s","%",studentName,"%")))
                .orElse(Collections.emptyList());*/

            return Optional.ofNullable(studentRepository
                    .findTop2ByStudentNameLike(String
                            .format("%s%S%s","%",studentName,"%")))
                    .orElse(Collections.emptyList());

      /*   return Optional.ofNullable(studentRepository
                .findByStudentName(studentName))
                .orElse(Collections.emptyList());
*/
        }

    }
}
