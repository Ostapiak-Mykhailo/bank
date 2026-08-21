package writers;

import bank.Payment;

import java.io.IOException;
import java.util.List;

public interface TransactionWriter {

    void writeDataToFile(List<Payment> payment) throws IOException;
}
