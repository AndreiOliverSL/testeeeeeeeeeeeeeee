package exceptions;

public class LivroNaoExisteException extends Exception {
    public LivroNaoExisteException(String msg){
        super(msg);
    }
}
