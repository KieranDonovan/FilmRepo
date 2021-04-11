package ie.director.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NewFilmForm {
	@Size(min=4, max=30)
	private String newFilmName;
	
	@NotNull
	private int directorId;

}
