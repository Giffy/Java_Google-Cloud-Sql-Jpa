package model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Admin {
	
	@Id

    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	


	private String name;
	
	@OneToMany(mappedBy="admin", orphanRemoval=true, cascade={CascadeType.ALL})
	@CascadeOnDelete
	private Set<Gallery> gallery  = new HashSet<Gallery>();
	
	

	/* GETTERS AND SETTERS */

	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Gallery> getGallery() {
		return gallery;
	}


	public void setGallery(Set<Gallery> gallery) {
		this.gallery = gallery;
	}
	
	
}
