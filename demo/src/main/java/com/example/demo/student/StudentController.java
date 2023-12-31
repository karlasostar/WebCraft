package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RequestMapping(path = "api/v1/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(@RequestParam(required = false) Long id,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) String dobAfter,
                                     @RequestParam(required = false) String dobBefore) {




        if(id != null && (name != null || email != null || dobAfter != null || dobBefore != null)){
            throw new IllegalArgumentException("Ne moze filtrirati");
        } else if (name != null && (email != null || dobAfter != null || dobBefore != null )) {
            throw new IllegalArgumentException("Ne moze filtrirati");
        } else if (email != null && (dobAfter != null || dobBefore != null )) {
            throw new IllegalArgumentException("Ne moze filtrirati");
        }
        //else if (dobAfter != null && (id != null || name != null || email != null) {
        //throw new IllegalArgumentException("Ne moze filtrirati po dobAfter i jos nesto sto nije dobBefore");
        //}
        else if (id!=null) {
            return studentService.getStudentsById(id);
        } else if (name!=null) {
            return studentService.getStudentsByName(name);
        } else if (email != null) {
            return studentService.getStudentsByEmail(email);
        }

        return studentService.getStudents();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @DeleteMapping(path="{studentId}")
    public void deleteStudent(
            @PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
                studentService.updateStudent(studentId,name,email);
    }


}
