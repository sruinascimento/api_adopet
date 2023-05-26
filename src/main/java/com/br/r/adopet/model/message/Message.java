package com.br.r.adopet.model.message;

public enum Message {
    TUTOR_NOT_FOUND("\"message\":\"Não encontrado\""),
    TUTOR_DELETED("\"message\":\"Tutor deletado com sucesso\""),
    TUTOR_NOT_DELETED("\"message\":\"Falha ao deletar tutor\"");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
