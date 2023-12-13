package com.RestApi.MongoDB;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping ("api/v1")
public class StudentController {


    private final StudentService studentService;
    @GetMapping("/students")
    public List<Student> fetchAllStudents(){
        return studentService.getAllStudents();
    }
}
