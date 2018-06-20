import objects.Components;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestComponents {

    private static Components components;

    @BeforeEach
    public void initComponents() {
        String gameVersion = "testVersion";
        components = new Components(gameVersion);
    }

    @Test
    public void shouldFetchAttributes() {
        assertEquals(5, components.getWargearMap().size());
        assertEquals(0, components.getMagicPowerMap().size());
        assertEquals(3, components.getSpecialRuleMap().size());
        assertEquals(1, components.getMountMap().size());
    }

}
