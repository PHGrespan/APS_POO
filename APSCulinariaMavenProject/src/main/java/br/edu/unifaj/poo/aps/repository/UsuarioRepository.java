package br.edu.unifaj.poo.aps.repository;

import br.edu.unifaj.poo.aps.entity.dao.ReceitaDao;
import br.edu.unifaj.poo.aps.entity.dao.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class UsuarioRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UsuarioDao getUsuarioById(Integer idUsuario) throws Exception {
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "SELECT id_usuario, cpf, nome_usuario FROM usuario WHERE id_usuario=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idUsuario);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                Integer id_usuario = result.getInt("id_usuario");
                String cpf = result.getString("cpf");
                String nome_usuario = result.getString("nome_usuario");
                con.close();
                statement.close();
                return new UsuarioDao(id_usuario, cpf, nome_usuario);
            }
            con.close();
            statement.close();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Falha ao recuperar usuario com id: " + idUsuario);
        }
    }

    public UsuarioDao getUsuarioByNome(String nomeUsuario) throws Exception {
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "SELECT id_usuario, cpf, nome_usuario FROM usuario WHERE nome_usuario=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, nomeUsuario);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                Integer id_usuario = result.getInt("id_usuario");
                String cpf = result.getString("cpf");
                String nome_usuario = result.getString("nome_usuario");
                con.close();
                statement.close();
                return new UsuarioDao(id_usuario, cpf, nome_usuario);
            }
            con.close();
            statement.close();
            return null;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Falha ao recuperar usuario com nome: " + nomeUsuario);
        }
    }

    public UsuarioDao postUsuario(UsuarioDao usuarioDao) throws Exception {
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "INSERT INTO usuario(cpf, nome_usuario) VALUES (?, ?)";
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, usuarioDao.getCpf());
            statement.setString(2, usuarioDao.getNome());
            statement.executeUpdate();
            System.out.println("Usuário inserida com sucesso.");
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                usuarioDao.setId(generatedKeys.getInt(1));
            } else {
                throw new Exception("Falha ao inserir receita, ID não obtido");
            }
            con.close();
            statement.close();
            return usuarioDao;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Falha ao inserir usuário");
        }
    }
}
