package com.mcw.homeutility.repo;

import com.mcw.homeutility.entity.Milk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by renuka on 30/9/17.
 */

@Repository
public interface MilkRepo extends JpaRepository<Milk, Date> {
    //@Query("select sum(quantity * price) from M")
    //int getTotal(Date date);
}
