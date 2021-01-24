package io.github.maciejlagowski.prz.project.model.bik;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BikApi {

    private boolean authorised = false;

    public BikApi(String key) {
        if (key.equals("123456789")) {
            authorised = true;
        } else {
            System.err.println("Bad authorisation");
        }
    }

    public BikReport getBikReport(Long pesel) {
        if (authorised) {
            return generateBikReport(pesel);
        } else {
            System.err.println("Not authorised to do this");
            return null;
            // as clean code rules method should not return null, but real api won't throw an exception
        }
    }

    public BikReport generateBikReport(Long pesel) {
        BikReport report = new BikReport();
        report.setLiabilities(generateLiabilities());
        report.setPesel(pesel);
        return report;
    }

    public List<BikLiability> generateLiabilities() {
        final int MAX_LIABILITIES_TO_GEN = 6;
        List<BikLiability> liabilities = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(MAX_LIABILITIES_TO_GEN); i++) {
            BikLiability liability = new BikLiability();
            liability.setId(random.nextLong());
            liability.setAmount(random.nextDouble() % 500000 + 5000);
            liability.setAmountRepaid(liability.getAmount() * random.nextDouble());
            liability.setIsPaidOff(liability.getAmountRepaid().equals(liability.getAmount()));
            liability.setTakeDate(new Date((long) (System.currentTimeMillis() * (random.nextDouble()))));
            liability.setInstallment(liability.getAmount() / 96);
//            liability.setIsDefault(!liability.getIsPaidOff() && random.nextBoolean());
//            it was generating too many defaults
            liability.setIsDefault(false);
            liabilities.add(liability);
        }
        return liabilities;
    }
}
