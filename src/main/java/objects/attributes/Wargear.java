package objects.attributes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Wargear extends Attribute {

    public boolean isMissile() {
        return false;
    }

}
