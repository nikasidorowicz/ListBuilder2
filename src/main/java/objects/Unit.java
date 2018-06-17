package objects;

import lombok.Data;
import objects.attributes.Wargear;

import java.util.ArrayList;
import java.util.List;

@Data
public class Unit {

    private String name;
    private int points;
    private int unitCount;
    private List<Figure> figures;
    private List<Wargear> options;
    private List<Wargear> bought;

    public String getNameHeader() {
        return String.format("%s(%s)", getName(), getRaces());
    }

    public String getPointsHeader() {
        if (getTotalPoints() != getPoints())
            return String.format("%s(%s)", getPoints(), getTotalPoints());
        else
            return String.valueOf(getPoints());
    }

    private List<String> getRacesList() {
        List<String> races = new ArrayList<>();
        for (Figure figure : getFigures()) races.add(figure.getRace());
        return races;
    }

    private String getRaces() {
        return String.join(", ", getRacesList());
    }

    public int getTotalPoints() {
        int points = 0;
        for (Wargear option : getBought())
            points += option.getPoints();
        return getPoints() + points;
    }

    public void buyOption(String name) throws Exception {
        for (Wargear option : getOptions())
            if (option.getName().equals(name)) {
                getBought().add(option);
                return;
            }
        throw new Exception("Wrong option name!");
    }

    public void removeOption(String name) throws Exception {
        for (Wargear option : getBought())
            if (option.getName().equals(name)) {
                getBought().remove(option);
                return;
            }
        throw new Exception("Wrong option name!");
    }

}
