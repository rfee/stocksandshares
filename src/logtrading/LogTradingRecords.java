package logtrading;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import stocks.Stock;

/**
 *
 * @author robertfee
 */
public class LogTradingRecords {

    private final HashMap<String, ArrayList<TradeRecord>> eachStocksTradeLog;

    public LogTradingRecords() {
        this.eachStocksTradeLog = new HashMap<>();
    }

    public void logTrade(Stock stock, long quantity, TradeType tradeType, long price) throws LogTradeException {
        ArrayList<TradeRecord> tradeLog;

        if ((stock == null) || (stock.getSymbol() == null))
            throw new LogTradeException("Null stock was logged");
        else {
            tradeLog = this.eachStocksTradeLog.get(stock.getSymbol());
            if (tradeLog == null) {
                tradeLog = new ArrayList<>();
                this.eachStocksTradeLog.put(stock.getSymbol(), tradeLog);
            }
            tradeLog.add(new TradeRecord(stock, new Date(), quantity, tradeType, price));
        }
    }

    public double getVolumeWeightedStockPriceOnTrades(String symbol, int inPastMinutes)
            throws LogTradeException {
        Date pastTimeStamp;
        int next;
        long sumQuantity = 0;
        long sumPriceTimesQuantity = 0;
        ArrayList<TradeRecord> tradeLog;
        TradeRecord tradeRecord;

        tradeLog = this.eachStocksTradeLog.get(symbol);
        if (tradeLog == null)
            return 0;
        else {
            pastTimeStamp = getTimeStampMinutesAgo(inPastMinutes);
            next = tradeLog.size() - 1;
            while ((next >= 0) && (tradeLog.get(next).getTimestamp().getTime() >= pastTimeStamp.getTime())) {
                tradeRecord = tradeLog.get(next);
                sumQuantity += tradeRecord.getQuantity();
                sumPriceTimesQuantity += (tradeRecord.getQuantity() * tradeRecord.getPrice());
                next--;
            }
        }
        if (sumQuantity == 0)
            return 0;
        else
            return (double)sumPriceTimesQuantity / (double)sumQuantity;
    }

    public double getGBCEAllShareIndexForallStocks() throws LogTradeException {
        double allVolumeWeightedStockPrices[];
        int next = 0;

        allVolumeWeightedStockPrices = new double[this.eachStocksTradeLog.values().size()];
        for (ArrayList<TradeRecord> stockLog : this.eachStocksTradeLog.values()) {
            allVolumeWeightedStockPrices[next] = getAllVolumeWeightedStockPriceOnTrades(stockLog);
            next++;
        }
        return getGeometricMean(allVolumeWeightedStockPrices);
    }

    private Date getTimeStampMinutesAgo(int minutesAgo) throws LogTradeException {
        Calendar calendar;
        if (minutesAgo < 0)
            throw new LogTradeException("Past minutes in the future");
        else {
            calendar = new GregorianCalendar();
            calendar.add(Calendar.MINUTE, -minutesAgo);
            return calendar.getTime();
        }
    }

    private double getAllVolumeWeightedStockPriceOnTrades(ArrayList<TradeRecord> stockTradeLog)
            throws LogTradeException {
        long sumQuantity = 0;
        long sumPriceTimesQuantity = 0;

        if (stockTradeLog == null)
            return 0;
        else
            for (TradeRecord tradeRecord : stockTradeLog) {
                sumQuantity += tradeRecord.getQuantity();
                sumPriceTimesQuantity += (tradeRecord.getQuantity() * tradeRecord.getPrice());
            }
        if (sumQuantity == 0)
            return 0;
        else
            return (double)sumPriceTimesQuantity / (double)sumQuantity;
    }

    private double getGeometricMean(double... values) {
        double times=1;
        if ((values == null)||(values.length==0))
            return 0;
        else
            for (double val : values)
                times *= val;
        return Math.pow(times, ((double)1/(double)values.length));
    }

}
