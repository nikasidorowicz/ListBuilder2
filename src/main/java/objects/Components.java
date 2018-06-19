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

        // TODO: refactor using https://stackoverflow.com/questions/8482761/returning-an-extended-class-where-super-class-is-expected
        // WARGEAR
        Map<String, Attribute> map = fetchAttributes(gameVersion, "wargear");
        Wargear wargear;
        Map<String, Wargear> wargearMap = new HashMap<>();
        for (Map.Entry<String, Attribute> entry : map.entrySet()) {
            wargear = (Wargear) entry.getValue();
            wargearMap.put(entry.getKey(), wargear);
        }
        setWargearMap(wargearMap);

        // SPECIAL RULES
        map = fetchAttributes(gameVersion, "specialRules");
        SpecialRule specialRule;
        Map<String, SpecialRule> specialRuleMap = new HashMap<>();
        for (Map.Entry<String, Attribute> entry : map.entrySet()) {
            specialRule = (SpecialRule) entry.getValue();
            specialRuleMap.put(entry.getKey(), specialRule);
        }
        setSpecialRuleMap(specialRuleMap);

        // MOUNTS
        map = fetchAttributes(gameVersion, "mounts");
        Mount mount;
        Map<String, Mount> mountMap = new HashMap<>();
        for (Map.Entry<String, Attribute> entry : map.entrySet()) {
            mount = (Mount) entry.getValue();
            mount.getModel().setFight();
            mountMap.put(entry.getKey(), mount);
        }
        setMountMap(mountMap);

        // MAGIC POWERS
        map = fetchAttributes(gameVersion, "magicPowers");
        MagicPower magicPower;
        Map<String, MagicPower> magicPowerMap = new HashMap<>();
        for (Map.Entry<String, Attribute> entry : map.entrySet()) {
            magicPower = (MagicPower) entry.getValue();
            magicPowerMap.put(entry.getKey(), magicPower);
        }
        setMagicPowerMap(magicPowerMap);

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
