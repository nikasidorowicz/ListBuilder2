package objects.attributes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MagicPower extends Attribute {

    private String duration;
    private int range;
    private int castingValue;
    private String channeledRules;

}
