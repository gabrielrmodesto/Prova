package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Fornecedor;

/**
 *
 * @author gabrielrmodesto
 */
public class FornecedorDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor(f_nome, f_situacao, f_cidade) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getSituacao());
            stmt.setString(3, fornecedor.getCidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET f_nome=?, f_situacao=?, f_cidade=? WHERE fx=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getSituacao());
            stmt.setString(3, fornecedor.getCidade());
            stmt.setInt(4, fornecedor.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Fornecedor fornecedor) {
        String sql = "DELETE FROM fornecedor WHERE fx=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getCodigo());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Fornecedor> listar() {
        String sql = "SELECT * FROM fornecedor";
        List<Fornecedor> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCodigo(resultado.getInt("fx"));
                fornecedor.setNome(resultado.getString("f_nome"));
                fornecedor.setSituacao(resultado.getString("f_situacao"));
                fornecedor.setCidade(resultado.getString("f_cidade"));
                retorno.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public Fornecedor buscar(Fornecedor fornecedor) {
        String sql = "SELECT * FROM fornecedor WHERE fx=?";
        Fornecedor retorno = new Fornecedor();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getCodigo());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                fornecedor.setNome(resultado.getString("f_nome"));
                fornecedor.setSituacao(resultado.getString("f_situacao"));
                fornecedor.setCidade(resultado.getString("f_cidade"));
                retorno = fornecedor;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
