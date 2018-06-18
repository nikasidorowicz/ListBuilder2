package objects;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import lombok.Data;
import objects.attributes.MagicPower;
import objects.attributes.SpecialRule;
import objects.attributes.Wargear;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Data
public class Components {

    private Map<String, Wargear> wargearMap;
    private Map<String, SpecialRule> specialRuleMap;
    private Map<String, MagicPower> magicPowerMap;

    public Components(String gameVersion) {
        setWargearMap(fetchWargear(gameVersion));
    }

    // TODO: write type adapter for other attributes
    private Map<String, Wargear> fetchWargear(String gameVersion) {
        Map<String, Wargear> wargears = new HashMap<>();
        Gson g = new Gson();

        String path = String.format("%s/wargear.json", gameVersion);

        ClassLoader classLoader = Components.class.getClassLoader();

        try {
            File file = new File(classLoader.getResource(path).getFile());
            try (JsonReader jsonReader = new JsonReader(new FileReader(file))) {
                List<Wargear> wargearsFromFile = Arrays.asList(g.fromJson(jsonReader, Wargear[].class));
                for (Wargear wargear : wargearsFromFile)
                    wargears.put(wargear.getId(), wargear);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            System.out.println(String.format("%s not found", path));
        }

        return wargears;
    }

}
