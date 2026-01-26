package com.example.myfinancehub.infrastructure.services;

public interface FindOneService <D , Did>{
    D findOne(Did id);
}
