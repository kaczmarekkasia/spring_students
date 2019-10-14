package pl.sda.spring.students.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.spring.students.model.Student;
import pl.sda.spring.students.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService{

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void add(Student student) {
        studentRepository.save(student);
    }


    public Optional<Student> findById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
