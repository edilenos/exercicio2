package br.com.navita.patrimonymanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_PROFILE")
public class ProfileEntity implements GrantedAuthority {
	
	private static final long serialVersionUID = -651057048064865126L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Override
	public String getAuthority() {
		return name;
	}
	
}