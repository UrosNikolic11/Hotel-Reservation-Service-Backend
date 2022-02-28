package com.raf.korisnicki_servis.service;

import com.raf.korisnicki_servis.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    UserDto addManager(UserCreateDto userCreateDto);
    UserDto addClient(UserCreateDto userCreateDto);
    UserDto changetRestriction(Long id);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    UserDto update(Long id, UserUpdateDto userUpdateDto);
    DiscountDto findDiscount(Long id);
    void addRez(PlusReservationDto plusReservationDto);
    void deleteRez(PlusReservationDto plusReservationDto);
    Page<UserDto> findUsers(Pageable pageable);
    void confirm(String email, String password);
    UserDto findUser(Long id);


}
