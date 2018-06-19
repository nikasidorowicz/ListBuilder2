package objects;

import lombok.Data;

@Data
public class Model {

    private String name;
    private int move;
    private int fightSkill;
    private int shootSkill;
    private String fight;
    private int strength;
    private int defence;
    private int attacks;
    private int wounds;
    private int courage;
    private int might;
    private int will;
    private int fate;

    public void setFight() {
        setFight(String.format("%s/%s+", getFightSkill(), getShootSkill()));
    }

}
