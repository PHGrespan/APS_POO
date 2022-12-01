package br.edu.unifaj.poo.aps.controller;

import br.edu.unifaj.poo.aps.entity.Retorno;
import br.edu.unifaj.poo.aps.entity.model.Usuario;
import br.edu.unifaj.poo.aps.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/") 
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


    @GetMapping("/usuarios/{nome}")
    Retorno recuperarUsuario(@PathVariable String nome){
        try {
            return new Retorno(usuarioService.getUsuario(nome));
        } catch (Exception e) {
            return new Retorno(e.getMessage());
        }
    }

}
