package objects.attributes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MissileWargear extends Wargear {

    private int strength;
    private int range;

    @Override
    public boolean isMissile() {
        return true;
    }

}
