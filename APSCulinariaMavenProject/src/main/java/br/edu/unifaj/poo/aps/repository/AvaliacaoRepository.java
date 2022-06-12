package br.edu.unifaj.poo.aps.repository;

import br.edu.unifaj.poo.aps.entity.dao.AvaliacaoDao;
import br.edu.unifaj.poo.aps.entity.dao.ReceitaDao;
import br.edu.unifaj.poo.aps.entity.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AvaliacaoRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<AvaliacaoDao> getAvaliacaoByReceita(ReceitaDao receita) throws Exception {
        try {
            List<AvaliacaoDao> avaliacaos = new ArrayList<>();
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "SELECT id_avaliacao, id_receita, id_usuario, descricao, nota FROM avaliacao WHERE id_receita=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, receita.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int id_avaliacao = result.getInt("id_avaliacao");
                int id_receita = result.getInt("id_receita");
                int id_usuario = result.getInt("id_usuario");
                String descricao = result.getString("descricao");
                Float nota = result.getFloat("nota");
                UsuarioDao usuario = usuarioRepository.getUsuarioById(id_usuario);
                avaliacaos.add(new AvaliacaoDao(id_avaliacao, receita, usuario, descricao, nota));
            }
            con.close();
            statement.close();
            return avaliacaos;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Falha ao recuperar avaliacoes da receita: " + receita.getNome());
        }
    }

    public AvaliacaoDao postAvaliacao(AvaliacaoDao avaliacaoDao) throws Exception {
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "INSERT INTO avaliacao(id_receita, id_usuario, descricao, nota) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ReceitaDao receitaDao = avaliacaoDao.getReceita();
            statement.setInt(1, receitaDao.getId());
            UsuarioDao usuarioDao = avaliacaoDao.getUsuario();
            statement.setInt(2, usuarioDao.getId());
            statement.setString(3, avaliacaoDao.getDescricao());
            statement.setFloat(4, avaliacaoDao.getNota());
            statement.executeUpdate();
            System.out.println("Avalição inserida com sucesso.");
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                avaliacaoDao.setId(generatedKeys.getInt(1));
            } else {
                throw new Exception("Falha ao inserir avaliação, ID não obtido");
            }
            con.close();
            statement.close();
            return avaliacaoDao;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Falha ao inserir avalição");
        }
    }
}
