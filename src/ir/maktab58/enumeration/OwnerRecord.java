package ir.maktab58.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Taban Soleymani
 */
@AllArgsConstructor
public enum OwnerRecord {
    NO_RECORD("no_record"),
    CREDIT_WORTHY("credit_worthy"),
    UN_TRUST_WORTHY("un-trust_worthy");

    private @Getter String ownerRecord;
}
