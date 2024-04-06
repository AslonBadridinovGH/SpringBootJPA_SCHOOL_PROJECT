package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.repository.SchoolRepository;
import uz.pdp.studentDto.StudentDto;
import uz.pdp.entity.School;
import uz.pdp.entity.Student;
import uz.pdp.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SchoolRepository schoolRepository;

    //READ
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    //Create
    @RequestMapping(value = "/student",method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentDto dto){

         School school=new School();
        school.setSchoolName(dto.getSchoolName());
        School saveSchool = schoolRepository.save(school);

         Student student=new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());

        student.setSchoolName(saveSchool);

        studentRepository.save(student);
        return "Student added";

    }

    //UPDATE
    @RequestMapping(value = "/student/{id}",method = RequestMethod.PUT)
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto dto){

     Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
         Student student = optionalStudent.get();

         student.setFirstName(dto.getFirstName());
         student.setLastName(dto.getLastName());

         School school = student.getSchoolName();
         school.setSchoolName(dto.getSchoolName());
         schoolRepository.save(school);
         studentRepository.save(student);

         return "Student edited";
     }
        return "Student not found";
}

    // DELETE
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return "Student deleted";
    }


}
