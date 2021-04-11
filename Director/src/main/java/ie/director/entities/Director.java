package ie.director.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "director")
public class Director {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int directorId;
	
	@Column(nullable = false, unique = true  )
	private String directorName;
	
	// One Director has many films
	// When fetching a Director from a query, do not fetch the list of films. 
	// If the director is removed from the database, also remove the films. 
	@JsonIgnore
	@OneToMany(mappedBy = "filmDirector", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Film> directorFilms = new ArrayList<>();

	public Director(String directorName) {
		this.directorName = directorName;
	}
}