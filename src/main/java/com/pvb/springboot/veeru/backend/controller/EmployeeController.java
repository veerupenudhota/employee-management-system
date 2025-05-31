package com.pvb.springboot.veeru.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pvb.springboot.veeru.backend.entity.Employee;
import com.pvb.springboot.veeru.backend.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private ObjectMapper objectMapper; 

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addEmployee(
            @RequestPart("data") String data,
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "uploadDir", required = false) String uploadDir) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            Employee employee = mapper.readValue(data, Employee.class);
            service.save(employee, file, uploadDir);
            return ResponseEntity.ok("Employee saved successfully");
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.badRequest().body("Error parsing data: " + e.getMessage());
        }
    }
    @GetMapping("/search")
    public List<Employee> search() {
        return service.getAll();
    }

    @GetMapping("/view/{id}") 
    public Employee view(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}") 
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.badRequest().body("Error deleting employee: " + e.getMessage());
        }
    }
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateEmployee(
        @PathVariable Long id, 
        @RequestPart("data") String empData,
        @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            Employee updatedEmp = mapper.readValue(empData, Employee.class);
            updatedEmp.setId(id); 
            service.update(updatedEmp, file);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error updating employee: " + e.getMessage());
        }
    }
}