package definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

import static com.EvolutionGaming.Helper.*;


public class Hooks {
    @Before
    public void initialize() throws IOException {
        initializeDriver();
    }

    @After
    public void tearDown() {
        closeSession();
    }
}

