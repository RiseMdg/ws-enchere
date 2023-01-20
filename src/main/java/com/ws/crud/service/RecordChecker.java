package com.ws.crud.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ws.crud.repository.EnchereRepository;

@Service
public class RecordChecker {
    @Autowired
    private EnchereRepository enchereRepository;
    //Executes each 500 ms
    @Scheduled(fixedRate=500)
    public void checkRecords() {
        List<Timestamp> time= enchereRepository.getTime();
        for(Timestamp result: time){
            Timestamp now = new Timestamp(new Date().getTime());
            if(now.before(result)){
                enchereRepository.updateStatut(result);
            }
        }
    }
}
