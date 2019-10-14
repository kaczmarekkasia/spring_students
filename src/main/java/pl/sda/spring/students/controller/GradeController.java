package pl.sda.spring.students.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.spring.students.model.Grade;
import pl.sda.spring.students.model.GradeSubject;
import pl.sda.spring.students.service.GradeService;
import pl.sda.spring.students.service.StudentService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
@RequestMapping(path = "/grade/")
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;

    @GetMapping("/edit/{edited_grade_id}")
    public String edit(Model model, @PathVariable(name = "edited_grade_id") Long gradeId){
        Optional<Grade> optionalGrade = gradeService.findById(gradeId);
        if(optionalGrade.isPresent()){
            model.addAttribute("grade", optionalGrade.get());
            model.addAttribute("subjects", GradeSubject.values());
            return "grade-add";
        }
        return "redirect:/grade/list";
    }

    @GetMapping("/delete/{deleted_grade_id}")
    public String delete(@PathVariable(name = "deleted_grade_id") Long gradeId) {
        gradeService.deleteById(gradeId);
        return "redirect:/grade/list";
    }


    @GetMapping("/add/{studentId}")
    public String add(Model model, Grade grade, @PathVariable(name="studentId") Long studentId) {
        model.addAttribute("grade", grade);
        model.addAttribute("subjects", GradeSubject.values());
        model.addAttribute("studentId", studentId);
        return "grade-add";
    }

    @PostMapping("/add")
    public String add(Grade grade, Long studentParam) {
        gradeService.add(grade, studentParam);

        return "redirect:/grade/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Grade> gradeList = gradeService.getAllGrades();
        model.addAttribute("grades", gradeList);
        return "grade-list";
    }
}
