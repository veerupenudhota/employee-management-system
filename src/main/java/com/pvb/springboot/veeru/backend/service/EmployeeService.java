package com.pvb.springboot.veeru.backend.service;

import com.pvb.springboot.veeru.backend.entity.Employee;
import com.pvb.springboot.veeru.backend.repository.EmployeeRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository; 

    private final String defaultUploadDir = "uploads"; 

    public Employee save(Employee employee, MultipartFile file, String uploadDir) throws IOException {
        String finalUploadDir = (uploadDir != null && !uploadDir.isEmpty()) ? uploadDir : defaultUploadDir;
        Path uploadPath = Paths.get(finalUploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            employee.setIdProofPath(filePath.toString());
        }
        return repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Employee update(Employee updatedEmployee, MultipartFile file) throws IOException {
        Optional<Employee> existingEmployeeOptional = repository.findById(updatedEmployee.getId());

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setMiddleName(updatedEmployee.getMiddleName());
            existingEmployee.setLoginId(updatedEmployee.getLoginId());
            existingEmployee.setDob(updatedEmployee.getDob());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setPermanentAddress(updatedEmployee.getPermanentAddress());
            existingEmployee.setCurrentAddress(updatedEmployee.getCurrentAddress());

            if (updatedEmployee.getEmployeeId() != null && !updatedEmployee.getEmployeeId().equals(existingEmployee.getEmployeeId())) {
                existingEmployee.setEmployeeId(updatedEmployee.getEmployeeId());
            }
            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get(defaultUploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);
                existingEmployee.setIdProofPath(filePath.toString());
            }
            return repository.save(existingEmployee); 
        } else {
            throw new IllegalArgumentException("Employee with ID " + updatedEmployee.getId() + " not found.");
        }
    }
}