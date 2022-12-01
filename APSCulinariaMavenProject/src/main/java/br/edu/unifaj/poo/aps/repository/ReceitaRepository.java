package br.edu.unifaj.poo.aps.repository;

import br.edu.unifaj.poo.aps.entity.dao.ReceitaDao;
import br.edu.unifaj.poo.aps.entity.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Repository
public class ReceitaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UsuarioRepository usuarioRepository;

    public ReceitaDao getReceitaByNome(String nomeReceita) throws Exception {
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "SELECT id_receita, nome_receita, tempo_preparo, passos, id_usuario FROM receita WHERE nome_receita=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, nomeReceita);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Integer id_receita = result.getInt("id_receita");
                String nome_receita_retorno = result.getString("nome_receita");
                Float tempo_preparo = result.getFloat("tempo_preparo");
                String passos = result.getString("passos");
                Integer id_usuario = result.getInt("id_usuario");
                UsuarioDao usuario = usuarioRepository.getUsuarioById(id_usuario);
                con.close();
                statement.close();
                return new ReceitaDao(id_receita, nome_receita_retorno, tempo_preparo, passos, usuario);
            }
            con.close();
            statement.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao recuperar receita com nome: " + nomeReceita);
        }
    }

    public List<ReceitaDao> getReceitas() throws Exception {
        try {
            List<ReceitaDao> receitas = new ArrayList<>();
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "SELECT id_receita, nome_receita, tempo_preparo, passos, id_usuario FROM receita";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Integer id_receita = result.getInt("id_receita");
                String nome_receita_retorno = result.getString("nome_receita");
                Float tempo_preparo = result.getFloat("tempo_preparo");
                String passos = result.getString("passos");
                Integer id_usuario = result.getInt("id_usuario");
                UsuarioDao usuario = usuarioRepository.getUsuarioById(id_usuario);
                receitas.add(new ReceitaDao(id_receita, nome_receita_retorno, tempo_preparo, passos, usuario));
            }
            con.close();
            statement.close();
            return receitas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao recuperar receitas");
        }
    }

    public ReceitaDao postReceita(ReceitaDao receita) throws Exception {
        try {
            UsuarioDao usuario = receita.getUsuario();
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "INSERT INTO receita(nome_receita, tempo_preparo, passos, id_usuario) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, receita.getNome());
            statement.setFloat(2, receita.getTempoPreparo());
            statement.setString(3, receita.getPassos());
            statement.setInt(4, usuario.getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Integer id = generatedKeys.getInt(1);
                receita.setId(id);
                System.out.println("Receita inserida com sucesso, id: " + id);
            } else {
                throw new Exception("Falha ao inserir receita, ID não obtido");
            }

            con.close();
            statement.close();
            return receita;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Falha ao inserir receita");
        }
    }

    public void createImage(String base64, String nome) throws Exception {
        byte[] imgBytes = Base64.getMimeDecoder().decode(base64);
        FileOutputStream fos = new FileOutputStream(
                "./APSCulinariaMavenProject/src/main/resources/static/images/" + nome);
        fos.write(imgBytes);
        fos.close();
        System.out.println("Imagem criada: " + nome);
    }
}
