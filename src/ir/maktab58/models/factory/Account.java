package ir.maktab58.models.factory;

import ir.maktab58.models.BankTransaction;
import ir.maktab58.models.Card;
import ir.maktab58.models.Owner;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Taban Soleymani
 */
@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type",
        discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"accountNumber"})
@ToString(of = {"id", "accountNumber", "balance", "card", "creationDate"})
public abstract class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    protected int id;
    @Column(name = "account_number")
    protected long accountNumber;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_card_id")
    protected Card card;
    @CreationTimestamp
    @Column(name = "creation_date", updatable = false)
    protected Date creationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_owner_id", updatable = false)
    protected Owner owner;
    @Column(name = "current_balance")
    protected long balance;
    @OneToMany(mappedBy = "account")
    protected List<BankTransaction> transactions;

    public Account(long accountNumber, Date creationDate, Owner owner, long balance) {
        this.accountNumber = accountNumber;
        this.creationDate = creationDate;
        this.owner = owner;
        this.balance = balance;
    }
}
