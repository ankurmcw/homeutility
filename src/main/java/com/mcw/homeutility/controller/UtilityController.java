package com.mcw.homeutility.controller;

import com.mcw.homeutility.dto.MilkDto;
import com.mcw.homeutility.service.MilkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by renuka on 30/9/17.
 */

@Controller
public class UtilityController {

    private static Logger logger = LoggerFactory.getLogger(UtilityController.class);

    @Autowired
    private MilkService milkService;

    @GetMapping("/home")
    public String welcome(Map<String, Object> model) {
        model.put("message", "Hello world");
        return "welcome";
    }

    @PostMapping("/milk")
    public String addMilk(@ModelAttribute("milkDto") MilkDto milk) throws ParseException {
        logger.info("Price: {}", milk.getPrice());
        logger.info("Date: {}", milk.getDate());
        milkService.storeMilkData(milk);
        return "redirect:home";
    }

    @GetMapping("/milk")
    public String getMilkDetails(Model model) {
        List<MilkDto> milkDtos = milkService.getMilkDetails();
        int sum = 0;
        for (MilkDto dto: milkDtos)
            sum += dto.getPrice();

        model.addAttribute("milkDetails", milkDtos);
        model.addAttribute("total", sum);
        return "milk";
    }
}
