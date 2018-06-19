package objects;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import objects.attributes.MagicPower;
import objects.attributes.SpecialRule;
import objects.attributes.Wargear;

import java.util.List;

@Data
public class Figure {

    private String name;
    private String race;
    private Model model;
    private List<String> wargears;
    private List<String> specialRules;
    private List<String> magicPowers;

}
