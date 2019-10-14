package pl.sda.spring.students.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.spring.students.model.Student;
import pl.sda.spring.students.service.StudentService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping(path= "/student/")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/add")
    public String add(Model model, Student student){
        model.addAttribute("student", student);
        return"student-add";
    }

    @PostMapping("/add")
    public String add(Student student){
        studentService.add(student);

        return "redirect:/student/list";
    }
 //wykorzytsanie PathVariable przy delete
    @GetMapping("/delete/{deleted_student_id}")
    public String delete (@PathVariable(name="deleted_student_id") Long studentId) {
        return deleteStudent(studentId);
    }

    //wykorzytsanie RequestParam zamiast PathVariable
    @GetMapping("/delete2")
    public String delete2 (Model model, @RequestParam(name="studentId") Long studentId) {
        model.addAttribute("studentId", studentId);
        return deleteStudent(studentId);
    }

    private String deleteStudent(@PathVariable(name = "deleted_student_id") Long studentId) {
        studentService.delete(studentId);
        return "redirect:/student/list";
    }

//metoda 1
    @GetMapping("/edit/{edited_student_id}")
    public String edit (Model model, @PathVariable(name="edited_student_id") Long studentId) {
        return editStudent(model, studentId);
    }

//    metoda2
    @GetMapping("/edit2")
    public String edit2 (Model model, @RequestParam(name="studentId") Long studentId) {
        model.addAttribute("studentId", studentId);
        return editStudent(model, studentId);
    }


    @GetMapping("/grades/{id}")
    public String studentGrades(Model model,
                                @PathVariable(name = "id") Long studentId) {
        Optional<Student> studentOptional = studentService.findById(studentId);
        if(studentOptional.isPresent()){
            model.addAttribute("grades", studentOptional.get().getGradeList());
            return "grade-list";
        }
        return "redirect:/student/list";
    }

    private String editStudent(Model model, @RequestParam(name = "studentId") Long studentId) {
        Optional<Student> studentOptional = studentService.findById(studentId);
        if (studentOptional.isPresent()) {
            model.addAttribute("student", studentOptional.get());
            return "student-add";
        }
        return "redirect:/student/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("students", studentList);
        return "student-list";
    }
}
