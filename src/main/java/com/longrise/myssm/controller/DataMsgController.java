package com.longrise.myssm.controller;

import java.util.List;

import com.longrise.myssm.service.DataMsgService;
import com.longrise.myssm.vo.DataMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataMsgController {
    
    @Autowired
    private DataMsgService dataMsgService;

    @GetMapping("/getAll")
    public List<DataMsg> getAll(){
        return dataMsgService.queryAll();
    }

    @GetMapping("/getOne/{id}")
    public DataMsg getOne(@PathVariable("id") int id){
        return dataMsgService.queryOne(id);
    }

    @PostMapping("/add")
    public DataMsg add(DataMsg dataMsg){
        return dataMsgService.addOne(dataMsg);
    }

    @PutMapping("/update")
    public DataMsg update(DataMsg dataMsg){
        return dataMsgService.postOne(dataMsg);
    }

    @DeleteMapping("/del/{id}")
    public boolean del(@PathVariable("id") int id){
        return dataMsgService.delOne(id);
    }
}