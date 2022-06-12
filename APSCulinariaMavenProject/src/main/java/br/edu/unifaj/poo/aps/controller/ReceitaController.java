package br.edu.unifaj.poo.aps.controller;

import br.edu.unifaj.poo.aps.entity.Retorno;
import br.edu.unifaj.poo.aps.entity.model.Receita;
import br.edu.unifaj.poo.aps.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping("/receitas")
    Retorno listarReceitas(){
        try {
            return new Retorno(receitaService.getReceitas());
        } catch (Exception e) {
            return new Retorno(e.getMessage());
        }
    }

    @PostMapping("/receitas/{nomeUsuario}")
    Retorno inserirReceita(@RequestBody Receita receita, @PathVariable String nomeUsuario){
        try {
            return new Retorno(receitaService.postReceita(receita, nomeUsuario));
        } catch (Exception e) {
            return new Retorno(e.getMessage());
        }
    }
}
