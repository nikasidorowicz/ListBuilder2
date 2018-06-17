package objects;

import lombok.Data;

import java.util.List;

@Data
public class ArmyList {

    private String sideOfConflict;
    private int pointsLimit;
    private int points;
    private List<Warband> warbands;
    private int unitCount;
    private int breakPoint; // Half of unit count rounding up
    private Figure leader;
    private int bowsLimit;
    private int bowsCount;

}
