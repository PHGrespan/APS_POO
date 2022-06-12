package br.edu.unifaj.poo.aps.repository;

import br.edu.unifaj.poo.aps.entity.dao.IngredienteDao;
import br.edu.unifaj.poo.aps.entity.dao.IngredienteReceitaDao;
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
public class IngredienteRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<IngredienteDao> getIngredientes() throws Exception {
        try {
            List<IngredienteDao> ingredientes = new ArrayList<>();
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "SELECT id_ingrediente, nome_ingrediente FROM ingrediente";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                int id_ingrediente = result.getInt("id_ingrediente");
                String nome_ingrediente = result.getString("nome_ingrediente");
                ingredientes.add(new IngredienteDao(id_ingrediente, nome_ingrediente));
            }
            con.close();
            statement.close();
            return ingredientes;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Falha ao recuperar ingredientes");
        }
    }

    public IngredienteDao getIngredienteByNome(String nomeIngrediente) throws Exception {
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "SELECT id_ingrediente, nome_ingrediente FROM ingrediente WHERE nome_ingrediente=?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, nomeIngrediente);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                int id_ingrediente = result.getInt("id_ingrediente");
                String nome_ingrediente = result.getString("nome_ingrediente");
                con.close();
                statement.close();
                return new IngredienteDao(id_ingrediente, nome_ingrediente);
            }
            return null;

        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Falha ao recuperar ingrediente");
        }
    }
}
