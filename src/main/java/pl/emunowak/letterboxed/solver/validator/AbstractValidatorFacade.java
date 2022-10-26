package pl.emunowak.letterboxed.solver.validator;

import java.util.List;

public abstract class AbstractValidatorFacade<T, E extends Exception> {

    private final ValidatorChain<T, E> headValidator;

    public AbstractValidatorFacade( List<ValidatorChain<T, E>> validators ) {
        this.headValidator = buildChain( validators );
    }

    public T validate( T input ) throws E {
        return headValidator.validate( input );
    }

    private ValidatorChain<T, E> buildChain( List<ValidatorChain<T, E>> validators ) {
        if ( validators.isEmpty() ) {
            return new DummyValidator<>();
        }
        for ( int i = 0; i < validators.size(); i++ ) {
            var current = validators.get( i );
            var next = i < validators.size() - 1 ? validators.get( i + 1 ) : new DummyValidator<T, E>();
            current.setNext( next );
        }
        return validators.get( 0 );
    }
}
