package com.department.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.dto.DepartmentDTO;
import com.department.service.DepartmentService;

@RestController
@RequestMapping(path = "/dept")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getDept() {
        return ResponseEntity.ok(departmentService.getDept());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDept(@RequestBody DepartmentDTO departmentDTO) {
        return new ResponseEntity<>(departmentService.createDept(departmentDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DepartmentDTO> updateDept(@PathVariable long id, @RequestBody DepartmentDTO departmentDTO) {

        return ResponseEntity.ok(departmentService.updateDept(id, departmentDTO));
    }

    @PatchMapping("{id}")
    public ResponseEntity<DepartmentDTO> updatePartialDept(@PathVariable long id,
            @RequestBody Map<String, Object> update) {
        return ResponseEntity.ok(departmentService.updatePartialDept(id, update));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteDept(@PathVariable long id) {
        boolean gotDeleted = departmentService.deleteDept(id);
        if (gotDeleted)
            ResponseEntity.ok(gotDeleted);
        return ResponseEntity.notFound().build();
    }

}
