package pl.drzazga.jpa.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class BaseEntity {

	@Id
	@GeneratedValue
	private Long id;
	
	private String uuid = UUID.randomUUID().toString();
	
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || 
				obj instanceof BaseEntity && Objects.equals(uuid, ((BaseEntity) obj).uuid);
	}
}
