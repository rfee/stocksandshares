package stocks;

/**
 * Notes
 *    Calculations can be implemented in different ways so use a specific
 *    exception to hide different implementations.
 * 
 * @author robertfee
 */
public class StockCalcException extends Exception {

    public StockCalcException() {
    }

    public StockCalcException(String msg) {
        super(msg);
    }
}
