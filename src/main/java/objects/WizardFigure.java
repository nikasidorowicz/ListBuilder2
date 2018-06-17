package objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import objects.attributes.MagicPower;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class WizardFigure extends Figure {

    private List<MagicPower> magicPowers;

}
