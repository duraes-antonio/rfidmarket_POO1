/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.supermercado.mercadoria;

import java.util.List;
import modelo.supermercado.Supermercado;
import modelo.supermercado.mercadoria.Fornecedor;
import modelo.supermercado.mercadoria.Lote;
import modelo.supermercado.mercadoria.Produto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20162bsi0147
 */
public class LoteDAOTest {
    
    public LoteDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class LoteDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Lote lote = null;
        Fornecedor forn = null;
        Produto prod = null;
        Supermercado superm = null;
        int expResult = 0;
        int result = LoteDAO.create(lote, forn, prod, superm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readLotesBySupermercado method, of class LoteDAO.
     */
    @Test
    public void testReadLotesBySupermercado() throws Exception {
        System.out.println("readLotesBySupermercado");
        Supermercado superm = null;
        List<Lote> expResult = null;
        List<Lote> result = LoteDAO.readLotesBySupermercado(superm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readLotesByProduto method, of class LoteDAO.
     */
    @Test
    public void testReadLotesByProduto() throws Exception {
        System.out.println("readLotesByProduto");
        Produto produto = null;
        List<Lote> expResult = null;
        List<Lote> result = LoteDAO.readLotesByProduto(produto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readLotesByFornecedor method, of class LoteDAO.
     */
    @Test
    public void testReadLotesByFornecedor() throws Exception {
        System.out.println("readLotesByFornecedor");
        Fornecedor fornecedor = null;
        List<Lote> expResult = null;
        List<Lote> result = LoteDAO.readLotesByFornecedor(fornecedor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}