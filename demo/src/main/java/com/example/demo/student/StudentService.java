package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("zauzet email");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student sa id: " + studentId +"ne postoji.");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "ne postoji student sa id: "+studentId));

        if(name != null &&
                name.length()>0 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null &&
                email.length()>0 &&
                !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email zauzet");
            }
            student.setEmail(email);
        }

    }

    public List<Student> getStudentsById(Long id) {
        Optional<Student> student = studentRepository.findStudentById(id);
        if (student.isPresent()){
            return studentRepository.findStudentById(id).stream().toList();
        }
        else {
            throw new IllegalStateException("ne postoji student sa tim id");
        }

    }

    public List<Student> getStudentsByNamePrefix(String name) {
        Optional <Student> student = studentRepository.findStudentByNamePrefix(name);
        if (student.isPresent()){
            return studentRepository.findStudentByNamePrefix(name).stream().toList();
        }
        else {
            throw new IllegalStateException("ne postoji student sa tim imenom");
        }
    }

    public List<Student> getStudentsByEmail(String email) {
        Optional <Student> student = studentRepository.findStudentByEmailPrefix(email);
        if (student.isPresent()){
            return studentRepository.findStudentByEmailPrefix(email).stream().toList();
        }
        else {
            throw new IllegalStateException("ne postoji student sa tim emailom");
        }
    }

    public List<Student> getStudentsByEmailPrefix(String email) {
        Optional<Student> student = studentRepository.findStudentByEmailPrefix(email);
        if (student.isPresent()){
            return studentRepository.findStudentByEmailPrefix(email).stream().toList();
        }
        else {
            throw new IllegalStateException("ne postoji student sa tim pocetkom emaila");
        }
    }

    public List<Student> getStudentsByDobAfter(String dobAfter) {
        List<Student> student = studentRepository.findStudentByDobAfter(LocalDate.parse(dobAfter));
        if (!student.isEmpty()){
            return studentRepository.findStudentByDobAfter(LocalDate.parse(dobAfter)).stream().toList();
        }
        else {
            throw new IllegalStateException("ne postoji student roden nakon");
        }
    }
}
