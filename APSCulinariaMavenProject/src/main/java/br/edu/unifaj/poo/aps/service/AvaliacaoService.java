package br.edu.unifaj.poo.aps.service;

import br.edu.unifaj.poo.aps.entity.dao.AvaliacaoDao;
import br.edu.unifaj.poo.aps.entity.dao.ReceitaDao;
import br.edu.unifaj.poo.aps.entity.dao.UsuarioDao;
import br.edu.unifaj.poo.aps.entity.model.Avaliacao;
import br.edu.unifaj.poo.aps.repository.AvaliacaoRepository;
import br.edu.unifaj.poo.aps.repository.ReceitaRepository;
import br.edu.unifaj.poo.aps.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<AvaliacaoDao> getAvaliacaoByReceita(String nomeReceita) throws Exception {
        ReceitaDao receitaDao = receitaRepository.getReceitaByNome(nomeReceita);
        if(receitaDao == null){
            throw new Exception("Receita não encontrada para o nome: " + nomeReceita);
        }
        return avaliacaoRepository.getAvaliacaoByReceita(receitaDao);
    }

    public AvaliacaoDao postAvaliacao(Avaliacao avaliacao, String nomeReceita) throws Exception {
        UsuarioDao usuarioDao = usuarioRepository.getUsuarioByNome(avaliacao.getNomeUsuario());
        if(usuarioDao == null){
            throw new Exception("Usuário não encontrado: " + avaliacao.getNomeUsuario());
        }
        ReceitaDao receitaDao = receitaRepository.getReceitaByNome(nomeReceita);
        if(receitaDao == null){
            throw new Exception("Receita não encontrada: " + nomeReceita);
        }
        AvaliacaoDao avaliacaoDao = new AvaliacaoDao(receitaDao, usuarioDao, avaliacao.getDescricao(), avaliacao.getNota());
        return avaliacaoRepository.postAvaliacao(avaliacaoDao);
    }
}
