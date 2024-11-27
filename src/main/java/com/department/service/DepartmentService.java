package com.department.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.department.dto.DepartmentDTO;
import com.department.entity.DepartmentEntity;
import com.department.repository.DepartmentRepo;

@Service
public class DepartmentService {

    DepartmentRepo departmentRepo;
    ModelMapper modelMapper;

    public List<DepartmentDTO> getDept() {
        List<DepartmentEntity> dept = departmentRepo.findAll();
        return dept.stream()
                .map(deptEntity -> modelMapper.map(deptEntity, DepartmentDTO.class))
                .collect(Collectors.toList());

    }

    public DepartmentService(DepartmentRepo departmentRepo, ModelMapper modelMapper) {
        this.departmentRepo = departmentRepo;
        this.modelMapper = modelMapper;
    }

    public DepartmentDTO createDept(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        DepartmentEntity saveDept = departmentRepo.save(departmentEntity);
        return modelMapper.map(saveDept, DepartmentDTO.class);
    }

    public DepartmentDTO updateDept(long id, DepartmentDTO departmentDTO) {
        if (departmentRepo.existsById(id)) {
            DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
            departmentEntity.setId(id);
            DepartmentEntity saveDept = departmentRepo.save(departmentEntity);
            return modelMapper.map(saveDept, DepartmentDTO.class);
        }
        return null;
    }

    public DepartmentDTO updatePartialDept(long id, Map<String, Object> update) {
        DepartmentEntity departmentEntity = departmentRepo.findById(id).get();
        update.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
        });
        return modelMapper.map(departmentRepo.save(departmentEntity), DepartmentDTO.class);
    }

    public boolean deleteDept(long id) {
        if (departmentRepo.existsById(id)) {
            departmentRepo.deleteById(id);
            return true;
        } else
            return false;
    }

}
