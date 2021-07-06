package az.code.demoweb.controllers;

import az.code.demoweb.models.Student;
import az.code.demoweb.repositories.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    StudentRepository studentRepository;
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostConstruct
    public void init(){
        List<Student> students = IntStream.range(1, 10)
                .mapToObj(i -> Student.builder()
                        .id(i).name("Name-" + i)
                        .surname("Surname" + i)
                        .build()).collect(Collectors.toList());
        studentRepository.saveAll(students);
    }
    @GetMapping
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/version")
    public String getVersion(){
        return "version-1.0";
    }
}
