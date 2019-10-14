package pl.sda.spring.students.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.spring.students.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
