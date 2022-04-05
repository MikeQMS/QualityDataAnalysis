package com.example.qdata.controller;

import com.example.qdata.model.Data;
import com.example.qdata.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerRest {

    @Autowired
    DataService dataService;

    @RequestMapping("/all")
    public List<Data> getAllData(){
        return dataService.listData();
    }

}
