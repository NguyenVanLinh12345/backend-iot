package com.javatechie.service.impl;

import com.javatechie.converter.TypeEggConverter;
import com.javatechie.dto.TypeEggDto;
import com.javatechie.entity.Schedule;
import com.javatechie.entity.TypeEgg;
import com.javatechie.repository.ScheduleRepository;
import com.javatechie.repository.TypeEggRepository;
import com.javatechie.service.ITypeEggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TypeEggService implements ITypeEggService {

    @Autowired
    private TypeEggRepository typeEggRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<TypeEggDto> findAll() {
        List<TypeEggDto> typeEggDtoList = new ArrayList<>();
        List<TypeEgg> typeEggList = typeEggRepository.findAll();
        for(TypeEgg typeEgg : typeEggList) {
            typeEggDtoList.add(TypeEggConverter.toDto(typeEgg));
        }
        return typeEggDtoList;
    }

    @Override
    public TypeEggDto findOne(Integer id) {
        try {
            TypeEgg typeEgg = typeEggRepository.findById(id).orElse(null);
            if(typeEgg == null) return null;
            return TypeEggConverter.toDto(typeEgg);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TypeEggDto updateTypeEgg(TypeEggDto typeEggDto) {
        try {
            TypeEgg oldTypeEgg = typeEggRepository.findById(typeEggDto.getId()).orElse(null);
            if(oldTypeEgg == null) return new TypeEggDto();
            TypeEgg newTypeEgg = typeEggRepository.findByName(typeEggDto.getName()).orElse(null);
            // kiem tra ten
            if(newTypeEgg == null || newTypeEgg.getId().equals(oldTypeEgg.getId())) {
                oldTypeEgg = TypeEggConverter.toEntity(oldTypeEgg, typeEggDto);
                if(oldTypeEgg == null) return new TypeEggDto();
                oldTypeEgg = typeEggRepository.save(oldTypeEgg);
                return TypeEggConverter.toDto(oldTypeEgg);
            }
            // truong hop trung ten khong duoc capj nhat
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new TypeEggDto();
        }
    }

    @Override
    public String deleteTypeEgg(Integer id) {
        try {
            TypeEgg typeEgg = typeEggRepository.findById(id).orElse(null);
            if(typeEgg == null) return "Can not found typeEgg with id = " + id;
            List<Schedule> schedules = typeEgg.getSchedule();
            for(Schedule schedule : schedules) {
                schedule.setTypeEgg(null);
            }
            scheduleRepository.saveAll(schedules);
            typeEggRepository.delete(typeEgg);
            return "success";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    @Override
    public TypeEggDto addTypeEgg(TypeEggDto typeEggDto) {
        try {
            TypeEgg typeEgg = TypeEggConverter.toEntity(typeEggDto);
            if (typeEgg == null) return new TypeEggDto();
            Boolean checkName = typeEggRepository.existsByName(typeEggDto.getName());
            if(!checkName) {
                typeEgg = typeEggRepository.save(typeEgg);
                return TypeEggConverter.toDto(typeEgg);
            }
            return null; // danh dau khong the them moi do trung ten
        }
        catch (Exception e) {
            e.printStackTrace();
            return new TypeEggDto();
        }
    }
}
