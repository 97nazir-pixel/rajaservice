package com.rajaservice.panservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajaservice.panservice.entity.PanApplication;


public interface PanServiceRepository extends JpaRepository<PanApplication, Long> {

}
