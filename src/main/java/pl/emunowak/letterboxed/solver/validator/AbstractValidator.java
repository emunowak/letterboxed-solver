package pl.emunowak.letterboxed.solver.validator;

public abstract class AbstractValidator<T, E extends Exception> implements ValidatorChain<T, E> {

    private ValidatorChain<T, E> nextValidator;

    @Override
    public T validate( T input ) throws E {
        var validatedInput = validateAndApplyNext( input );
        return nextValidator.validate( validatedInput );
    }

    @Override
    public void setNext( ValidatorChain<T, E> nextValidator ) {
        this.nextValidator = nextValidator;
    }

    protected abstract T validateAndApplyNext( T input ) throws E;
}
