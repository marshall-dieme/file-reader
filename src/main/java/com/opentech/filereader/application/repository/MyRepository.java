package com.opentech.filereader.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.opentech.filereader.application.model.MyModel;

@Repository
public interface MyRepository extends JpaRepository<MyModel, String> {
    
}