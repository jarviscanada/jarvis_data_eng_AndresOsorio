package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "symbol",
    "companyName",
    "calculationPrice",
    "open",
    "openTime",
    "close",
    "closeTime",
    "high",
    "low",
    "latestPrice",
    "latestSource",
    "latestTime",
    "latestUpdate",
    "latestVolume",
    "volume",
    "iexRealtimePrice",
    "iexRealtimeSize",
    "iexLastUpdated",
    "delayedPrice",
    "delayedPriceTime",
    "oddLotDelayedPrice",
    "oddLotDelayedPriceTime",
    "extendedPrice",
    "extendedChange",
    "extendedChangePercent",
    "extendedPriceTime",
    "previousClose",
    "previousVolume",
    "change",
    "changePercent",
    "iexMarketPercent",
    "iexVolume",
    "avgTotalVolume",
    "iexBidPrice",
    "iexBidSize",
    "iexAskPrice",
    "iexAskSize",
    "marketCap",
    "week52High",
    "week52Low",
    "ytdChange",
    "peRatio",
    "lastTradeTime",
    "isUSMarketOpen"
})
public class IexQuote {

  @JsonProperty("symbol")
  private String symbol;
  @JsonProperty("companyName")
  private String companyName;
  @JsonProperty("calculationPrice")
  private String calculationPrice;
  @JsonProperty("open")
  private double open;
  @JsonProperty("openTime")
  private double openTime;
  @JsonProperty("close")
  private double close;
  @JsonProperty("closeTime")
  private double closeTime;
  @JsonProperty("high")
  private double high;
  @JsonProperty("low")
  private double low;
  @JsonProperty("latestPrice")
  private double latestPrice;
  @JsonProperty("latestSource")
  private String latestSource;
  @JsonProperty("latestTime")
  private String latestTime;
  @JsonProperty("latestUpdate")
  private double latestUpdate;
  @JsonProperty("latestVolume")
  private double latestVolume;
  @JsonProperty("volume")
  private double volume;
  @JsonProperty("iexRealtimePrice")
  private double iexRealtimePrice;
  @JsonProperty("iexRealtimeSize")
  private double iexRealtimeSize;
  @JsonProperty("iexLastUpdated")
  private double iexLastUpdated;
  @JsonProperty("delayedPrice")
  private double delayedPrice;
  @JsonProperty("delayedPriceTime")
  private double delayedPriceTime;
  @JsonProperty("oddLotDelayedPrice")
  private double oddLotDelayedPrice;
  @JsonProperty("oddLotDelayedPriceTime")
  private double oddLotDelayedPriceTime;
  @JsonProperty("extendedPrice")
  private double extendedPrice;
  @JsonProperty("extendedChange")
  private double extendedChange;
  @JsonProperty("extendedChangePercent")
  private double extendedChangePercent;
  @JsonProperty("extendedPriceTime")
  private double extendedPriceTime;
  @JsonProperty("previousClose")
  private double previousClose;
  @JsonProperty("previousVolume")
  private double previousVolume;
  @JsonProperty("change")
  private double change;
  @JsonProperty("changePercent")
  private double changePercent;
  @JsonProperty("iexMarketPercent")
  private double iexMarketPercent;
  @JsonProperty("iexVolume")
  private double iexVolume;
  @JsonProperty("avgTotalVolume")
  private double avgTotalVolume;
  @JsonProperty("iexBidPrice")
  private double iexBidPrice;
  @JsonProperty("iexBidSize")
  private double iexBidSize;
  @JsonProperty("iexAskPrice")
  private double iexAskPrice;
  @JsonProperty("iexAskSize")
  private double iexAskSize;
  @JsonProperty("marketCap")
  private double marketCap;
  @JsonProperty("week52High")
  private double week52High;
  @JsonProperty("week52Low")
  private double week52Low;
  @JsonProperty("ytdChange")
  private double ytdChange;
  @JsonProperty("peRatio")
  private double peRatio;
  @JsonProperty("lastTradeTime")
  private double lastTradeTime;
  @JsonProperty("isUSMarketOpen")
  private boolean isUSMarketOpen;

  /**
   * No args constructor for use in serialization
   *
   */
  public IexQuote() {
  }

