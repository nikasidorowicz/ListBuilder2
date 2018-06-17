package objects;

import lombok.Data;
import objects.attributes.AvailableWargear;

import java.util.List;

@Data
public class Unit {

    private String name;
    private int points;
    private int unitCount;
    private List<Figure> figures;
    private List<AvailableWargear> options;

}
