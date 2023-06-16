package com.joel.crud.service;

import com.joel.crud.model.Student;
import com.joel.crud.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private IStudentRepository iStudentRepository;

    public Student saveStudent(Student student){
        if(student.getId() == null){
            return iStudentRepository.save(student);
        }
        return null;
    }

    public Page<Student> getAllStudent (Integer page, Integer size, Boolean enablePagination) {
        return iStudentRepository.findAll(enablePagination ? PageRequest.of(page, size) : Pageable.unpaged());
    }

    public void deleteStudent(Long id){
        iStudentRepository.deleteById(id);
    }

    public Student editStudent(Student student) {
        try {
            if (student.getId() != null && iStudentRepository.existsById(student.getId())) {
                return iStudentRepository.save(student);
            }
        } catch (DataAccessException e) {
            // Maneja la excepción aquí o realiza alguna acción específica
            System.out.println("Ocurrió un error al editar el estudiante: " + e.getMessage());
        }
        return null;
    }

    public boolean existById(Long id) {
        return iStudentRepository.existsById(id);
    }
}
