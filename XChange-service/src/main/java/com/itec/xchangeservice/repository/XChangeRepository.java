package com.itec.xchangeservice.repository;

import com.itec.xchangeservice.entity.XChangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XChangeRepository extends JpaRepository<XChangeEntity, Long> {
}
