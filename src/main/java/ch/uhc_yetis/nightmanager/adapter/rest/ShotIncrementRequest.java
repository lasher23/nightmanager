package ch.uhc_yetis.nightmanager.adapter.rest;

public class ShotIncrementRequest {
    private Integer count;

    public ShotIncrementRequest() {
        this.count = 1;
    }

    public ShotIncrementRequest(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
