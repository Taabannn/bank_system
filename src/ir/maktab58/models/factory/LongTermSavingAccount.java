package ir.maktab58.models.factory;

import ir.maktab58.enumeration.LongTermType;
import ir.maktab58.models.Owner;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorValue("long-term")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = "longTermType")
public class LongTermSavingAccount extends Account {
    @Enumerated(EnumType.STRING)
    @Column(name = "long_term_type")
    private LongTermType longTermType;

    @Builder(setterPrefix = "with")
    public LongTermSavingAccount(long accountNumber, Date creationDate, Owner owner, long balance, LongTermType longTermType) {
        super(accountNumber, creationDate, owner, balance);
        this.longTermType = longTermType;
    }
}
