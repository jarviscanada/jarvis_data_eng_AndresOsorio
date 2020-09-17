package ca.jrvs.apps.trading.model.domain;

import java.util.Objects;

public class MarketOrderDto implements Entity<Integer> {

  private Integer accountId;
  private Integer size;
  private String ticker;
  private String action;

  @Override
  public Integer getId() {
    return accountId;
  }

  @Override
  public void setId(Integer accountId) {
    this.accountId = accountId;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }
}
