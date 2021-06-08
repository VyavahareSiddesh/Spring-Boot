package com.example.demo.student;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(student s){
        Optional<student> studentByEmail=studentRepository.findStudentByEmail(s.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email Already Taken");
        }
        studentRepository.save(s);
        System.out.println(s);

    }

    public void deleteStudent(Long id) {
        boolean exist =studentRepository.existsById(id);
        if(!exist)
        {
             throw new IllegalStateException("No Student Found With this id ="+ id);
        }
        studentRepository.deleteById(id);

    }

    @Transactional
    public void updateStudent(Long id,String name, String email) {

        student student = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException("Student id not existing"));
        if(name!=null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email!=null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            Optional<student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent())
            {
                throw new IllegalStateException("Email id existing");
            }
            student.setEmail(email);
        }
    }
}
