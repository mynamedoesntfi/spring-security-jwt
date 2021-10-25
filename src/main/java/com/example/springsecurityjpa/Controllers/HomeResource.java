package com.example.springsecurityjpa.Controllers;


import com.example.springsecurityjpa.Models.AuthenticationRequest;
import com.example.springsecurityjpa.Models.AuthenticationResponse;
import com.example.springsecurityjpa.Services.MyUserDetailsService;
import com.example.springsecurityjpa.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    @RequestMapping("/")
    public String welcome() {
        return "<h1>Welcome</h1>";
    }

    @GetMapping
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping
    @RequestMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                                                                authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        //region Token Generation

        /**
         * Executes only if authentication success
         * Authentication uses UserDetailsService to authenticate, it is used again to create jwt
         */
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        //endregion

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping
    @RequestMapping("/user")
    public String welcomeUser() {
        return "<h1>Welcome User</h1>";
    }

    @GetMapping
    @RequestMapping("/admin")
    public String welcomeAdmin() {
        return "<h1>Welcome Admin</h1>";
    }

}
