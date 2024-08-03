package com.suma.reserva_de_canchas.service;

import com.suma.reserva_de_canchas.controller.models.AuthResponse;
import com.suma.reserva_de_canchas.controller.models.AuthenticationRequest;
import com.suma.reserva_de_canchas.controller.models.RegisterRequest;

public interface AuthService {

    public AuthResponse register(RegisterRequest registerRequest);
    public AuthResponse login(AuthenticationRequest authenticationRequest);
}
