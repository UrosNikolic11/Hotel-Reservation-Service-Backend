package com.raf.korisnicki_servis.service.impl;
import com.raf.korisnicki_servis.domain.Rank;
import com.raf.korisnicki_servis.domain.User;
import com.raf.korisnicki_servis.dto.*;
import com.raf.korisnicki_servis.listener.MessageHelper;
import com.raf.korisnicki_servis.mapper.UserMapper;
import com.raf.korisnicki_servis.repository.RankRepository;
import com.raf.korisnicki_servis.secutiry.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.raf.korisnicki_servis.repository.UserRepository;
import com.raf.korisnicki_servis.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private TokenService tokenService;
    private UserRepository userRepository;
    private RankRepository rankRepository;
    private UserMapper userMapper;
    private String registracijaEmail;
    private String promenaPassworda;
    private JmsTemplate jms;
    private MessageHelper msgHelper;

    public UserServiceImpl(TokenService tokenService, UserRepository userRepository, RankRepository rankRepository, UserMapper userMapper,
                           @Value("${destination.registerMail}") String registracijaEmail, JmsTemplate jms,
                           MessageHelper msgHelper, @Value("${destination.promenaPassworda}") String promenaPassworda) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.rankRepository = rankRepository;
        this.userMapper = userMapper;
        this.registracijaEmail = registracijaEmail;
        this.jms = jms;
        this.msgHelper = msgHelper;
        this.promenaPassworda = promenaPassworda;
    }

    @Override
    public UserDto addManager(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        user.setRole("manager");
        userRepository.save(user);
        jms.convertAndSend(registracijaEmail, msgHelper.createTextMessage(userMapper.userToUserDto(user)));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto addClient(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        user.setRole("client");
        user.setBrRezervacija(0);
        userRepository.save(user);
        jms.convertAndSend(registracijaEmail, msgHelper.createTextMessage(userMapper.userToUserDto(user)));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto changetRestriction(Long id) {
        User user = userRepository.findUserById(id);
        String s = user.getAktivan();
        if(s.equals("No")){
            user.setAktivan("Yes");
            userRepository.save(user);
            return userMapper.userToUserDto(user);
        }
        else if(s.equals("Yes")){
            user.setAktivan("No");
            userRepository.save(user);
            return userMapper.userToUserDto(user);
        }
        return null;
    }


    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        User user = userRepository
                .findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword());
        if(user.getAktivan().equals("No")){
            return null;
        }
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public UserDto update(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findUserById(id);
        boolean flag= false;
        if(!(userUpdateDto.getDatumRodjenja() == null)){
            user.setDatumRodjenja(userUpdateDto.getDatumRodjenja());
        }
        if(!(userUpdateDto.getEmail() == null)){
            user.setEmail(userUpdateDto.getEmail());
        }
        if(!(userUpdateDto.getFirstName() == null)){
            user.setIme(userUpdateDto.getFirstName());
        }
        if(!(userUpdateDto.getLastName() == null)){
            user.setPrezime(userUpdateDto.getLastName());
        }
        if(!(userUpdateDto.getTelefon() == null)){
            user.setTelefon(userUpdateDto.getTelefon());
        }
        if(!(userUpdateDto.getPassword() == null)){
            user.setPassword(userUpdateDto.getPassword());
            System.out.println("SETOVANOOOOOOOOOOOOO");
            flag = true;
        }
        if(!(userUpdateDto.getUsername() == null)){
            user.setUsername(userUpdateDto.getUsername());
        }
        if(!(userUpdateDto.getBrPasosa() == null)){
            user.setBrPasosa(userUpdateDto.getBrPasosa());
        }
        if(!(userUpdateDto.getBrRezervacija() == null)){
            user.setBrRezervacija(userUpdateDto.getBrRezervacija());
        }
        if(!(userUpdateDto.getDatumZaposlenja() == null)){
            user.setDatumZaposlenja(userUpdateDto.getDatumZaposlenja());
        }
        userRepository.save(user);
        if(flag){
            System.out.println("pas" + user.getPassword());
            jms.convertAndSend(promenaPassworda, msgHelper.createTextMessage(userMapper.userToUserDto(user)));
        }
        return userMapper.userToUserDto(user);
    }

    @Override
    public DiscountDto findDiscount(Long id) {
        User user = userRepository.findUserById(id);
        Integer rez = user.getBrRezervacija();
        DiscountDto discountDto = new DiscountDto();
        List<Rank> rankovi = rankRepository.findAll();
        for(Rank rank : rankovi){
            if(rez >= rank.getBotLimit() && rez<= rank.getTopLimit()){
                discountDto.setDiscount(rank.getPopust());
                return discountDto;
            }
        }
        discountDto.setDiscount((float) 0);
        return discountDto;
    }

    @Override
    public void addRez(PlusReservationDto plusReservationDto) {
        User user = userRepository.findUserById(plusReservationDto.getUserId());
        user.setBrRezervacija(user.getBrRezervacija() + 1);
        userRepository.save(user);
    }

    @Override
    public void deleteRez(PlusReservationDto plusReservationDto) {
        User user = userRepository.findUserById(plusReservationDto.getUserId());
        user.setBrRezervacija(user.getBrRezervacija() - 1);
        userRepository.save(user);
    }

    @Override
    public Page<UserDto> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    public void confirm(String email, String password) {
        User user = userRepository.findUserByEmailAndPassword(email, password);
        user.setAktivan("Yes");
        userRepository.save(user);
    }

    @Override
    public UserDto findUser(Long id) {
        User user = userRepository.findUserById(id);
        return userMapper.userToUserDto(user);
    }
}
