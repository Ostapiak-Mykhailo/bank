package writers;

import bank.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonWriter implements TransactionWriter {

    @Override
    public void writeDataToFile(List<Payment> payment) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(
                    "src/main/resources/transactions.json"), payment);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
