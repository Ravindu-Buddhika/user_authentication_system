package service.IMPL;

import model.dto.LogingInfoDTO;
import service.UserService;

public class UserServiceIMPL implements UserService {
    @Override
    public void getUser(LogingInfoDTO logingInfoDTO) {
        System.out.println(logingInfoDTO.getEmail()+" "+logingInfoDTO.getPassword()); System.out.println(logingInfoDTO.getEmail()+" "+logingInfoDTO.getPassword());
    }

    public void validateEmails() {

    }
}
