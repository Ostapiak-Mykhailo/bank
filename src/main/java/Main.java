import bank.BankService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException {

        BankService service = new BankService();
        service.runApp();
    }
}
