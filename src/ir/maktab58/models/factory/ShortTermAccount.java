package ir.maktab58.models.factory;

import ir.maktab58.models.Owner;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorValue("short-term")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShortTermAccount extends Account {
    private final static double interest = 0.08;

    @Builder(setterPrefix = "with")
    public ShortTermAccount(long accountNumber, Date creationDate, Owner owner, long balance) {
        super(accountNumber, creationDate, owner, balance);
    }
}
