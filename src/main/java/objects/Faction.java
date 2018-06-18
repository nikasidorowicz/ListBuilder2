package objects;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class Faction {

    private String factionId;

    private Map<String, Unit> factionHeroes;
    private Set<String> factionCommanders;
    private Set<String> factionWarriors;

    private UnitFactory unitFactory;

    public Faction(String gameVersion, String factionId) {
        setFactionId(factionId);
        setUnitFactory(new UnitFactory(gameVersion, factionId));
    }

    public List<String> getAvailableCommanders() {
        List<String> commanders = new ArrayList<>();
        commanders.addAll(getFactionHeroes().keySet());
        commanders.addAll(getFactionCommanders());
        return commanders;
    }

}
