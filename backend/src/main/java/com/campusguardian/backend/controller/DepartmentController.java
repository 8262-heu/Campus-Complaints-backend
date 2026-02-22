package com.campusguardian.backend.controller;

import com.campusguardian.backend.model.Department;
import com.campusguardian.backend.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    // GET all departments (for dropdowns)
    @GetMapping
    public List<Department> getDepartments() throws Exception {
        return service.getAll();
    }

    // CREATE department
    @PostMapping
    public Department createDepartment(@RequestBody Department dept) throws Exception {
        return service.create(dept);
    }

    // UPDATE department
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable String id, @RequestBody Department dept) throws Exception {
        boolean updated = service.update(id, dept);
        if (updated) return ResponseEntity.ok("Updated");
        return ResponseEntity.notFound().build();
    }

    // DELETE department
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String id) throws Exception {
        boolean deleted = service.delete(id);
        if (deleted) return ResponseEntity.ok("Deleted");
        return ResponseEntity.notFound().build();
    }
}
