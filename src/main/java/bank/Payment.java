package bank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private int transactionNumber;
    private String cardNumber;
    private int amount;
    private String currency;
    private String dayTime;
    private String purpose;
    private String status;

    @Override
    public String toString() {
        return "Payment{" +
                "transactionNumber = " + transactionNumber +
                ", cardNumber = '" + cardNumber + '\'' +
                ", amount = " + amount +
                ", currency = '" + currency + '\'' +
                ", dayTime = '" + dayTime + '\'' +
                ", purpose = '" + purpose + '\'' +
                ", status = '" + status + '\'' +
                '}';
    }
}
