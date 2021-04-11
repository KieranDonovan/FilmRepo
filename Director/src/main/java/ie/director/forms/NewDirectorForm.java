package ie.director.forms;

import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class NewDirectorForm {
	@Size(min=3, max=30)
	private String newDirectorName;

}
