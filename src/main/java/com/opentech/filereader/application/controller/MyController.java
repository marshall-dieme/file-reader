package com.opentech.filereader.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opentech.filereader.application.model.MyModel;
import com.opentech.filereader.application.service.MyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/file-reader")
public class MyController {
    private final MyService service;

    @PostMapping
    public ResponseEntity<List<MyModel>> readFile(@RequestParam("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(service.readAndSave(file), HttpStatus.CREATED);
        }
        catch (Exception err) {
            log.error("An error occurs : ${}", err);
        }

        return ResponseEntity.badRequest().body(null);
    }
}