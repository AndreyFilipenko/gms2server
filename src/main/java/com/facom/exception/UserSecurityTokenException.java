package com.facom.exception;

import com.facom.domain.UserOperationStatus;

public class UserSecurityTokenException extends Exception {
    private UserOperationStatus operationStatus;

    public UserSecurityTokenException(UserOperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public UserOperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(UserOperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
