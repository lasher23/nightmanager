package ch.uhc_yetis.nightmanager.adapter.rest;

import java.time.LocalDateTime;

public class HttpErrorObject {
    private LocalDateTime timestamp;
    private String path;
    private String message;
    private String error;

    private HttpErrorObject() {
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getPath() {
        return this.path;
    }

    public String getMessage() {
        return this.message;
    }

    public String getError() {
        return this.error;
    }

    public static PathBuilder withTimestamp(LocalDateTime timestamp) {
        return new HttpErrorObjectBuilder().withTimestamp(timestamp);
    }

    private static final class HttpErrorObjectBuilder implements TimestampBuilder, PathBuilder, MessageBuilder, ErrorBuilder, OptionalFieldsBuilder {

        private HttpErrorObject errorObject;

        public HttpErrorObjectBuilder() {
            this.errorObject = new HttpErrorObject();
        }

        @Override
        public PathBuilder withTimestamp(LocalDateTime timestamp) {
            this.errorObject.timestamp = timestamp;
            return this;
        }

        @Override
        public MessageBuilder withPath(String path) {
            this.errorObject.path = path;
            return this;
        }

        @Override
        public ErrorBuilder withMessage(String message) {
            this.errorObject.message = message;
            return this;
        }

        @Override
        public OptionalFieldsBuilder withError(String error) {
            this.errorObject.error = error;
            return this;
        }

        @Override
        public HttpErrorObject build() {
            return this.errorObject;
        }
    }

    public interface TimestampBuilder {
        PathBuilder withTimestamp(LocalDateTime timestamp);
    }

    public interface PathBuilder {
        MessageBuilder withPath(String path);
    }

    public interface MessageBuilder {
        ErrorBuilder withMessage(String message);
    }

    public interface ErrorBuilder {
        OptionalFieldsBuilder withError(String error);
    }

    public interface OptionalFieldsBuilder {
        HttpErrorObject build();
    }
}
