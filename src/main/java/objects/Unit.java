package objects;

import lombok.Data;
import objects.attributes.Mount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: add copies support for cleaner Warband view.
@Data
public class Unit {

    private String id;
    private String name;
    private int points;
    private int figuresCount;
    private List<Figure> figures;
    private Map<String, Integer> options;
    private Map<String, Integer> bought;
    private Mount mount;

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
        for (int optionCost : getBought().values())
            points += optionCost;
        return getPoints() + points;
    }

    public List<Model> getModels() {
        List<Model> models = new ArrayList<>();
        for (Figure figure : getFigures())
            models.add(figure.getModel());
        if (getMount() != null)
            models.add(getMount().getModel());
        return models;
    }

    // TODO: handle problem of multiple figures with different wargear.
    public List<String> getAllWargear() {
        List<String> wargear = new ArrayList<>();
        wargear.addAll(getFigures().get(0).getWargears());
        wargear.addAll(getBought().keySet());
        return wargear;
    }

    public void buyOption(String id) throws Exception {
        for (Map.Entry<String, Integer> option : options.entrySet())
            if (option.getKey().equals(id)) {
                getBought().put(option.getKey(), option.getValue());
                return;
            }
        throw new Exception("Wrong option name!");
    }

    public void removeOption(String name) throws Exception {
        for (String optionName : getBought().keySet())
            if (optionName.equals(name)) {
                getBought().remove(optionName);
                return;
            }
        throw new Exception("Wrong option name!");
    }

    public void clear() {
        setMount(null);
        setBought(new HashMap<>());
    }

}
