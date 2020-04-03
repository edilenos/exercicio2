package br.com.navita.patrimonymanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "TB_BRAND")
public class BrandEntity implements Serializable {

	private static final long serialVersionUID = -7384271418733330983L;

	public BrandEntity(String name) {
		super();
		this.setName(name);
	}
	
	@Id
	@SequenceGenerator(sequenceName = "SQ_BRAND_ID", name = "SEQ_BRAND_ID", allocationSize = 1)
	@GeneratedValue(generator = "SEQ_BRAND_ID", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME", nullable=false, length = 60, unique = true)
	private String name;
	
}
