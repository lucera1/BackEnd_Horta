package com.estagio.services;

import com.estagio.domains.Usuario;
import com.estagio.repositories.UsuarioRepository;
import com.estagio.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Optional<Usuario> user = usuarioRepository.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found: "+ username);
        }
        return new UserSS(user.orElse(null));
    }
}