  /**
   *
   * @param symbol
   * @param avgTotalVolume
   * @param companyName
   * @param iexRealtimePrice
   * @param delayedPrice
   * @param iexMarketPercent
   * @param calculationPrice
   * @param extendedChangePercent
   * @param latestSource
   * @param latestUpdate
   * @param iexBidPrice
   * @param previousClose
   * @param high
   * @param peRatio
   * @param isUSMarketOpen
   * @param low
   * @param delayedPriceTime
   * @param oddLotDelayedPrice
   * @param extendedPrice
   * @param extendedPriceTime
   * @param week52Low
   * @param closeTime
   * @param changePercent
   * @param week52High
   * @param openTime
   * @param close
   * @param oddLotDelayedPriceTime
   * @param latestPrice
   * @param marketCap
   * @param previousVolume
   * @param iexRealtimeSize
   * @param iexLastUpdated
   * @param change
   * @param latestVolume
   * @param iexAskPrice
   * @param volume
   * @param ytdChange
   * @param iexVolume
   * @param iexAskSize
   * @param lastTradeTime
   * @param extendedChange
   * @param latestTime
   * @param open
   * @param iexBidSize
   */
  public IexQuote(String symbol, String companyName, String calculationPrice, double open, double openTime, double close, double closeTime, double high, double low, double latestPrice, String latestSource, String latestTime, double latestUpdate, double latestVolume, double volume, double iexRealtimePrice, double iexRealtimeSize, double iexLastUpdated, double delayedPrice, double delayedPriceTime, double oddLotDelayedPrice, double oddLotDelayedPriceTime, double extendedPrice, double extendedChange, double extendedChangePercent, double extendedPriceTime, double previousClose, double previousVolume, double change, double changePercent, double iexMarketPercent, double iexVolume, double avgTotalVolume, double iexBidPrice, double iexBidSize, double iexAskPrice, double iexAskSize, double marketCap, double week52High, double week52Low, double ytdChange, double peRatio, double lastTradeTime, boolean isUSMarketOpen) {
    super();
    this.symbol = symbol;
    this.companyName = companyName;
    this.calculationPrice = calculationPrice;
    this.open = open;
    this.openTime = openTime;
    this.close = close;
    this.closeTime = closeTime;
    this.high = high;
    this.low = low;
    this.latestPrice = latestPrice;
    this.latestSource = latestSource;
    this.latestTime = latestTime;
    this.latestUpdate = latestUpdate;
    this.latestVolume = latestVolume;
    this.volume = volume;
    this.iexRealtimePrice = iexRealtimePrice;
    this.iexRealtimeSize = iexRealtimeSize;
    this.iexLastUpdated = iexLastUpdated;
    this.delayedPrice = delayedPrice;
    this.delayedPriceTime = delayedPriceTime;
    this.oddLotDelayedPrice = oddLotDelayedPrice;
    this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    this.extendedPrice = extendedPrice;
    this.extendedChange = extendedChange;
    this.extendedChangePercent = extendedChangePercent;
    this.extendedPriceTime = extendedPriceTime;
    this.previousClose = previousClose;
    this.previousVolume = previousVolume;
    this.change = change;
    this.changePercent = changePercent;
    this.iexMarketPercent = iexMarketPercent;
    this.iexVolume = iexVolume;
    this.avgTotalVolume = avgTotalVolume;
    this.iexBidPrice = iexBidPrice;
    this.iexBidSize = iexBidSize;
    this.iexAskPrice = iexAskPrice;
    this.iexAskSize = iexAskSize;
    this.marketCap = marketCap;
    this.week52High = week52High;
    this.week52Low = week52Low;
    this.ytdChange = ytdChange;
    this.peRatio = peRatio;
    this.lastTradeTime = lastTradeTime;
    this.isUSMarketOpen = isUSMarketOpen;
  }

  @JsonProperty("symbol")
  public String getSymbol() {
    return symbol;
  }

  @JsonProperty("symbol")
  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  @JsonProperty("companyName")
  public String getCompanyName() {
    return companyName;
  }

  @JsonProperty("companyName")
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @JsonProperty("calculationPrice")
  public String getCalculationPrice() {
    return calculationPrice;
  }

