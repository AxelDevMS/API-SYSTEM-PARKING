package com.ams.dev.api.parking.user.controller;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.user.dto.UserDto;
import com.ams.dev.api.parking.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/parking/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> executeCreateUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        ApiResponseDto resposne = this.userService.executeCreateUser(userDto, bindingResult);
        return new ResponseEntity<>(resposne, HttpStatus.CREATED);
    }


}
