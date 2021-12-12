package ir.maktab58.models.factory;

import ir.maktab58.models.Owner;
import ir.maktab58.models.factory.Account;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.annotation.processing.SupportedSourceVersion;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorValue("transaction")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = "isChequeBookEnabled")
public class TransactionAccount extends Account {
    @Column(name = "cheque_book")
    private boolean isChequeBookEnabled;

    @Builder(setterPrefix = "with")
    public TransactionAccount(long accountNumber, Date creationDate, Owner owner, long balance, boolean isChequeBookEnabled) {
        super(accountNumber, creationDate, owner, balance);
        this.isChequeBookEnabled = isChequeBookEnabled;
    }
}
