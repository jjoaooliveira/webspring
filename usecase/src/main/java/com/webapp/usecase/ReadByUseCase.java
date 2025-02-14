package com.webapp.usecase;

public abstract class ReadByUseCase<I, O> {
    public abstract O execute(I i);
}
