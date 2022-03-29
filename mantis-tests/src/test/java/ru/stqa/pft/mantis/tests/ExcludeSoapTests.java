package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class ExcludeSoapTests extends TestBase {

    private int id = 1;

    @Test
    public void testExcludeSoap() {
        skipIfNotFixed(id);
    }

}
