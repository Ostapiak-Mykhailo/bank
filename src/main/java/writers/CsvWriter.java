package writers;

import bank.Payment;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CsvWriter implements TransactionWriter {

    @Override
    public void writeDataToFile(List<Payment> payment) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = CsvSchema.builder()
                .addColumn("transactionNumber")
                .addColumn("cardNumber")
                .addColumn("amount")
                .addColumn("currency")
                .addColumn("dayTime")
                .addColumn("purpose")
                .addColumn("status")
                .setUseHeader(true)
                .build();
        try {
            File transactions = new File("src/main/resources/transactions.csv");
            csvMapper.writer(csvSchema).writeValue(transactions, payment);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
