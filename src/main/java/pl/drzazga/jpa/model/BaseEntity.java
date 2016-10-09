package pl.drzazga.jpa.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class BaseEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private int version;
	
	private String uuid = UUID.randomUUID().toString();
	
	public Long getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
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
