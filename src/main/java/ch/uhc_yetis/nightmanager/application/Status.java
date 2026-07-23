package ch.uhc_yetis.nightmanager.application;

public enum Status {
    NOT_FOUND(404), NOT_EXISTING(404), ALREADY_EXISTS(400), MAIL_FAILED(502);

    private final int httpCode;

    Status(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return this.httpCode;
    }
}
