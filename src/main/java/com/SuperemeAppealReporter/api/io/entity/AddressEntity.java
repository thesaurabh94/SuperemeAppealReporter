package com.SuperemeAppealReporter.api.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity extends BaseEntity {

	
	@Column(name = "full_address", nullable = true)
	private String address;
	
	
	@Column(name = "zipcode", nullable = true)
	private String zipcode;
	

	
	
	/**------------------------Mappings-------------------------**/
	
	@OneToOne
	private StateEntity stateEntity;
	
	@OneToOne
	private CityEntity cityEntity;
	
	@OneToOne
	private CountryEntity countryEntity;
	
}