  @JsonProperty("calculationPrice")
  public void setCalculationPrice(String calculationPrice) {
    this.calculationPrice = calculationPrice;
  }

  @JsonProperty("open")
  public double getOpen() {
    return open;
  }

  @JsonProperty("open")
  public void setOpen(double open) {
    this.open = open;
  }

  @JsonProperty("openTime")
  public double getOpenTime() {
    return openTime;
  }

  @JsonProperty("openTime")
  public void setOpenTime(double openTime) {
    this.openTime = openTime;
  }

  @JsonProperty("close")
  public double getClose() {
    return close;
  }

  @JsonProperty("close")
  public void setClose(double close) {
    this.close = close;
  }

  @JsonProperty("closeTime")
  public double getCloseTime() {
    return closeTime;
  }

  @JsonProperty("closeTime")
  public void setCloseTime(double closeTime) {
    this.closeTime = closeTime;
  }

  @JsonProperty("high")
  public double getHigh() {
    return high;
  }

  @JsonProperty("high")
  public void setHigh(double high) {
    this.high = high;
  }

  @JsonProperty("low")
  public double getLow() {
    return low;
  }

  @JsonProperty("low")
  public void setLow(double low) {
    this.low = low;
  }

  @JsonProperty("latestPrice")
  public double getLatestPrice() {
    return latestPrice;
  }

  @JsonProperty("latestPrice")
  public void setLatestPrice(double latestPrice) {
    this.latestPrice = latestPrice;
  }

  @JsonProperty("latestSource")
  public String getLatestSource() {
    return latestSource;
  }

  @JsonProperty("latestSource")
  public void setLatestSource(String latestSource) {
    this.latestSource = latestSource;
  }

  @JsonProperty("latestTime")
  public String getLatestTime() {
    return latestTime;
  }

  @JsonProperty("latestTime")
  public void setLatestTime(String latestTime) {
    this.latestTime = latestTime;
  }

  @JsonProperty("latestUpdate")
  public double getLatestUpdate() {
    return latestUpdate;
  }

  @JsonProperty("latestUpdate")
  public void setLatestUpdate(double latestUpdate) {
    this.latestUpdate = latestUpdate;
  }

  @JsonProperty("latestVolume")
  public double getLatestVolume() {
    return latestVolume;
  }

  @JsonProperty("latestVolume")
  public void setLatestVolume(double latestVolume) {
    this.latestVolume = latestVolume;
  }

  @JsonProperty("volume")
  public double getVolume() {
    return volume;
  }

  @JsonProperty("volume")
  public void setVolume(double volume) {
    this.volume = volume;
  }

  @JsonProperty("iexRealtimePrice")
  public double getIexRealtimePrice() {
    return iexRealtimePrice;
  }

  @JsonProperty("iexRealtimePrice")
  public void setIexRealtimePrice(double iexRealtimePrice) {
    this.iexRealtimePrice = iexRealtimePrice;
  }

  @JsonProperty("iexRealtimeSize")
  public double getIexRealtimeSize() {
    return iexRealtimeSize;
  }

  @JsonProperty("iexRealtimeSize")
  public void setIexRealtimeSize(double iexRealtimeSize) {
    this.iexRealtimeSize = iexRealtimeSize;
  }

  @JsonProperty("iexLastUpdated")
  public double getIexLastUpdated() {
    return iexLastUpdated;
  }

  @JsonProperty("iexLastUpdated")
  public void setIexLastUpdated(double iexLastUpdated) {
    this.iexLastUpdated = iexLastUpdated;
  }

  @JsonProperty("delayedPrice")
  public double getDelayedPrice() {
    return delayedPrice;
  }

  @JsonProperty("delayedPrice")
  public void setDelayedPrice(double delayedPrice) {
    this.delayedPrice = delayedPrice;
  }

  @JsonProperty("delayedPriceTime")
  public double getDelayedPriceTime() {
    return delayedPriceTime;
  }

  @JsonProperty("delayedPriceTime")
  public void setDelayedPriceTime(double delayedPriceTime) {
    this.delayedPriceTime = delayedPriceTime;
  }

  @JsonProperty("oddLotDelayedPrice")
  public double getOddLotDelayedPrice() {
    return oddLotDelayedPrice;
  }

