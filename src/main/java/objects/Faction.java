package objects;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Data
public class Faction {

    private String sideOfConflict;
    private List<String> allies;

    private String factionId;
    private String factionName;

    private Map<String, Unit> factionHeroes;
    private List<String> factionCommanders;
    private List<String> factionWarriors;

    private UnitFactory unitFactory;

    public Faction(String gameVersion, String factionId) throws Exception {
        setFactionId(factionId);
        setUnitFactory(new UnitFactory(gameVersion, factionId));
        loadFromJson(gameVersion, factionId);
    }

    private void loadFromJson(String gameVersion, String factionId) throws Exception {

        Gson g = new Gson();
        ClassLoader classLoader = Faction.FactionBase.class.getClassLoader();
        String path = String.format("%s/%s/%s.json", gameVersion, factionId, factionId);
        File file = new File(classLoader.getResource(path).getFile());

        try (JsonReader jsonReader = new JsonReader(new FileReader(file))) {
            FactionBase base = g.fromJson(jsonReader, Faction.FactionBase.class);
            if (!factionId.equals(base.getFactionId())) throw new Exception("Wrong faction ID!");

            setSideOfConflict(base.getSideOfConflict());
            setAllies(base.getAllies());
            setFactionName(base.getFactionName());
            setFactionCommanders(base.getFactionCommanders());
            setFactionWarriors(base.getFactionWarriors());

            setFactionHeroes(new HashMap<>());
            for (String heroId : base.getFactionHeroes())
                getFactionHeroes().put(heroId, getUnitFactory().getUnit(heroId));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: this can surely be done more efficiently...
    @Data
    private class FactionBase {
        private String sideOfConflict;
        private List<String> allies;
        private String factionId;
        private String factionName;
        private List<String> factionHeroes;
        private List<String> factionCommanders;
        private List<String> factionWarriors;
    }

    public List<String> getAvailableCommanders() {
        List<String> commanders = new ArrayList<>();
        commanders.addAll(getFactionHeroes().keySet());
        commanders.addAll(getFactionCommanders());
        return commanders;
    }

}
