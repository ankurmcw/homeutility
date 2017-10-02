package com.mcw.homeutility.controller;

import com.mcw.homeutility.dto.MilkDto;
import com.mcw.homeutility.exception.AppException;
import com.mcw.homeutility.service.MilkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by renuka on 2/10/17.
 */

@RestController
public class UtilityRestController {

    @Autowired
    private MilkService milkService;

    @GetMapping("/milk/detail")
    public ResponseEntity<List<MilkDto>> getMilkDetails() {
        return new ResponseEntity<>(milkService.getMilkDetails(), HttpStatus.OK);
    }

    @GetMapping("/milk/detail/{date}")
    public ResponseEntity<MilkDto> getMilkDetail(@PathVariable("date") String date) throws AppException {
        return new ResponseEntity<>(milkService.getMilkDetail(date), HttpStatus.OK);
    }

    @PostMapping("/milk/detail")
    public ResponseEntity<MilkDto> storeMilkDetail(@RequestBody MilkDto milkDto) throws AppException {
        return new ResponseEntity<>(milkService.storeMilkData(milkDto), HttpStatus.CREATED);
    }

    @PutMapping("/milk/detail/{date}")
    public ResponseEntity<MilkDto> updateMilkDetail(@PathVariable("date") String date, @RequestBody MilkDto milkDto) throws AppException {
        MilkDto newMilkDto = milkService.storeMilkData(date, milkDto);
        return new ResponseEntity<>(newMilkDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/milk/detail/{date}")
    public ResponseEntity<String> updateMilkDetail(@PathVariable("date") String date) throws AppException {
        milkService.deleteMilkDetail(date);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
