package com.example.entity;

//import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "maintenance")
public class Maintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Automobile is manditory")
	private Long automobileId;

	// @Temporal(TemporalType.DATE)
	private LocalDate date = LocalDate.now();
	
	@NotBlank
	@NotNull
	private String description;

	// Getters and Setters

	public Maintenance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Maintenance( Long automobileId, LocalDate date, String description) {
		super();
		this.automobileId = automobileId;
		this.date = date;
		this.description = description;
	}

	public Long getAutomobileId() {
		return automobileId;
	}

	public void setAutomobileId(Long automobileId) {
		this.automobileId = automobileId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

//package com.example.model;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//public class Maintenance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "automobileId")
//    private Automobile automobile;
//
//    @Temporal(TemporalType.DATE)
//    private Date date;
//
//    private String description;
//
//    // Getters and Setters
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Automobile getAutomobile() {
//        return automobile;
//    }
//
//    public void setAutomobile(Automobile automobile) {
//        this.automobile = automobile;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}
