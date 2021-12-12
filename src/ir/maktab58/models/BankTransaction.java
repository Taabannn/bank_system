package ir.maktab58.models;

import ir.maktab58.enumeration.TransactionType;
import ir.maktab58.models.factory.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Taban Soleymani
 */
@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@ToString
public class BankTransaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", updatable = false)
    private TransactionType transactionType;
    @CreationTimestamp
    @Column(name = "transaction_date", updatable = false)
    private Date dateOfTransaction;
    @Column(name = "details", updatable = false)
    private String details;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_account_id", updatable = false)
    private Account account;
}
