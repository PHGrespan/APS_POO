package br.edu.unifaj.poo.aps.controller;

import br.edu.unifaj.poo.aps.entity.Retorno;
import br.edu.unifaj.poo.aps.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class IngredienteController {

    @Autowired
    IngredienteService ingredienteService;

    @GetMapping("/ingredientes")
    Retorno listarIngredientes(){
        try {
            return new Retorno(ingredienteService.getIngredientes());
        } catch (Exception e) {
            return new Retorno(e.getMessage());
        }
    }
}
