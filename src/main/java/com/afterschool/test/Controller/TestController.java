package com.afterschool.test.Controller;

import com.afterschool.test.Entity.User;
import com.afterschool.test.Entity.UserDTO;
import com.afterschool.test.Service.LoginService;
import com.afterschool.test.repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final LoginService loginService;
    private final UserRepo userRepository;

    @GetMapping("/login")
    public ResponseEntity<String>login(HttpSession session){
        Long id = (Long) session.getAttribute("userid");
        if(id==null){
            return ResponseEntity.badRequest().body("fail");
        }
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/login")
    public ResponseEntity<String>login2(@RequestBody UserDTO dto, HttpSession session){
        Long id = loginService.login(dto.getEmail(), dto.getPassword());
        if(id==null){
            return ResponseEntity.badRequest().body("fail");
        }
        else{
            session.setAttribute("id", id);
            return ResponseEntity.ok().body("success");
        }
    }

    @GetMapping("/signup")
    public ResponseEntity<String>signup(){
        return ResponseEntity.ok().body("signup page");
    }

    @PostMapping("/signup")
    public ResponseEntity<String>signup2(@RequestBody UserDTO dto){
        try {
            User user = dto.toEntity();
            loginService.signup(user);
            return ResponseEntity.ok().body("success");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("fail because: " + e.getMessage() + dto.getEmail());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String>logout(HttpSession session){
        Long id = (Long) session.getAttribute("id");
        User user = userRepository.findUserById(id);
        if(user==null){
            return ResponseEntity.badRequest().body("fail");
        }
        return ResponseEntity.ok().body("Hello " + user.getEmail());
    }

    @PostMapping("/logout")
    public ResponseEntity<String>logout2(HttpSession session){
        Long id = (Long) session.getAttribute("id");
        if(id!=null) {
            session.invalidate();
            return ResponseEntity.ok().body("success");
        }
        return ResponseEntity.badRequest().body("fail");
    }

    @PostMapping("/check")
    public ResponseEntity<String> check(HttpSession session){
        return ResponseEntity.ok("asdf");
    }
}
