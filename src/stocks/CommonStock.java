package stocks;

import java.util.Objects;

/**
 * Notes
 *   Looks like an immutable class so make it so.
 * 
 * @author robertfee
 */
public class CommonStock implements Stock {

    private final String symbol;
    private final long lastDividend;
    private final long parValue;

    public CommonStock(String symbol, long lastDividend, long parValue) {
        this.symbol = symbol;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
    }

    @Override
    public double getDividendYield(long price) throws StockCalcException {
        if (price == 0)
            throw new StockCalcException("zero price in dividend yield");
        else
            return this.lastDividend / price;

    }

    @Override
    public double getPERatio(long price) throws StockCalcException {
        if (this.lastDividend == 0)
            throw new StockCalcException("zero price in PE Ratio");
        else
            return price / this.lastDividend;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public long getLastDividend() {
        return lastDividend;
    }

    public long getParValue() {
        return parValue;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.symbol);
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
        final CommonStock other = (CommonStock) obj;
        if (!Objects.equals(this.symbol, other.symbol))
            return false;
        return true;
    }

}
