package com.mgoldblat.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private Role() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@Column
	private String description;

	public enum Name {
		ADMIN("ADMIN"),
		CUSTOMER("CUSTOMER");

		private String name;

		Name(String name) {
			this.name = name;
		}
	}
}
