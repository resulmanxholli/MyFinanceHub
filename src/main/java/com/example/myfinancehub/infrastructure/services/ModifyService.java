package com.example.myfinancehub.infrastructure.services;

public interface ModifyService <D,Did>{
    D modify(Did id, D dto);
}
