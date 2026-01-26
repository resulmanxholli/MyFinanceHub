package com.example.myfinancehub.services.impls;

import com.example.myfinancehub.dtos.UserDto;
import com.example.myfinancehub.mappers.UserMapper;
import com.example.myfinancehub.repositories.UserRepository;
import com.example.myfinancehub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto add(UserDto dto) {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return List.of();
    }

    @Override
    public UserDto findOne(Long id) {
        return null;
    }

    @Override
    public UserDto modify(Long id, UserDto dto) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
