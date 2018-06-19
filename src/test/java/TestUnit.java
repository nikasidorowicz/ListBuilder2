import objects.Figure;
import objects.Unit;
import objects.UnitFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUnit {

    public static UnitFactory factory;

    @BeforeAll
    public static void init() {
        String gameVersion = "testVersion";
        String factionId = "faction1";

        factory = new UnitFactory(gameVersion, factionId);
    }

    @Test
    public void testUnitFactory() {
        Unit hero = factory.getUnit("hero1");

        assertEquals("hero1", hero.getId());
        assertEquals("hero1", hero.getName());
        assertEquals(100, hero.getPoints());
        assertEquals(1, hero.getFiguresCount());
        assertTrue(hero.getOptions().containsKey("horse"));
        assertEquals(5, (int) hero.getOptions().get("bow"));
        assertEquals(null, hero.getMount());
        assertEquals(new ArrayList<>(), hero.getBought());

        List<Figure> figures = hero.getFigures();
        assertEquals(1, figures.size());
        Figure heroFigure = figures.get(1);

    }

}
