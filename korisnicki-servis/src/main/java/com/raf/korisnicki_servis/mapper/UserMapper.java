package com.raf.korisnicki_servis.mapper;

import com.raf.korisnicki_servis.domain.User;
import com.raf.korisnicki_servis.dto.UserCreateDto;
import com.raf.korisnicki_servis.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserMapper() {
    }

    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getIme());
        userDto.setLastName(user.getPrezime());
        userDto.setUsername(user.getUsername());
        userDto.setTelefon(user.getTelefon());
        return userDto;
    }

    public User userCreateDtoToUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setEmail(userCreateDto.getEmail());
        user.setIme(userCreateDto.getFirstName());
        user.setPrezime(userCreateDto.getLastName());
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setTelefon(userCreateDto.getTelefon());
        user.setDatumRodjenja(userCreateDto.getDatumRodjenja());
        user.setDatumZaposlenja(userCreateDto.getDatumZaposlenja());
        user.setBrPasosa(userCreateDto.getBrPasosa());
        user.setAktivan("No");
        return user;
    }
}

