package com.example.repo;

//import org.springframework.data.jpa.repo.JpaRepository;
import com.example.entity.Maintenance;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepo extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByAutomobileId(Long automobileId);
}

