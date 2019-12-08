package ch.uhc_yetis.nightmanager.application;

public class CustomException extends RuntimeException {
    private String error;
    private String path;
    private String timestamp;
    private Status status;


    public CustomException(String error, Status status) {
        this.error = error;
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
