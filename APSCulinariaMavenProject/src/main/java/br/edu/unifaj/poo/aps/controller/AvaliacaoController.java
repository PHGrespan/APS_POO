package br.edu.unifaj.poo.aps.controller;

import br.edu.unifaj.poo.aps.entity.Retorno;
import br.edu.unifaj.poo.aps.entity.model.Avaliacao;
import br.edu.unifaj.poo.aps.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @GetMapping("/avaliacoes/{nomeReceita}")
    Retorno listarAvaliacoesPorReceita(@PathVariable String nomeReceita){
        try {
            return new Retorno(avaliacaoService.getAvaliacaoByReceita(nomeReceita));
        } catch (Exception e) {
            return new Retorno(e.getMessage());
        }
    }

    @PostMapping("/avaliacoes/{nomeReceita}")
    Retorno inserirAvaliacaoPorReceita(@RequestBody Avaliacao avaliacao, @PathVariable String nomeReceita){
        try {
            return new Retorno(avaliacaoService.postAvaliacao(avaliacao, nomeReceita));
        } catch (Exception e) {
            return new Retorno(e.getMessage());
        }
    }
}
