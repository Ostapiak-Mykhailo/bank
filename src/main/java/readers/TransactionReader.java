package readers;

import bank.Payment;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface TransactionReader {

    List<Payment> readDataFromFile() throws IOException, ParserConfigurationException;
}