  @JsonProperty("oddLotDelayedPrice")
  public void setOddLotDelayedPrice(double oddLotDelayedPrice) {
    this.oddLotDelayedPrice = oddLotDelayedPrice;
  }

  @JsonProperty("oddLotDelayedPriceTime")
  public double getOddLotDelayedPriceTime() {
    return oddLotDelayedPriceTime;
  }

  @JsonProperty("oddLotDelayedPriceTime")
  public void setOddLotDelayedPriceTime(double oddLotDelayedPriceTime) {
    this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
  }

  @JsonProperty("extendedPrice")
  public double getExtendedPrice() {
    return extendedPrice;
  }

  @JsonProperty("extendedPrice")
  public void setExtendedPrice(double extendedPrice) {
    this.extendedPrice = extendedPrice;
  }

  @JsonProperty("extendedChange")
  public double getExtendedChange() {
    return extendedChange;
  }

  @JsonProperty("extendedChange")
  public void setExtendedChange(double extendedChange) {
    this.extendedChange = extendedChange;
  }

  @JsonProperty("extendedChangePercent")
  public double getExtendedChangePercent() {
    return extendedChangePercent;
  }

  @JsonProperty("extendedChangePercent")
  public void setExtendedChangePercent(double extendedChangePercent) {
    this.extendedChangePercent = extendedChangePercent;
  }

  @JsonProperty("extendedPriceTime")
  public double getExtendedPriceTime() {
    return extendedPriceTime;
  }

  @JsonProperty("extendedPriceTime")
  public void setExtendedPriceTime(double extendedPriceTime) {
    this.extendedPriceTime = extendedPriceTime;
  }

  @JsonProperty("previousClose")
  public double getPreviousClose() {
    return previousClose;
  }

  @JsonProperty("previousClose")
  public void setPreviousClose(double previousClose) {
    this.previousClose = previousClose;
  }

  @JsonProperty("previousVolume")
  public double getPreviousVolume() {
    return previousVolume;
  }

  @JsonProperty("previousVolume")
  public void setPreviousVolume(double previousVolume) {
    this.previousVolume = previousVolume;
  }

  @JsonProperty("change")
  public double getChange() {
    return change;
  }

  @JsonProperty("change")
  public void setChange(double change) {
    this.change = change;
  }

  @JsonProperty("changePercent")
  public double getChangePercent() {
    return changePercent;
  }

  @JsonProperty("changePercent")
  public void setChangePercent(double changePercent) {
    this.changePercent = changePercent;
  }

  @JsonProperty("iexMarketPercent")
  public double getIexMarketPercent() {
    return iexMarketPercent;
  }

  @JsonProperty("iexMarketPercent")
  public void setIexMarketPercent(double iexMarketPercent) {
    this.iexMarketPercent = iexMarketPercent;
  }

  @JsonProperty("iexVolume")
  public double getIexVolume() {
    return iexVolume;
  }

  @JsonProperty("iexVolume")
  public void setIexVolume(double iexVolume) {
    this.iexVolume = iexVolume;
  }

  @JsonProperty("avgTotalVolume")
  public double getAvgTotalVolume() {
    return avgTotalVolume;
  }

  @JsonProperty("avgTotalVolume")
  public void setAvgTotalVolume(double avgTotalVolume) {
    this.avgTotalVolume = avgTotalVolume;
  }

  @JsonProperty("iexBidPrice")
  public double getIexBidPrice() {
    return iexBidPrice;
  }

  @JsonProperty("iexBidPrice")
  public void setIexBidPrice(double iexBidPrice) {
    this.iexBidPrice = iexBidPrice;
  }

  @JsonProperty("iexBidSize")
  public double getIexBidSize() {
    return iexBidSize;
  }

  @JsonProperty("iexBidSize")
  public void setIexBidSize(double iexBidSize) {
    this.iexBidSize = iexBidSize;
  }

  @JsonProperty("iexAskPrice")
  public double getIexAskPrice() {
    return iexAskPrice;
  }

  @JsonProperty("iexAskPrice")
  public void setIexAskPrice(double iexAskPrice) {
    this.iexAskPrice = iexAskPrice;
  }

