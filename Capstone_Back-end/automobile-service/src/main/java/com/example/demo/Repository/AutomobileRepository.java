package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Automobile;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutomobileRepository extends JpaRepository< Automobile, Long>{
	List<Automobile> findByYear(int year);
	Optional<List<Automobile>> findByManufacturerId(Long manufacturerId);
}
