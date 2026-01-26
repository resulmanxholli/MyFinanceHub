package com.example.myfinancehub.mappers;

import com.example.myfinancehub.dtos.UserDto;
import com.example.myfinancehub.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserDto, User> {
}
