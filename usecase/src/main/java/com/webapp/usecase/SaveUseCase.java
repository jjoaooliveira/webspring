package com.webapp.usecase;

public abstract class SaveUseCase<I, O> {
    public abstract O execute(I i);
}
