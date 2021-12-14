package ir.maktab58.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Taban Soleymani
 */
@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "cardNumber")
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "card_number", updatable = false)
    private long cardNumber;
    @Column(name = "cvv2", updatable = false)
    private int cvv2;
    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    private Date expirationDate;

    @Builder(setterPrefix = "with")
    public Card(long cardNumber, int cvv2, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cvv2 = cvv2;
        this.expirationDate = expirationDate;
    }
}
