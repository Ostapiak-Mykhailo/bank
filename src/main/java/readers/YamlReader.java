package readers;

import bank.Payment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class YamlReader implements TransactionReader {

    @Override
    public List<Payment> readDataFromFile() throws IOException {
        byte[] yamlData = Files.readAllBytes(Paths.get("src/main/resources/transactions.yaml"));
        YAMLMapper mapper = new YAMLMapper();
        return mapper.readValue(yamlData, new TypeReference<>() {
        });
    }
}
