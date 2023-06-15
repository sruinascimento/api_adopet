package br.com.rsoft.adopet.model.message;

public enum Message {
    TUTOR_NOT_FOUND("\"message\":\"Tutor not found.\""),
    TUTOR_DELETED("\"message\":\"Tutor deleted successfully.\""),
    TUTOR_NOT_DELETED("\"message\":\"Tutor was not deleted.\"");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
