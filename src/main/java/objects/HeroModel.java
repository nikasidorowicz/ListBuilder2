package objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HeroModel extends Model {

    private int might;
    private int will;
    private int fate;

}
