package com.example.myfinancehub.mappers;

import java.util.List;

public interface BaseMapper <D, E>{
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntityList(List<D> dtos);
    List<D> toDtoList(List<E> entities);
}
