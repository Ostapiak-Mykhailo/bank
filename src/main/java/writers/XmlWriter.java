package writers;

import bank.Payment;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlWriter implements TransactionWriter {

    @Override
    public void writeDataToFile(List<Payment> payment) {
        XmlMapper mapper = new XmlMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File
                    ("src/main/resources/transactions.xml"), payment);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
