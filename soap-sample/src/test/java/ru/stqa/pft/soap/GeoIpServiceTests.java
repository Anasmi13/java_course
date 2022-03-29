package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("95.105.127.211");
        assertTrue(geoIP.indexOf("RU") != -1);
    }

}
