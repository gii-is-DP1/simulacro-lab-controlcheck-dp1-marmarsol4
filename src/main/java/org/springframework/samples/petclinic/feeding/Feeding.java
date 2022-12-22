package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "feedings")
public class Feeding extends BaseEntity {
	
	//private Integer id;
	
	@NotNull
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate startDate;
	
	@NotNull
	@Min(1) // Mayor o igual a 1
	@Column(name = "weeks_duration")
	private Double weeksDuration;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@NotNull // aunque no me digan nada, not null
	@ManyToOne
	@JoinColumn(name = "feeding_type_id")
	private FeedingType feedingType; // "name" en el form es el nombre del atributo en la clase
	
}