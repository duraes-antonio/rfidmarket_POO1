package database;

import database.core.CoreDAO;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.pessoa.Endereco;

import modelo.supermercado.Funcionario;

import modelo.supermercado.Supermercado;
import org.postgresql.util.PSQLException;

/**
 * Created by 20162bsi0511 on 21/05/2018.
 */
public abstract class FuncionarioDAO extends CoreDAO {

    /**
     * Insere um funcionário na base de dados;
     * @param funcionario funcionário a ser gravado na base de dados;
     * @param supermercado supermercado em que o funcionário trabalha;
     * @return Inteiro que representa o ID do funcionário inserido no BD;
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static int create(Funcionario funcionario, Supermercado supermercado) throws SQLException, ClassNotFoundException {
        int id = PessoaFisicaDAO.create(funcionario); // insere primeiro os dados da pessoa

        Connection conn = getConnection();

        String sql = "INSERT INTO funcionario (setor,cargo,fk_pessoa_fisica,fk_supermercado) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, funcionario.getSetor());
        st.setString(2, funcionario.getCargo());
        st.setInt(3, id);
        st.setInt(4, supermercado.getId());

        try {
            st.executeUpdate();
        }

        catch (PSQLException ex) {
            // se ocorrer algum erro durante a inseção do restante dos dados
            // apaga o que ja foi inserido e lança a exceção
            PessoaFisicaDAO.delete(id);
            throw ex;
        }

        st.close();
        conn.close();

        return id;
    }
    
    //Jennifer
    //TODO: Fazer melhoria Query usando filtros uteis
    //Filtros devem ser baseados nas telas do prototipo e o que se pede no git
    //Seguir o modelo de filtro da função ClienteDAO.readClientesBySupermercado(...);
    public static List<Funcionario> readFuncionariosBySupermercado(Supermercado supermercado) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException {
          List<Funcionario> funcionarios = new ArrayList<>();
      
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT * FROM funcionario "
                + "INNER JOIN fisica on fisica.fk_pessoa = funcionario.fk_pessoa_fisica "
                + "INNER JOIN pessoa on pessoa.id = funcionario.fk_pessoa_fisica "
                + "WHERE fk_supermercado = ?"; 

        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, supermercado.getId());
        
        ResultSet rs = st.executeQuery();
        while (rs.next()) {


            String cargo = rs.getString("cargo");
            String setor = rs.getString("setor");
            String cpf = rs.getString("cpf");
            Date dataNasc = rs.getDate("data_nasc");
            char genero = rs.getString("genero").charAt(0);
            String login = rs.getString("login");
            String senha = rs.getString("senha");
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
   
            Endereco endereco = PessoaDAO.getEndereco(rs);
            

            funcionarios.add(new Funcionario(cargo,setor,cpf,dataNasc,genero,login,senha,id,nome,endereco));
        }

        st.close();
        conexao.close();

        return funcionarios;
        
    }
}
