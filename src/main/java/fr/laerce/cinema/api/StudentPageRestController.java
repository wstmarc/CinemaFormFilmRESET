package fr.laerce.cinema.api;

import fr.laerce.cinema.dao.StudentPageRepository;
import fr.laerce.cinema.model.Student;
import fr.laerce.cinema.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

//TEST PAGINATION
//  /studentsApi/queryByPage?page=0&size=3
@RestController
@RequestMapping("/studentsApi")
public class StudentPageRestController {
    @Autowired
    private StudentService studentService;

    @Autowired//#
    private StudentPageRepository studentPageRepository;//#

    @RequestMapping(value = "/queryByPage", method = RequestMethod.GET)
    public Page<Student> queryByPage(Pageable pageable) {
        Page<Student> pageInfo = studentService.listByPage(pageable);
        return pageInfo;
    }

/*    @GetMapping("/list")
    public String list(Model model){
        int pageSize = 10;
        Pageable pageable = PageRequest.of(0,pageSize);
        Page<Student> All = studentPageRepository.findAll(pageable);
        model.addAttribute("page", All.getTotalPages());
        model.addAttribute("size", All.get());
        return "list";
    }

    @RequestMapping(value="/test", method=RequestMethod.GET)
    public String test(){
        return "test";
    }*/
}