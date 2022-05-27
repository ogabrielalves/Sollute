package sollute.estoquecerto.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import sollute.estoquecerto.entity.*;
import sollute.estoquecerto.repository.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {EmpresaController.class})
class EmpresaControllerTest {

    @Autowired
    EmpresaController empresaController;

    @MockBean
    private EmpresaRepository empresaRepository;

    @MockBean
    private ProdutoRepository produtoRepository;

    @MockBean
    private CaixaRepository caixaRepository;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @MockBean
    private FornecedorRepository fornecedorRepository;


    // Testes da empresa
    @Test
    @DisplayName("Sem empresas deveria retornar 204 SEM corpo")
    void getListaEmpresaVazia() {
        when(empresaRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Empresa>> resposta = empresaController.listarEmpresas();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Com empresas deveria retornar 200 COM corpo")
    void getListaEmpresaPreenchida() {

        Empresa empresa1 = mock(Empresa.class);
        Empresa empresa2 = mock(Empresa.class);
        List<Empresa> listaMock = List.of(empresa1, empresa2);

        when(empresaRepository.findAll()).thenReturn(listaMock);

        ResponseEntity<List<Empresa>> resposta = empresaController.listarEmpresas();

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(listaMock, resposta.getBody());
    }



    // Testes do Produto
    @Test
    @DisplayName("Sem produtos deveria retornar 204 SEM corpo")
    void getListaProdutosVazia() {
        when(produtoRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Produto>> resposta = empresaController.listarProdutos(1);

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Com produtos deveria retornar 200 COM corpo")
    void getListaProdutoPreenchido() {

        Produto produto1 = mock(Produto.class);
        Produto produto2 = mock(Produto.class);
        List<Produto> listaMock = List.of(produto1, produto2);

        Integer idEmpresa = 1;
        when(produtoRepository.findByFkEmpresaIdEmpresa(idEmpresa)).thenReturn(listaMock);

        ResponseEntity<List<Produto>> resposta = empresaController.listarProdutos(idEmpresa);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(listaMock, resposta.getBody());
    }

    @Test
    @DisplayName("Criar produtos deve retornar 200 SEM corpo")
    void postAdicionaProduto() {
        Produto produto = mock(Produto.class);

        Integer idEmpresa = 1;
        when(empresaRepository.existsById(idEmpresa)).thenReturn(true);
        when(produtoRepository.save(produto)).thenReturn(null);

        ResponseEntity resposta = empresaController.adicionaProduto(produto, 1);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }



    // Testes do Funcionario
    @Test
    @DisplayName("Sem funcionarios deveria retornar 204 SEM corpo")
    void getListaFuncionariosVazia() {
        when(funcionarioRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Funcionario>> resposta = empresaController.listarFuncionario(1);

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Com funcionario deveria retornar 200 COM corpo")
    void getListaFuncionariosPreenchido() {

        Funcionario funcionario1  = mock(Funcionario.class);
        Funcionario funcionario2 = mock(Funcionario.class);
        List<Funcionario> listaMock = List.of(funcionario1, funcionario2);

        Integer idEmpresa = 1;
        when(funcionarioRepository.findByFkEmpresaIdEmpresa(idEmpresa)).thenReturn(listaMock);

        ResponseEntity<List<Funcionario>> resposta = empresaController.listarFuncionario(idEmpresa);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(listaMock, resposta.getBody());
    }

    @Test
    @DisplayName("Criar funcionario deve retornar 200 SEM corpo")
    void postCriaFuncionario() {
        Funcionario funcionario = mock(Funcionario.class);

        Integer idEmpresa = 1;
        when(empresaRepository.existsById(idEmpresa)).thenReturn(true);
        when(funcionarioRepository.save(funcionario)).thenReturn(null);

        ResponseEntity resposta = empresaController.criarFuncionario(funcionario, 1);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }



    // Testes do Fornecedor
    @Test
    @DisplayName("Sem fornecedores deveria retornar 204 SEM corpo")
    void getListaFornecedoresVazia() {
        when(fornecedorRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Fornecedor>> resposta = empresaController.listarFornecedor(1);

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Com fornecedores deveria retornar 200 COM corpo")
    void getListaFornecedoresPreenchido() {

        Fornecedor fornecedor1  = mock(Fornecedor.class);
        Fornecedor fornecedor2 = mock(Fornecedor.class);
        List<Fornecedor> listaMock = List.of(fornecedor1, fornecedor2);

        Integer idEmpresa = 1;
        when(fornecedorRepository.findByfkEmpresaIdEmpresa(idEmpresa)).thenReturn(listaMock);

        ResponseEntity<List<Fornecedor>> resposta = empresaController.listarFornecedor(idEmpresa);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(listaMock, resposta.getBody());
    }

    @Test
    @DisplayName("Criar fornecedor deve retornar 200 SEM corpo")
    void postCriaFornecedor() {
        Fornecedor fornecedor = mock(Fornecedor.class);

        Integer idEmpresa = 1;
        when(empresaRepository.existsById(idEmpresa)).thenReturn(true);
        when(fornecedorRepository.save(fornecedor)).thenReturn(null);

        ResponseEntity resposta = empresaController.criarFornecedor(fornecedor, 1);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }



    // Testes do Cliente
    @Test
    @DisplayName("Sem clientes deveria retornar 204 SEM corpo")
    void getListaClientesVazia() {
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Cliente>> resposta = empresaController.listarCliente(1);

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Com clientes deveria retornar 200 COM corpo")
    void getListaClientesPreenchido() {

        Cliente cliente1  = mock(Cliente.class);
        Cliente cliente2 = mock(Cliente.class);
        List<Cliente> listaMock = List.of(cliente1, cliente2);

        Integer idEmpresa = 1;
        when(clienteRepository.findByFkEmpresaIdEmpresa(idEmpresa)).thenReturn(listaMock);

        ResponseEntity<List<Cliente>> resposta = empresaController.listarCliente(idEmpresa);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(listaMock, resposta.getBody());
    }

    @Test
    @DisplayName("Criar fornecedor deve retornar 200 SEM corpo")
    void postAdicionaCliente() {
        Cliente cliente = mock(Cliente.class);

        Integer idEmpresa = 1;
        when(empresaRepository.existsById(idEmpresa)).thenReturn(true);
        when(clienteRepository.save(cliente)).thenReturn(null);

        ResponseEntity resposta = empresaController.adicionaCliente(cliente, 1);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }
}