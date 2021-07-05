package com.SuperemeAppealReporter.api.io.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "case_topic")
public class CaseTopicEntity extends BaseEntity{

	@Column(name = "topic", nullable = true)
	private String topic;
	
	/**------------------------Mappings-------------------------**/

	@ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
	private CaseEntity caseEntity;
}
