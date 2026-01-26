package com.example.myfinancehub.infrastructure.services;

public interface WriteOnlyService <D, Did> extends ModifyService<D, Did>, RemoveService<Did>, AddService<D>{
}
