package com.takehome;

public class Pipeline<I, O> {
    private final Step<I, O> current;
    Pipeline(Step<I, O> current) {
        this.current = current;
    }

    public  <NewO> Pipeline<I, NewO> pipe(Step<O, NewO> next) {
        return new Pipeline<>(input -> next.process(current.process(input)));
    }

    public O execute(I input) throws Step.StepException {
        return current.process(input);
    }

    public interface Step<I, O> {
        public static class StepException extends RuntimeException {
            public StepException(Throwable t) {
                super(t);
            }
        }
        public O process(I input) throws StepException;
    }
}
