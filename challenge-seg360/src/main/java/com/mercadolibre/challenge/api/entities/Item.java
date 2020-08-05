package com.mercadolibre.challenge.api.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "TBL_ITEMS")
public class Item {

	@Id
	private String id;
	private String title;
	@Column(name = "category_id")
	private String categoryId;
	private Double price;
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "stop_time")
	private Date stopTime;
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Child> children;

}
