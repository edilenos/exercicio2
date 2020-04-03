package br.com.navita.patrimonymanagement.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "TB_USER")
public class UserEntity implements Serializable, UserDetails {

	private static final long serialVersionUID = -7384271418733330983L;
	
	public UserEntity(String name, String email, String password) {
		super();
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
	}

	@Id
	@SequenceGenerator(sequenceName = "SQ_USER_ID", name = "SEQ_USER_ID", allocationSize = 1)
	@GeneratedValue(generator = "SEQ_USER_ID", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME", nullable=false, length = 60)
	private String name;
	
	@Column(name = "EMAIL", nullable=false, length = 60, unique = true)
	private String email;
	
	@Column(name = "PASSWORD", nullable=false, length = 100)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<ProfileEntity> profiles = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getProfiles();
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
