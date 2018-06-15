/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.supermercado.mercadoria;

import java.util.Date;
import util.Util;

public class Lote {
    // nenhum dos atributos pode ser null
    private int id;               //não pode ser alterado depois de instanciado e nem <= 0
    private final Date dataCompra;
    private final Date dataFabricacao;
    private final Date dataValidade;
    private final int numUnidades;      //não pode ser <= 0
    private final String identificador;
    private final Produto produto;      // deve ser carregado e não passado para construtor

    // Pode ser usada quando para instanciar a partir de dados do BD
    public Lote(int id, Date dataCompra, Date dataFabricacao, Date dataValidade, int numUnidades, String identificador, Produto produto) throws IllegalArgumentException{
        Util.verificaID(id);
        Util.verificaIsObjNull(dataCompra, "Data de compra");
        Util.verificaIsObjNull(dataFabricacao, "Data de fabricação");
        Util.verificaIsObjNull(dataValidade, "Data de validade");
        Util.verificaStringNullVazia(identificador, "Identificador");
        Util.verificaIsObjNull(produto, "Produto");
        
        if (numUnidades <= 0) throw new IllegalArgumentException("Número de unidade menor ou igual a 0!");
        
        this.id = id;
        this.dataCompra = dataCompra;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.numUnidades = numUnidades;
        this.identificador = identificador;
        this.produto = produto;    // Fazer carregamento do produto aki
    }
    
    // Pode ser usada quando for instaciar um objeto novo e que será salvo posteriormente no BD
    public Lote(Date dataCompra, Date dataFabricacao, Date dataValidade, int numUnidades, String identificador, Produto produto) throws IllegalArgumentException{
        Util.verificaIsObjNull(dataCompra, "Data de compra");
        Util.verificaIsObjNull(dataFabricacao, "Data de fabricação");
        Util.verificaIsObjNull(dataValidade, "Data de validade");
        Util.verificaStringNullVazia(identificador, "Identificador");
        Util.verificaIsObjNull(produto, "Produto");
         
        if (numUnidades <= 0) throw new IllegalArgumentException("Número de unidade menor ou igual a 0!");
        
        this.dataCompra = dataCompra;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.numUnidades = numUnidades;
        this.identificador = identificador;
        this.produto = produto;    
    }
    
    public int getId() {
        return id;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public int getNumUnidades() {
        return numUnidades;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Produto getProduto() {
        return produto;
    }
    
    
}
