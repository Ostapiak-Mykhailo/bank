package writers;

import bank.Payment;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YamlWriter implements TransactionWriter {

    @Override
    public void writeDataToFile(List<Payment> payment) {
        YAMLMapper mapper = new YAMLMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(
                    new File("src/main/resources/transactions.yaml"), payment);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
