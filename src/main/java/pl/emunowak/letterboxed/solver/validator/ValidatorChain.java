package pl.emunowak.letterboxed.solver.validator;

public interface ValidatorChain<T, E extends Exception> {
    T validate( T input ) throws E;
    void setNext( ValidatorChain<T, E> nextValidator );
}
