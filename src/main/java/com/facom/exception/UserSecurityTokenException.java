package com.facom.exception;

import com.facom.domain.OperationStatus;

public class UserSecurityTokenException extends Exception {
    private OperationStatus operationStatus;

    public UserSecurityTokenException(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
