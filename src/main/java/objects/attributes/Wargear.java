package objects.attributes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Wargear extends Attribute {

    private String id;
    private int points;
    private boolean isMissile;
    private int strength;
    private int range;

}
