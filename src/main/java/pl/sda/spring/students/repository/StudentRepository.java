package pl.sda.spring.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.spring.students.model.Student;

// pierwszy typ generyczny to klasa na której działa a drugi to jej id
public interface StudentRepository extends JpaRepository<Student, Long> {

}
