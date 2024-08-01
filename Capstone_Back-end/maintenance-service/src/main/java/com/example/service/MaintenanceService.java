package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repo.MaintenanceRepo;
import com.example.exception.ResourceNotFoundException;
import com.example.entity.Maintenance;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepo maintenanceRepository;

    public List<Maintenance> getMaintenanceByAutomobileId(Long automobileId) {
        return maintenanceRepository.findByAutomobileId(automobileId);
    }
    
    public List<Maintenance> getMaintenance() {
        return maintenanceRepository.findAll();
    }

    public Maintenance getMaintenanceById(Long id) throws ResourceNotFoundException {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Maintenance not found"));
    }

    public Maintenance createMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public Maintenance updateMaintenance(Long id, Maintenance maintenance) throws ResourceNotFoundException {
        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance not found");
        }
        maintenance.setId(id); // Ensure the ID is set correctly for the update
        return maintenanceRepository.save(maintenance);
    }

    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }
}




