package objects;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class UnitFactory {

    @Getter @Setter private String gameVersion;
    @Getter @Setter private String factionId;

    public UnitFactory(String gameVersion, String factionId) {
        setGameVersion(gameVersion);
        setFactionId(factionId);
    }

    public Unit getUnit(String unitId) {
        Gson g = new Gson();
        ClassLoader classLoader = UnitFactory.class.getClassLoader();
        String path = String.format("%s/%s/%s.json", getGameVersion(), getFactionId(), unitId);
        File file = new File(classLoader.getResource(path).getFile());

        try (JsonReader jsonReader = new JsonReader(new FileReader(file))) {
            Unit unit =  g.fromJson(jsonReader, Unit.class);
            unit.setBought(new HashMap<>());
            for (Figure figure : unit.getFigures())
                figure.getModel().setFight();
            return unit;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