  @JsonProperty("iexAskSize")
  public double getIexAskSize() {
    return iexAskSize;
  }

  @JsonProperty("iexAskSize")
  public void setIexAskSize(double iexAskSize) {
    this.iexAskSize = iexAskSize;
  }

  @JsonProperty("marketCap")
  public double getMarketCap() {
    return marketCap;
  }

  @JsonProperty("marketCap")
  public void setMarketCap(double marketCap) {
    this.marketCap = marketCap;
  }

  @JsonProperty("week52High")
  public double getWeek52High() {
    return week52High;
  }

  @JsonProperty("week52High")
  public void setWeek52High(double week52High) {
    this.week52High = week52High;
  }

  @JsonProperty("week52Low")
  public double getWeek52Low() {
    return week52Low;
  }

  @JsonProperty("week52Low")
  public void setWeek52Low(double week52Low) {
    this.week52Low = week52Low;
  }

  @JsonProperty("ytdChange")
  public double getYtdChange() {
    return ytdChange;
  }

  @JsonProperty("ytdChange")
  public void setYtdChange(double ytdChange) {
    this.ytdChange = ytdChange;
  }

  @JsonProperty("peRatio")
  public double getPeRatio() {
    return peRatio;
  }

  @JsonProperty("peRatio")
  public void setPeRatio(double peRatio) {
    this.peRatio = peRatio;
  }

  @JsonProperty("lastTradeTime")
  public double getLastTradeTime() {
    return lastTradeTime;
  }

  @JsonProperty("lastTradeTime")
  public void setLastTradeTime(double lastTradeTime) {
    this.lastTradeTime = lastTradeTime;
  }

  @JsonProperty("isUSMarketOpen")
  public boolean isIsUSMarketOpen() {
    return isUSMarketOpen;
  }

  @JsonProperty("isUSMarketOpen")
  public void setIsUSMarketOpen(boolean isUSMarketOpen) {
    this.isUSMarketOpen = isUSMarketOpen;
  }

  @Override
  public String toString() {
    return "IexQuote{" +
        "symbol='" + symbol + '\'' +
        ", companyName='" + companyName + '\'' +
        ", calculationPrice='" + calculationPrice + '\'' +
        ", open=" + open +
        ", openTime=" + openTime +
        ", close=" + close +
        ", closeTime=" + closeTime +
        ", high=" + high +
        ", low=" + low +
        ", latestPrice=" + latestPrice +
        ", latestSource='" + latestSource + '\'' +
        ", latestTime='" + latestTime + '\'' +
        ", latestUpdate=" + latestUpdate +
        ", latestVolume=" + latestVolume +
        ", volume=" + volume +
        ", iexRealtimePrice=" + iexRealtimePrice +
        ", iexRealtimeSize=" + iexRealtimeSize +
        ", iexLastUpdated=" + iexLastUpdated +
        ", delayedPrice=" + delayedPrice +
        ", delayedPriceTime=" + delayedPriceTime +
        ", oddLotDelayedPrice=" + oddLotDelayedPrice +
        ", oddLotDelayedPriceTime=" + oddLotDelayedPriceTime +
        ", extendedPrice=" + extendedPrice +
        ", extendedChange=" + extendedChange +
        ", extendedChangePercent=" + extendedChangePercent +
        ", extendedPriceTime=" + extendedPriceTime +
        ", previousClose=" + previousClose +
        ", previousVolume=" + previousVolume +
        ", change=" + change +
        ", changePercent=" + changePercent +
        ", iexMarketPercent=" + iexMarketPercent +
        ", iexVolume=" + iexVolume +
        ", avgTotalVolume=" + avgTotalVolume +
        ", iexBidPrice=" + iexBidPrice +
        ", iexBidSize=" + iexBidSize +
        ", iexAskPrice=" + iexAskPrice +
        ", iexAskSize=" + iexAskSize +
        ", marketCap=" + marketCap +
        ", week52High=" + week52High +
        ", week52Low=" + week52Low +
        ", ytdChange=" + ytdChange +
        ", peRatio=" + peRatio +
        ", lastTradeTime=" + lastTradeTime +
        ", isUSMarketOpen=" + isUSMarketOpen +
        '}';
  }
}