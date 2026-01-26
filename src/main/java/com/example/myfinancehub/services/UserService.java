package com.example.myfinancehub.services;

import com.example.myfinancehub.dtos.UserDto;
import com.example.myfinancehub.infrastructure.services.CrudService;

public interface UserService extends CrudService<UserDto, Long> {
}
