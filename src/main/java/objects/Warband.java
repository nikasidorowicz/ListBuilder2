package objects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Warband {

    private Faction faction;
    private Unit commander;
    private List<Unit> warriors;

    public Warband(Faction faction) {
        setFaction(faction);
        setCommander(null);
        setWarriors(new ArrayList<>());
    }

    public int getPoints() {
        int points = getCommander().getTotalPoints();
        for (Unit unit : getWarriors())
            points += unit.getTotalPoints();
        return points;
    }

    public int getFiguresCount() {
        int figuresCount = getCommander().getFiguresCount();
        for (Unit unit : getWarriors())
            figuresCount += unit.getFiguresCount();
        return figuresCount;
    }

    public void chooseCommander(String commanderId) throws Exception {
        if (getFaction().getFactionHeroes().containsKey(commanderId))
            setCommander(getFaction().getFactionHeroes().remove(commanderId));
        else if (getFaction().getFactionCommanders().contains(commanderId))
            setCommander(getFaction().getUnitFactory().getUnit(commanderId));
        else
            throw new Exception("No such commander!");
    }

    public void deleteCommander() {
        if (!getFaction().getFactionCommanders().contains(getCommander().getId())) {
            getCommander().clear();
            getFaction().getFactionHeroes().put(getCommander().getId(), getCommander());
        }
        setCommander(null);
    }

    public void changeCommander(String commanderId) throws Exception {
        deleteCommander();
        chooseCommander(commanderId);
    }

    public void addWarriors(String warriorId, int number) {
        if (getFaction().getFactionWarriors().contains(warriorId))
            for (int i = 0; i < number; i++)
                getWarriors().add(getFaction().getUnitFactory().getUnit(warriorId));
    }

    public void deleteWarrior(Unit warrior) throws Exception {
        if (getWarriors().contains(warrior))
            getWarriors().remove(warrior);
        else
            throw new Exception("No such warrior!");
    }

}
