package ir.maktab58.models;

import ir.maktab58.enumeration.UpdateType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
public class UpdateInfo {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    @Column(name = "last_modified_date", updatable = false)
    private Date dateOfUpdate;
    @Enumerated(EnumType.STRING)
    private UpdateType updateType;
    @Column(name = "details", updatable = false)
    private String detail;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_owner_id")
    private Owner owner;

    @Builder(setterPrefix = "with")
    public UpdateInfo(Date dateOfUpdate, UpdateType updateType, String detail, Owner owner) {
        this.dateOfUpdate = dateOfUpdate;
        this.updateType = updateType;
        this.detail = detail;
        this.owner = owner;
    }
}
