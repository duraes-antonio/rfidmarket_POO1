package database.supermercado.mercadoria;

import database.core.CoreDAO;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.usuarios.PessoaDAO;
import database.usuarios.PessoaJuridicaDAO;
import modelo.supermercado.mercadoria.Fornecedor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.usuarios.Endereco;

import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Lote;

public abstract class FornecedorDAO extends CoreDAO{

    /**
     * Insere um fornecedor na base de dados;
     * @param fornecedor a ser gravado na base de dados;
     * @return Inteiro que representa o ID do fornecedor inserido no BD;
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static int create(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {

        // Retorne o id da usuarios jurídica que corresponde ao fornecedor;
        return PessoaJuridicaDAO.create(fornecedor);
    }
    
    //Larissa
    //TODO: Fazer melhoria na Query usando filtros uteis
    //Filtros devem ser baseados nas telas do prototipo e o que se pede no git
    //Seguir o modelo de filtro da função ClienteDAO.readClientesBySupermercado(...);
    public static List<Fornecedor> readFornecedoresBySupermercado(Supermercado supermercado)throws ClassNotFoundException, SQLException{
        List<Fornecedor> fornecedores = new ArrayList<>();
      
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string sql;
        String sql = "SELECT pessoa.id, cnpj, nome, numero, rua, cep, bairro, estado, cidade FROM fornecimento "
                + "INNER JOIN juridica ON fornecimento.fk_fornecedor = juridica.fk_pessoa "
                + "INNER JOIN pessoa ON juridica.fk_pessoa = pessoa.id "
                + "WHERE fornecimento.fk_supermercado = ?";

        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, supermercado.getId());
        
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            fornecedores.add(readFornecedor(rs));
        }

        st.close();
        conexao.close();

        return fornecedores;
    }
    
    //Larissa
    public static Fornecedor readFornecedorByLote(Lote lote) throws ClassNotFoundException, SQLException{
        Fornecedor fornecedor = null;
        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();
        // Forme a string sql;
        String sql = "SELECT pessoa.id, cnpj, nome, numero, rua, cep, bairro, estado, cidade FROM lote " //em duvida
                    + "INNER JOIN juridica as fornecedor ON fornecedor.fk_pessoa = lote.fk_fornecedor "
                    + "WHERE lote.id = ? ";
        PreparedStatement st = conexao.prepareStatement (sql);
        st.setInt(1, lote.getId());

        fornecedor = readFornecedor(st);
       
        st.close();
        conexao.close();
        return fornecedor;
    }
    
    
    private static Fornecedor readFornecedor(PreparedStatement st) throws SQLException{
        ResultSet rs = st.executeQuery();
        rs.next();
        return readFornecedor(rs);
    }
    
    //Larissa
    private static Fornecedor readFornecedor(ResultSet rs) throws SQLException{
 
        String cnpj = rs.getString("cnpj");
        String nome = rs.getString("nome");
        int id = rs.getInt("id");
        Endereco endereco = PessoaDAO.getEndereco(rs);
       
        return new Fornecedor(cnpj,id,nome,endereco);
        
    }
}