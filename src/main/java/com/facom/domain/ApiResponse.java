package com.facom.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

import static com.facom.domain.OperationStatus.SUCCESSFUL_OPERATION;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<S,B> {
    private final S operationStatus;
    @Nullable
    private final B body;

    public ApiResponse(S status, @Nullable B body) {
        this.operationStatus = status;
        this.body = body;
    }

    public S getOperationStatus() {
        return operationStatus;
    }

    @Nullable
    public B getBody() {
        return body;
    }
}
