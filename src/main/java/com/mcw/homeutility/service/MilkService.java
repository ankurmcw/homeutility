package com.mcw.homeutility.service;

import com.mcw.homeutility.dto.MilkDto;
import com.mcw.homeutility.entity.Milk;
import com.mcw.homeutility.repo.MilkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by renuka on 30/9/17.
 */

@Service
public class MilkService {

    @Autowired
    private MilkRepo milkRepo;

    public void storeMilkData(MilkDto milkDto) {
        Milk milk = new Milk();
        milk.setDate(new Date());
        milk.setPrice(milkDto.getPrice());
        milkRepo.save(milk);
    }

    public List<MilkDto> getMilkDetails() {
        return StreamSupport.stream(milkRepo.findAll().spliterator(), false).map(MilkDto::new).collect(Collectors.toList());
    }
}
