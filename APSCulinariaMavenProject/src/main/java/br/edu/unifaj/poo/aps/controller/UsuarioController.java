package br.edu.unifaj.poo.aps.controller;

import br.edu.unifaj.poo.aps.entity.Retorno;
import br.edu.unifaj.poo.aps.entity.dao.UsuarioDao;
import br.edu.unifaj.poo.aps.entity.model.Avaliacao;
import br.edu.unifaj.poo.aps.entity.model.Usuario;
import br.edu.unifaj.poo.aps.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/usuarios")
    Retorno inseruirUsuario(@RequestBody Usuario usuario){
        try {
            return new Retorno(usuarioService.postUsuario(usuario));
        } catch (Exception e) {
            return new Retorno(e.getMessage());
        }
    }

}
