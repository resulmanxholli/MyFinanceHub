package com.example.myfinancehub.infrastructure.services;

public interface ReadOnlyService <D, Did> extends FindOneService<D, Did>, FindAllService<D>{
}
