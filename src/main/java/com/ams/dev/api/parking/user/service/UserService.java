package com.ams.dev.api.parking.user.service;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.user.dto.UserDto;
import org.springframework.validation.BindingResult;

public interface UserService {

    ApiResponseDto executeCreateUser(UserDto userDto, BindingResult bindingResult) throws BadRequestException, NotFoundException;

}
