package ie.director.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int filmId;
	
	@Column(nullable=false)
	private String filmName;
	
	//This is the owning side of the relationship
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "director_id", nullable = false)
	private Director filmDirector;
	
	public Film(String filmName, Director filmDirector)
	{
		this.filmName = filmName;
		this.filmDirector = filmDirector;
	}
	
	
	

}
