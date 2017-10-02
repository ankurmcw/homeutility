package com.mcw.homeutility.job;

import com.mcw.homeutility.dto.MilkDto;
import com.mcw.homeutility.exception.AppException;
import com.mcw.homeutility.service.MilkService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static com.mcw.homeutility.utility.CommonUtils.getDate;
import static com.mcw.homeutility.utility.AppConstants.YES;

/**
 * Created by renuka on 30/9/17.
 */

@Component
public class MilkJob {

    private static Logger logger = LoggerFactory.getLogger(MilkJob.class);

    @Autowired
    private MilkService milkService;

    @Value("${milk.price}")
    private int price;

    @Value("${milk.quantity}")
    private int quantity;

    @Scheduled(cron = "#{@cronAddMilk}")
    public void addMilk() {
        logger.info("Job for adding milk has started");
        try {
            String date = getDate(new Date());
            MilkDto milkDto = milkService.getMilkDetail(date);
            if (milkDto == null) {
                milkDto = new MilkDto();
                milkDto.setDate(date);
                milkDto.setPrice(price);
                milkDto.setQuantity(quantity);
                milkDto.setDelivered(YES);
                milkService.storeMilkData(milkDto);
                logger.info("Milk details added");
            } else {
                logger.info("Milk detail already exist");
            }

        } catch (AppException | ParseException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
