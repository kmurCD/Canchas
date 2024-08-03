package com.suma.reserva_de_canchas.service.impl;

import com.suma.reserva_de_canchas.service.JwtService;
import com.suma.reserva_de_canchas.controller.models.AuthResponse;
import com.suma.reserva_de_canchas.controller.models.AuthenticationRequest;
import com.suma.reserva_de_canchas.controller.models.RegisterRequest;
import com.suma.reserva_de_canchas.entities.Role;
import com.suma.reserva_de_canchas.entities.User;
import com.suma.reserva_de_canchas.repository.UserRepository;
import com.suma.reserva_de_canchas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .nombre(registerRequest.getNombre())
                .apellidos(registerRequest.getApellidos())
                .usuario(registerRequest.getUsuario())
                .contrasenia(passwordEncoder.encode((registerRequest.getContrasenia())))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsuario(),
                request.getContrasenia()));



        var user = userRepository.findByUsuario(request.getUsuario()).orElseThrow();
        var token = jwtService.generateToken(user);

        return AuthResponse.builder().token(token).build();
    }
}
