package database;

import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;
import java.sql.*;

public class LoteDAO extends DBCommand {

    /**
     * Insere um lote de um produto na base de dados;
     * @param lote lote a ser escrito na base de dados;
     * @param forn fornecedor do lote de produtos;
     * @param prod produto que corresponde as unidades do lote;
     * @param superm supermercado proprietário do lote de produtos;
     * @return Inteiro que representa o ID do lote inserido no BD;
     */
    public static int create(Lote lote, Fornecedor forn, Produto prod, Supermercado superm)
            throws ClassNotFoundException, SQLException {

        // Obtenha a conexão com o BD;
        Connection conexao = getConnection();

        // Forme a string básica de sql;
        String sql = "INSERT INTO lote" +
                "(data_compra, numero, fabricacao, quantidade, validade," +
                "fk_fornecedor, fk_produto, fk_supermecado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps;
        ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Defina os valores que ocuparão as '?' na ordem acima;
        ps.setDate(1, new Date(lote.getDataCompra().getTime()));
        ps.setString(2, lote.getIdentificador());
        ps.setDate(3, new Date(lote.getDataFabricacao().getTime()));
        ps.setInt(4, lote.getNumUnidades());
        ps.setDate(5, new Date(lote.getDataValidade().getTime()));
        ps.setInt(6, forn.getId());
        ps.setInt(7, prod.getId());
        ps.setInt(8, superm.getId());

        // Execute o INSERT e receba o ID do cartão cadastrado no BD;
        ps.executeUpdate();
        int id = getIdAtCreate(ps);

        ps.close();
        conexao.close();

        return id;
    }
}
