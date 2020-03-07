package io.github.maciejlagowski.prz.project.test.unit.bik;


import io.github.maciejlagowski.prz.project.model.bik.BikApi;
import io.github.maciejlagowski.prz.project.model.bik.BikReport;
import org.junit.Assert;
import org.junit.Test;

public class BikApiTest {

    @Test
    public void getBikReportTestNotNull() {
        BikReport bikReport = new BikApi("123456789")
                .getBikReport(123456789L);
        Assert.assertNotNull(bikReport);
    }
    @Test
    public void getBikReportTestNull() {
        BikReport bikReport = new BikApi("954321")
                .getBikReport(123456789L);
        Assert.assertNull(bikReport);
    }

}
