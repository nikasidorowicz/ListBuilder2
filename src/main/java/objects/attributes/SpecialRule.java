package objects.attributes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpecialRule extends Attribute{

    private String type;

}
