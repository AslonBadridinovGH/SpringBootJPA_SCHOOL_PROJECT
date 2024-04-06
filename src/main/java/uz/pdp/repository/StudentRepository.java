package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
