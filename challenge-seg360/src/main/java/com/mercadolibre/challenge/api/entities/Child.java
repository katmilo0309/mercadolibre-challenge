package com.mercadolibre.challenge.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "TBL_CHILDS")
public class Child {
	
	@Id
	private String id;
	@Column(name = "stop_time")
	private Date stopTime;
	@ManyToOne
    @JoinColumn(name = "FK_INVOICE", nullable = false, updatable = false)
	private Item parent;
	
	

}
