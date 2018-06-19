package objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Data;
import objects.attributes.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Data
public class Components {

    private Gson gson;

    private Map<String, Wargear> wargearMap;
    private Map<String, SpecialRule> specialRuleMap;
    private Map<String, MagicPower> magicPowerMap;
    private Map<String, Mount> mountMap;

    public Components(String gameVersion) {
        RuntimeTypeAdapterFactory<Attribute> rta = RuntimeTypeAdapterFactory.of(Attribute.class)
                .registerSubtype(Wargear.class)
                .registerSubtype(MagicPower.class)
                .registerSubtype(SpecialRule.class)
                .registerSubtype(Mount.class);
        setGson(new GsonBuilder()
                .registerTypeAdapterFactory(rta)
                .create());

        System.out.println(fetchAttributes(gameVersion, "wargear"));
        System.out.println(fetchAttributes(gameVersion, "specialRules"));
        // TODO: remember about fight of horses
        System.out.println(fetchAttributes(gameVersion, "mounts"));
        System.out.println(fetchAttributes(gameVersion, "magicPowers"));
    }

    private HashMap<String, Attribute> fetchAttributes(String gameVersion, String attributeId) {
        HashMap<String, Attribute> attributes = new HashMap<>();
        String path = String.format("%s/%s.json", gameVersion, attributeId);
        ClassLoader classLoader = Components.class.getClassLoader();

        try {
            File file = new File(classLoader.getResource(path).getFile());
            try (JsonReader jsonReader = new JsonReader(new FileReader(file))) {
                List<Attribute> attributesFromFile = Arrays.asList(getGson().fromJson(jsonReader, Attribute[].class));
                for (Attribute attribute : attributesFromFile)
                    attributes.put(attribute.getId(), attribute);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            System.out.println(String.format("%s not found", path));
        }

        return attributes;
    }

}
