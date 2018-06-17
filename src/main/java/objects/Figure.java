package objects;

import lombok.Data;
import objects.attributes.SpecialRule;
import objects.attributes.Wargear;

import java.util.List;

@Data
public class Figure {

    private String name;
    private Model model;
    private MountModel mount;
    private List<Wargear> wargears;
    private List<SpecialRule> specialRules;

}
