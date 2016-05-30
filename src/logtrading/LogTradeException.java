package logtrading;

/**
 * Notes
 *     To hide specific implementation exceptions.
 * 
 * @author robertfee
 */
public class LogTradeException extends Exception {

    public LogTradeException() {
    }

    public LogTradeException(String msg) {
        super(msg);
    }
}
