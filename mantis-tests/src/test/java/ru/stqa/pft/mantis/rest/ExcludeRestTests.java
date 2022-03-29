package ru.stqa.pft.mantis.rest;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.tests.TestBase;

public class ExcludeRestTests extends TestBase {

    private int id = 1;

    @Test
    public void testExcludeRest() {
        skipIfNotFixedBugify(id);
    }

}
