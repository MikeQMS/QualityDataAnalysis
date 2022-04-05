package com.example.qdata.dao;

import com.example.qdata.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDAO extends JpaRepository<Data, Long> {


}
