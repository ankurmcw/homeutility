package com.mcw.homeutility.job;

import com.mcw.homeutility.dto.MilkDto;
import com.mcw.homeutility.service.MilkService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

import static com.mcw.homeutility.utility.CommonUtils.getDate;

/**
 * Created by renuka on 30/9/17.
 */

@Component
public class MilkJob {

    private static Logger logger = LoggerFactory.getLogger(MilkJob.class);

    @Autowired
    private MilkService milkService;

    @Scheduled(cron = "#{@cronAddMilk}")
    public void addMilk() {
        logger.info("Job for adding milk has started");
        try {
            MilkDto milkDto = new MilkDto();
            milkDto.setDate(getDate(new Date()));
            milkDto.setPrice(26);
            milkDto.setQuantity(1);
            milkService.storeMilkData(milkDto);
            logger.info("Milk details added");
        } catch (ParseException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
    }
}
