package readers;

import bank.Payment;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvReader implements TransactionReader {

    @Override
    public List<Payment> readDataFromFile() throws IOException {
        byte[] csvData = Files.readAllBytes(Paths.get("src/main/resources/transactions.csv"));
        CsvMapper mapper = new CsvMapper();
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

        return mapper.readerFor(Payment.class).with(csvSchema).<Payment>readValues(csvData).readAll();
    }
}
