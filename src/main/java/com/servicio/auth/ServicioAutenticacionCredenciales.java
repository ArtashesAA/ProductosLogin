package com.servicio.auth;

import com.modelo.BaseDatosUsuario;

public interface ServicioAutenticacionCredenciales {
    BaseDatosUsuario autenticarConCredenciales(String usuario, String contraseña);
}
