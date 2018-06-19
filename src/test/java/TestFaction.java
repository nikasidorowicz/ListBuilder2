import objects.Faction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFaction {

    private static Faction faction;

    @BeforeEach
    public void initFaction() {
        String gameVersion = "testVersion";
        String factionId = "faction1";
        try {
            faction = new Faction(gameVersion, factionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldLoadFromJson() {
        assertEquals("faction1", faction.getFactionId());
        assertEquals("Faction 1", faction.getFactionName());
        assertTrue(faction.getFactionCommanders().contains("testcaptain1"));
        assertTrue(faction.getFactionWarriors().contains("warrior1"));
    }

}
