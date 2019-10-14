package pl.sda.spring.students.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.spring.students.model.Grade;
import pl.sda.spring.students.model.Student;
import pl.sda.spring.students.repository.GradeRepository;
import pl.sda.spring.students.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    public List<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }


    public void add(Grade grade, Long studentParam) {
        // get one zawsze zwraca studenta a nie optional.
        // używamy tylko gdy mamy pewność że obiekt jest w bazie
        Student student = studentRepository.getOne(studentParam);
        grade.setStudent(student);
        gradeRepository.save(grade);
    }

    public void deleteById(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }

    public Optional<Grade> findById(Long gradeId){
        return gradeRepository.findById(gradeId);
    }
}
