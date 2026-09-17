package readers;

import bank.Payment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XmlReader implements TransactionReader {

    @Override
    public List<Payment> readDataFromFile() throws IOException {
        byte[] xmlData = Files.readAllBytes(Paths.get("src/main/resources/transactions.xml"));
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(xmlData, new TypeReference<>() {
        });
    }
}
