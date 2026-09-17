package readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import bank.Payment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonReader implements TransactionReader {

    @Override
    public List<Payment> readDataFromFile() throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/transactions.json"));

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, new TypeReference<>() {
        });
    }
}
