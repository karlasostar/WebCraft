package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.email LIKE ?1%")
    Optional<Student> findStudentByEmailPrefix(String email);

    @Query("SELECT s FROM Student s WHERE s.id = ?1")
    Optional<Student> findStudentById(Long id);

    @Query("SELECT s FROM Student s WHERE s.name LIKE ?1%")
    Optional<Student> findStudentByNamePrefix(String name);
    @Query("SELECT s FROM Student s WHERE s.dob > ?1")
    List<Student> findStudentByDobAfter(LocalDate dobAfter);
}
