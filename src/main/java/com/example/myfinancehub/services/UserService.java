package com.example.myfinancehub.services;

import com.example.myfinancehub.dtos.UserDto;
import com.example.myfinancehub.infrastructure.services.CrudeService;

public interface UserService extends CrudeService<UserDto, Long> {
}
