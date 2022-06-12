package br.edu.unifaj.poo.aps.service;

import br.edu.unifaj.poo.aps.entity.dao.IngredienteDao;
import br.edu.unifaj.poo.aps.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {

    @Autowired
    IngredienteRepository ingredienteRepository;

    public List<IngredienteDao> getIngredientes() throws Exception {
        List<IngredienteDao> ingredientes = ingredienteRepository.getIngredientes();
        if (ingredientes.size() == 0){
            throw new Exception("Não existem ingredientes");
        }
        return ingredientes;
    }
}
