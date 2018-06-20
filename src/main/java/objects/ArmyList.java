package objects;

import lombok.Data;

import java.util.*;

@Data
public class ArmyList {

    private String sideOfConflict;
    private int pointsLimit;

    private Components components;

    private List<Warband> warbands;
    private Figure leader;
    private int bowsLimit;
    private int bowsCount;

    public ArmyList(String sideOfConflict, int pointsLimit, Components components) {
        setSideOfConflict(sideOfConflict);
        setPointsLimit(pointsLimit);

        setComponents(components);

        setWarbands(new ArrayList<>());
    }

    public Set<String> getAvailableAllies() {
        Set<String> availableAllies = getComponents().getFactionMap().get(getSideOfConflict()).keySet();

        for (Warband warband : getWarbands())
            availableAllies.retainAll(warband.getFaction().getAllies());

        return availableAllies;
    }

    public void addWarband(Faction faction) {
        getWarbands().add(new Warband(faction));
    }

    public void removeWarband(Warband warband) throws Exception {
        if (getWarbands().contains(warband))
            getWarbands().remove(warband);
        else
            throw new Exception("No such warband!");
    }

    // TODO: add bow limit counting.
    public String getSummary() {
        return String.format("%s/%s\nFigures: %s\nBreak: %s\n", getPoints(), getPointsLimit(), getFiguresCount(), getBreakPoint());
    }

    public String getList() {
        return "";
    }

    private int getPoints() {
        int points = 0;
        for (Warband warband : getWarbands())
            points += warband.getPoints();
        return points;
    }

    private int getFiguresCount() {
        int figuresCount = 0;
        for (Warband warband : getWarbands())
            figuresCount += warband.getFiguresCount();
        return figuresCount;
    }

    private int getBreakPoint() {
        // halved figures count rounding up.
        return (getFiguresCount() + 1) / 2;
    }

}
