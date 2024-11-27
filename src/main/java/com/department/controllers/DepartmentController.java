package com.department.controllers;

import java.util.List;
import java.util.Map;

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
    public List<DepartmentDTO> getDept() {
        return departmentService.getDept();
    }

    @PostMapping
    public DepartmentDTO createDept(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.createDept(departmentDTO);
    }

    @PutMapping("{id}")
    public DepartmentDTO updateDept(@PathVariable long id, @RequestBody DepartmentDTO departmentDTO) {

        return departmentService.updateDept(id, departmentDTO);
    }

    @PatchMapping("{id}")
    public DepartmentDTO updatePartialDept(@PathVariable long id, @RequestBody Map<String, Object> update) {
        return departmentService.updatePartialDept(id, update);
    }

    @DeleteMapping("{id}")
    public boolean deleteDept(@PathVariable long id) {
        return departmentService.deleteDept(id);
    }

}
