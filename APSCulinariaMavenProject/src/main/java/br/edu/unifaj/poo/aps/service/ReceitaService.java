package br.edu.unifaj.poo.aps.service;

import br.edu.unifaj.poo.aps.entity.dao.IngredienteDao;
import br.edu.unifaj.poo.aps.entity.dao.IngredienteReceitaDao;
import br.edu.unifaj.poo.aps.entity.dao.ReceitaDao;
import br.edu.unifaj.poo.aps.entity.dao.UsuarioDao;
import br.edu.unifaj.poo.aps.entity.model.Ingrediente;
import br.edu.unifaj.poo.aps.entity.model.Receita;
import br.edu.unifaj.poo.aps.repository.IngredienteReceitaRepository;
import br.edu.unifaj.poo.aps.repository.IngredienteRepository;
import br.edu.unifaj.poo.aps.repository.ReceitaRepository;
import br.edu.unifaj.poo.aps.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    IngredienteRepository ingredienteRepository;

    @Autowired
    IngredienteReceitaRepository ingredienteReceitaRepository;

    public List<ReceitaDao> getReceitas() throws Exception {
        List<ReceitaDao> receitasDao = receitaRepository.getReceitas();
        if (receitasDao.size() == 0){
            throw new Exception("Não existem receitas");
        }
        return receitasDao;
    }

    public ReceitaDao postReceita(Receita receita, String nomeUsuario) throws Exception {
        UsuarioDao usuarioDao = usuarioRepository.getUsuarioByNome(nomeUsuario);
        if(usuarioDao == null){
            throw new Exception("Usuário não existe");
        }

        // valida se ingredientes existem
        for (Ingrediente ingrediente : receita.getIngredientes()) {
            // recupera id do ingrediente
            IngredienteDao ingredienteDao = ingredienteRepository.getIngredienteByNome(ingrediente.getNome());
            if(ingredienteDao != null){
                System.out.println("Ingrediente encontrado: " + ingrediente.getNome());
            } else {
                throw new Exception("Ingrediente não existe: " + ingrediente.getNome());
            }
        }

        // insere receita
        ReceitaDao receitaDao = receitaRepository.postReceita(new ReceitaDao(receita.getNome(), receita.getTempoPreparo(), receita.getPassos(), usuarioDao));

        // insere cada ingrediente na tabela que relaciona ingrediente e receita
        for (Ingrediente ingrediente : receita.getIngredientes()) {
            IngredienteDao ingredienteDao = ingredienteRepository.getIngredienteByNome(ingrediente.getNome());
            IngredienteReceitaDao ingredienteReceitaDao = new IngredienteReceitaDao(ingredienteDao, receitaDao, ingrediente.getQuantidade());
            ingredienteReceitaRepository.postIngredienteReceita(ingredienteReceitaDao);
        }

        return receitaDao;
    }
}
