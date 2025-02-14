package com.webapp.usecase;

public abstract class UpdateUseCase<I, O> {
    public abstract O execute(I i);
}
