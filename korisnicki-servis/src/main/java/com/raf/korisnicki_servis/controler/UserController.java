package com.raf.korisnicki_servis.controler;

import com.raf.korisnicki_servis.dto.*;
import com.raf.korisnicki_servis.secutiry.CheckSecurity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.raf.korisnicki_servis.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/manager")
    public ResponseEntity registerManager(@RequestBody @Valid UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.addManager(userCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/register/client")
    public ResponseEntity registerClient(@RequestBody @Valid UserCreateDto userCreateDto) {
        return new ResponseEntity<>(userService.addClient(userCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginUser(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        String token = userService.login(tokenRequestDto).getToken();
        System.out.println(token);
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"admin", "manager", "client"})
    public ResponseEntity<UserDto> update(@RequestHeader("Authorization") String authorization,
                                          @PathVariable("id") Long id
            , @RequestBody @Valid UserUpdateDto userUpdateDto) {

        return new ResponseEntity<>(userService.update(id, userUpdateDto), HttpStatus.OK);
    }

    @PutMapping("/change/{id}")
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<UserDto> cr(@RequestHeader("Authorization") String authorization,
                                      @PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.changetRestriction(id), HttpStatus.OK);
    }

    @GetMapping("/discount/{id}")
    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findDiscount(id), HttpStatus.OK);
    }


    @GetMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<Page<UserDto>> getUsers(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(userService.findUsers(pageable), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
    }

    @PutMapping("/confirmation/{email}/{password}")
    public void confirm(@PathVariable("email") String email, @PathVariable("password") String password) {

        userService.confirm(email, password);

    }

}
