package br.edu.unifaj.poo.aps.service;

import br.edu.unifaj.poo.aps.entity.dao.UsuarioDao;
import br.edu.unifaj.poo.aps.entity.model.Usuario;
import br.edu.unifaj.poo.aps.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioDao postUsuario(Usuario usuario) throws Exception {
        UsuarioDao usuarioDao = new UsuarioDao(usuario.getCpf(), usuario.getNome());
        return usuarioRepository.postUsuario(usuarioDao);
    }
}
