package ch.uhc_yetis.nightmanager.application;

import ch.uhc_yetis.nightmanager.domain.model.GameState;

public class GameRequestParams {

  private Long categoryId;
  private Long hallId;
  private GameState state;
  private Integer afterNow;
  private Integer beforeNow;
  private Boolean live;

  public Boolean getLive() {
    return live;
  }

  public void setLive(Boolean live) {
    this.live = live;
  }

  public Integer getAfterNow() {
    return this.afterNow;
  }

  public void setAfterNow(Integer afterNow) {
    this.afterNow = afterNow;
  }

  public Integer getBeforeNow() {
    return this.beforeNow;
  }

  public void setBeforeNow(Integer beforeNow) {
    this.beforeNow = beforeNow;
  }

  public Long getCategoryId() {
    return this.categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getHallId() {
    return this.hallId;
  }

  public void setHallId(Long hallId) {
    this.hallId = hallId;
  }

  public GameState getState() {
    return this.state;
  }

  public void setState(GameState state) {
    this.state = state;
  }
}
