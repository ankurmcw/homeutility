package com.mcw.homeutility.service;

import com.mcw.homeutility.dto.MilkDto;
import com.mcw.homeutility.entity.Milk;
import com.mcw.homeutility.repo.MilkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mcw.homeutility.utility.CommonUtils.getDate;

/**
 * Created by renuka on 30/9/17.
 */

@Service
public class MilkService {

    @Autowired
    private MilkRepo milkRepo;

    public void storeMilkData(MilkDto milkDto) throws ParseException {
        Milk milk = new Milk();
        milk.setDate(getDate(milkDto.getDate()));
        milk.setPrice(milkDto.getPrice());
        milk.setQuantity(milkDto.getQuantity());
        milkRepo.save(milk);
    }

    public List<MilkDto> getMilkDetails() {
        return StreamSupport.stream(milkRepo.findAll().spliterator(), false).map(MilkDto::new).collect(Collectors.toList());
    }
}
