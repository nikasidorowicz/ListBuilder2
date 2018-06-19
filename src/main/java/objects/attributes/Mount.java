package objects.attributes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import objects.Model;

@EqualsAndHashCode(callSuper = true)
@Data
public class Mount extends Attribute {

    private String race;
    private Model model;

}
