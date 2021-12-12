package ir.maktab58.exceptions;

import lombok.Builder;

/**
 * @author Taban Soleymani
 */
public class BankSysException extends RuntimeException {
    int errorCode;

    @Builder
    public BankSysException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
