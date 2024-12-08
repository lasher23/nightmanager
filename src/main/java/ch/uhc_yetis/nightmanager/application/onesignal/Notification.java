package ch.uhc_yetis.nightmanager.application.onesignal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Notification {
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("target_channel")
    private String targetChannel;
    @JsonProperty("included_segments")
    private List<String> includedSegments;
    private List<Filter> filters;
    private Content contents;
    private String url;

    public Notification(String appId, String targetChannel, List<String> includedSegments, List<Filter> filters, Content contents, String url) {
        this.appId = appId;
        this.targetChannel = targetChannel;
        this.includedSegments = includedSegments;
        this.filters = filters;
        this.contents = contents;
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public String getTargetChannel() {
        return targetChannel;
    }

    public List<String> getIncludedSegments() {
        return includedSegments;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public Content getContents() {
        return contents;
    }

    public String getUrl() {
        return url;
    }

    public static class Content {
        private final String de;
        private final String en;

        public Content(String de, String en) {
            this.de = de;
            this.en = en;
        }

        public String getDe() {
            return de;
        }

        public String getEn() {
            return en;
        }
    }

    public static class Filter {
        private String field;
        private String key;
        private String value;
        private String relation;

        public Filter(String field, String key, String value, String relation) {
            this.field = field;
            this.key = key;
            this.value = value;
            this.relation = relation;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }
    }
}

