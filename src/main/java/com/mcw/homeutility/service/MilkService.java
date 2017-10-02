package com.mcw.homeutility.service;

import com.mcw.homeutility.dto.MilkDto;
import com.mcw.homeutility.entity.Milk;
import com.mcw.homeutility.exception.AppException;
import com.mcw.homeutility.repo.MilkRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mcw.homeutility.utility.AppConstants.*;
import static com.mcw.homeutility.utility.CommonUtils.getDate;

/**
 * Created by renuka on 30/9/17.
 */

@Service
public class MilkService {

    @Autowired
    private MilkRepo milkRepo;

    public MilkDto storeMilkData (MilkDto milkDto) throws AppException {
        Milk milk = new Milk();
        try {
            milk.setDate(getDate(milkDto.getDate()));
        } catch (ParseException e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), DATE_FORMAT_ERROR_MSG, e);
        }
        if (milkDto.getDelivered().equals(YES)) {
            milk.setDelivered(true);
            milk.setPrice(milkDto.getPrice());
            milk.setQuantity(milkDto.getQuantity());
        } else {
            milk.setDelivered(false);
            milk.setPrice(0);
            milk.setQuantity(0);
        }
        return transform(milkRepo.save(milk));
    }

    public MilkDto storeMilkData (String date, MilkDto milkDto) throws AppException {
        MilkDto existing;

        try {
            existing = getMilkDetail(date);
        } catch (AppException e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), DATE_FORMAT_ERROR_MSG, e);
        }

        if (existing == null) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), String.format(NO_RECORD_ERROR_MSG, date));
        } else {
            if (StringUtils.isNotEmpty(milkDto.getDelivered()))
                existing.setDelivered(milkDto.getDelivered());

            if (milkDto.getPrice() != null)
                existing.setPrice(milkDto.getPrice());

            if (milkDto.getQuantity() != null)
                existing.setQuantity(milkDto.getQuantity());
        }

        return storeMilkData(existing);
    }

    public List<MilkDto> getMilkDetails () {
        return StreamSupport.stream(milkRepo.findAll().spliterator(), false).map(MilkDto::new).collect(Collectors.toList());
    }

    public MilkDto getMilkDetail (String date) throws AppException {
        MilkDto milkDto = null;
        Milk milk;
        try {
            milk = milkRepo.findOne(getDate(date));
        } catch (ParseException e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), DATE_FORMAT_ERROR_MSG, e);
        }
        if (milk != null) {
            milkDto = transform(milk);
        }
        return milkDto;
    }

    public MilkDto transform(Milk milk) throws AppException {
        MilkDto milkDto = new MilkDto();
        milkDto.setQuantity(milk.getQuantity());
        milkDto.setPrice(milk.getPrice());
        try {
            milkDto.setDate(getDate(milk.getDate()));
        } catch (ParseException e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), DATE_FORMAT_ERROR_MSG, e);
        }
        if (milk.getDelivered())
            milkDto.setDelivered(YES);
        else
            milkDto.setDelivered(NO);
        return milkDto;
    }

    public void deleteMilkDetail (String date) throws AppException {
        try {
            Milk milk = milkRepo.findOne(getDate(date));
            if (milk == null)
                throw new AppException(HttpStatus.BAD_REQUEST.value(), String.format(NO_RECORD_ERROR_MSG, date));
            milkRepo.delete(milk);
        } catch (ParseException e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), DATE_FORMAT_ERROR_MSG, e);
        }
    }
}
