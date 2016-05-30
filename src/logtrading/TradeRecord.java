package logtrading;

import java.util.Date;
import stocks.Stock;

/**
 *
 * @author robertfee
 */
public class TradeRecord {

    private final Stock stock;
    private final Date timestamp;
    private final long quantity;
    private final TradeType tradeType;
    private final long price;

    public TradeRecord(Stock stock, Date timestamp, long quantity, TradeType tradeType, long price) {
        this.stock = stock;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.tradeType = tradeType;
        this.price = price;
    }

    public Stock getStock() {
        return stock;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public long getQuantity() {
        return quantity;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public long getPrice() {
        return price;
    }

}
