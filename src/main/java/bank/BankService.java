package bank;

import lombok.extern.slf4j.Slf4j;
import readers.*;
import writers.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class BankService {

    private final ConsoleService consoleService = new ConsoleService();
    private List<Payment> payments = new ArrayList<>();
    private final JsonReader jsonReader = new JsonReader();
    private final CsvReader csvReader = new CsvReader();
    private final XmlReader xmlReader = new XmlReader();
    private final YamlReader yamlReader = new YamlReader();

    private final JsonWriter jsonWriter = new JsonWriter();
    private final CsvWriter csvWriter = new CsvWriter();
    private final XmlWriter xmlWriter = new XmlWriter();
    private final YamlWriter yamlWriter = new YamlWriter();

    public void runApp() throws IOException, ParserConfigurationException {
        String extension = getFileExtensionByInput();
        TransactionReader reader = getReaderByFileExtension(extension);
        TransactionWriter writer = getWriterByFileExtension(extension);
        payments = reader.readDataFromFile();
        printPaymentList(payments);
        if (suggestToEditData()) {
            editFile(payments);
        }
        writer.writeDataToFile(payments);
    }

    private TransactionReader getReaderByFileExtension(String extension) {
        switch (extension) {
            case "json" -> {
                return jsonReader;
            }
            case "xml" -> {
                return xmlReader;
            }
            case "csv" -> {
                return csvReader;
            }
            case "yaml" -> {
                return yamlReader;
            }
            default -> throw new IllegalArgumentException("unexpected file extension");
        }
    }

    private TransactionWriter getWriterByFileExtension(String extension) {
        switch (extension) {
            case "json" -> {
                return jsonWriter;
            }
            case "xml" -> {
                return xmlWriter;
            }
            case "csv" -> {
                return csvWriter;
            }
            case "yaml" -> {
                return yamlWriter;
            }
            default -> throw new IllegalArgumentException("unexpected file extension");
        }
    }

    private String getFileExtensionByInput() throws IOException {
        System.out.println("From which file do you want to get transaction data?\n" +
                "Select one of the available formats: json, xml, csv, yaml");

        String input = consoleService.getInput();
        while (!"json".equals(input) && !"xml".equals(input) && !"csv".equals(input) && !"yaml".equals(input)) {
            System.out.println("Invalid input. Please try again");
            input = consoleService.getInput();
        }
        return input;
    }

    private void printPaymentList(List<Payment> payments) {
        System.out.println(payments.toString());
    }

    private void editFile(List<Payment> payments) throws IOException {
        System.out.println("Enter transaction number you want to edit");

        int number = getTransactionNumber();
        Payment payment = new Payment();
        try {
            payment = payments.get(number - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No transaction with this number was found");
        }

        String fieldToEdit = findFieldToEdit();
        editField(fieldToEdit, payment);
    }

    private int getTransactionNumber() throws IOException {
        int number = 0;
        while (number <= 0 || number > payments.size()) {
            try {
                number = Integer.parseInt(consoleService.getInput());
            } catch (NumberFormatException e) {
                System.out.println("Please enter transaction number");
                continue;
            }
            if (number < 0 || number > payments.size()) {
                System.out.println("No transaction with this number was found");
            }
        }
        return number;
    }

    private String findFieldToEdit() throws IOException {
        System.out.println("which field do you want to edit?");
        String fieldToEdit = consoleService.getInput();
        while (!Set.of("cardNumber", "amount", "currency", "dayTime", "purpose", "status").contains(fieldToEdit)) {
            System.out.println("There`s no such field, Please try again");
            fieldToEdit = consoleService.getInput();
        }
        return fieldToEdit;
    }

    private void editField(String field, Payment payment) throws IOException {
        System.out.println("Enter a new value");
        String newValue = consoleService.getInput();
        switch (field) {
            case "cardNumber" -> payment.setCardNumber(newValue);
            case "amount" -> payment.setAmount(Integer.parseInt(newValue));
            case "currency" -> payment.setCurrency(newValue);
            case "dayTime" -> payment.setDayTime(newValue);
            case "purpose" -> payment.setPurpose(newValue);
            case "status" -> payment.setStatus(newValue);
        }
        log.info("Field {} was changed. New value is: {}", field, newValue);
    }

    private boolean suggestToEditData() throws IOException {
        System.out.println("Do you want to edit some field? Enter YES to edit or enter NO to exit");
        String string = "";
        while (!Set.of("yes", "no").contains(string.toLowerCase())) {
            System.out.println("Please make your choice");
            string = consoleService.getInput();
        }
        if ("yes".equalsIgnoreCase(string)) {
            return true;
        } else if ("no".equalsIgnoreCase(string)) {
            return false;
        }
        return false;
    }
}
