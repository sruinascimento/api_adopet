package com.br.r.adopet.model.error;

import lombok.ToString;

public enum ErrorMessage {
    TUTOR_NOT_FOUND("\"message\":\"Não encontrado\"");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
