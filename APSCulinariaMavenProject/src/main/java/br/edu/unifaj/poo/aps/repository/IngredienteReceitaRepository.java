package br.edu.unifaj.poo.aps.repository;

import br.edu.unifaj.poo.aps.entity.dao.IngredienteReceitaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class IngredienteReceitaRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public IngredienteReceitaDao postIngredienteReceita(IngredienteReceitaDao ingredienteReceitaDao) throws Exception {
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String query = "INSERT INTO ingrediente_receita(id_ingrediente, id_receita, quantidade) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, ingredienteReceitaDao.getIngrediente().getId());
            statement.setInt(2, ingredienteReceitaDao.getReceita().getId());
            statement.setString(3, ingredienteReceitaDao.getQuantidade());
            if(statement.executeUpdate() == 1){
                System.out.println("Ingrediente inserido com sucesso");
            } else {
                throw new Exception("Falha ao inserir ingrediente");
            }
            con.close();
            statement.close();
            return ingredienteReceitaDao;
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Falha ao inserir ingrediente");
        }
    }
}
