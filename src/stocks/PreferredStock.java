package stocks;

import java.util.Objects;

/**
 * Notes
 *   Looks like an immutable class so make it so.
 * 
 * @author robertfee
 */
public class PreferredStock implements Stock {

    private final String symbol;
    private final long lastDividend;
    private final long fixedDividend;
    private final long parValue;

    public PreferredStock(String symbol, long lastDividend, long fixedDividend, long parValue) {
        this.symbol = symbol;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    @Override
    public double getDividendYield(long price) throws StockCalcException {
        if (price == 0)
            throw new StockCalcException("zero price in dividend yield");
        else
            return (this.getFixedDividend() * this.getParValue()) / price;

    }

    @Override
    public double getPERatio(long price) throws StockCalcException {
        if (this.getLastDividend() == 0)
            throw new StockCalcException("zero price in PE Ratio");
        else
            return price / this.getLastDividend();
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public long getLastDividend() {
        return lastDividend;
    }

    public long getFixedDividend() {
        return fixedDividend;
    }

    public long getParValue() {
        return parValue;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.symbol);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PreferredStock other = (PreferredStock) obj;
        if (!Objects.equals(this.symbol, other.symbol))
            return false;
        return true;
    }

}
