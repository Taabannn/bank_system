package ir.maktab58.models;

import ir.maktab58.enumeration.OwnerRecord;
import ir.maktab58.models.factory.Account;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taban Soleymani
 */
@Entity
@Table(name = "owner")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"nationalCode"})
@ToString(of = {"id", "name", "nationalCode", "ownerRecord", "accounts"})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "family")
    private String family;
    @Column(name = "national_code", updatable = false)
    private long nationalCode;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    private OwnerRecord ownerRecord = OwnerRecord.NO_RECORD;
    @OneToMany(mappedBy = "owner")
    private List<Account> accounts = new ArrayList<>();
    @OneToMany(mappedBy = "owner")
    private List<UpdateInfo> lastUpdates = new ArrayList<>();
    @Transient
    private int numOfDishonoredCheques = 0;
    @Transient
    private long amountOfDebt = 0;

    @Builder(setterPrefix = "with")
    public Owner(String name, String family, long nationalCode, String username, String password, String email) {
        this.name = name;
        this.family = family;
        this.nationalCode = nationalCode;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
