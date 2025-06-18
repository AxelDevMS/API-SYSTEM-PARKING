package com.ams.dev.api.parking.user.dto;

import java.util.List;

public class UserListDto {

    private List<UserDto> users;
    private int totalElements;

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
