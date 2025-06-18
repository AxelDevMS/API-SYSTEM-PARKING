package com.ams.dev.api.parking.user.mapper;


import com.ams.dev.api.parking.user.dto.UserDto;
import com.ams.dev.api.parking.user.persistence.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserDto convertToDto(UserEntity userEntity){
        return this.modelMapper.map(userEntity, UserDto.class);
    }

    public UserEntity convertToEntity(UserDto userDto){
        return this.modelMapper.map(userDto, UserEntity.class);
    }
}
