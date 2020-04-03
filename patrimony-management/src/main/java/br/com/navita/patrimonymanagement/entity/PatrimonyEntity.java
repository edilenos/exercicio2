package br.com.navita.patrimonymanagement.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "TB_PATRIMONY")
public class PatrimonyEntity implements Serializable {

	private static final long serialVersionUID = -7384271418733330983L;

	@Id
	@SequenceGenerator(sequenceName = "SQ_PATRIMONY_ID", name = "SEQ_PATRIMONY_ID", allocationSize = 1)
	@GeneratedValue(generator = "SEQ_PATRIMONY_ID", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	public PatrimonyEntity(String name, String description, BrandEntity brand) {
		super();
		this.setName(name);
		this.setDescription(description);
		this.setBrand(brand);
	}
	
	@Column(name = "NAME", nullable = false, length = 60)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 300)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "ID_BRAND", nullable = false)
	private BrandEntity brand;
	
}
