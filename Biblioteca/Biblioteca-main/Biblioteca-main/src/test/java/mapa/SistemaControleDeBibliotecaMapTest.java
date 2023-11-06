package mapa;

import emprestimo.EmprestimoDosLivros;
import entidades.Livro;
import exceptions.LivroJaExisteException;
import exceptions.LivroNaoExisteException;
import exceptions.ValorInvalidoException;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaControleDeBibliotecaMapTest{
    SistemaControleDeBiblioteca biblioteca = new SistemaControleDeBibliotecaMap();
    List<EmprestimoDosLivros> listaDeEmprestimos;
    @Test
    public void testCadastraLivro() throws LivroJaExisteException {
        Livro livro = new Livro("Livro 1", "Autor 1", "Romance");

        // Verifica se o livro foi cadastrado com sucesso
        biblioteca.cadastraLivro(livro);
        assertTrue(biblioteca.existeLivroComTitulo("Livro 1"));

        // Tenta cadastrar o mesmo livro novamente, deve lançar uma exceção
        try {
            biblioteca.cadastraLivro(livro);
            fail("Deveria ter lançado LivroJaExisteException");
        } catch (LivroJaExisteException e) {
            assertEquals("Já existe livro com o nome: Livro 1", e.getMessage());
        }
    }

    @Test
    public void testRemoveLivro() throws LivroJaExisteException {
        Livro livro = new Livro("Livro 1", "Autor 1", "Terror");
        biblioteca.cadastraLivro(livro);

        // Verifica se o livro foi cadastrado com sucesso
        assertTrue(biblioteca.existeLivroComTitulo("Livro 1"));

        // Tenta remover o livro cadastrado
        try {
            biblioteca.removeLivro(livro);
        } catch (LivroNaoExisteException e) {
            fail("Não deveria lançar LivroNaoExisteException");
        }

        // Verifica se o livro foi removido com sucesso
        assertFalse(biblioteca.existeLivroComTitulo("Livro 1"));

        // Tenta remover o mesmo livro novamente, deve lançar uma exceção
        try {
            biblioteca.removeLivro(livro);
            fail("Deveria ter lançado LivroNaoExisteException");
        } catch (LivroNaoExisteException e) {
            assertEquals("Não existe livro com o nome: Livro 1", e.getMessage());
        }
    }
    @Test
    public void testExisteLivroComTitulo() throws LivroJaExisteException {
        Livro livro1 = new Livro("Livro 1", "Autor 1", "Ação");
        Livro livro2 = new Livro("Livro 2", "Autor 2", "Comédia");

        assertFalse(biblioteca.existeLivroComTitulo("Livro 1"));
        assertFalse(biblioteca.existeLivroComTitulo("Livro 2"));


        biblioteca.cadastraLivro(livro1);
        biblioteca.cadastraLivro(livro2);


        assertTrue(biblioteca.existeLivroComTitulo("Livro 1"));
        assertTrue(biblioteca.existeLivroComTitulo("Livro 2"));
    }

    @Test
    public void testPesquisaLivroNaBiblioteca() throws LivroNaoExisteException, LivroJaExisteException {
        Livro livro = new Livro("Livro 1", "Autor 1", "Romance");
        biblioteca.cadastraLivro(livro);

        Livro livroEncontrado = biblioteca.pesquisaLivroNaBiblioteca("Livro 1");

        assertNotNull(livroEncontrado);
        assertEquals("Livro 1", livroEncontrado.getTitulo());
    }

    @Test
    public void testVerTodosOsLivrosNaBiblioteca() throws ValorInvalidoException, LivroJaExisteException {
        Livro livro1 = new Livro("Livro 1", "Autor 1", "Terror");
        Livro livro2 = new Livro("Livro 2", "Autor 2", "Romance");

        biblioteca.cadastraLivro(livro1);
        biblioteca.cadastraLivro(livro2);

        List<Livro> livros = biblioteca.verTodosOsLivrosNaBiblioteca();

        assertEquals(2, livros.size());
        assertTrue(livros.contains(livro1));
        assertTrue(livros.contains(livro2));
    }

    @Test
    public void testListarLivrosEmOrdemAlfabetica() throws LivroJaExisteException {
        Livro livro2 = new Livro("Livro B", "Autor B", "Comédia");
        Livro livro1 = new Livro("Livro A", "Autor A", "Ação");

        biblioteca.cadastraLivro(livro2);
        biblioteca.cadastraLivro(livro1);

        List<Livro> livros = biblioteca.listarLivrosEmOrdemAlfabetica();

        assertEquals("Livro A", livros.get(0).getTitulo());
        assertEquals("Livro B", livros.get(1).getTitulo());
    }
    @Test
    public void testGetDataEmprestimoDoLivro() throws LivroJaExisteException {
        Livro livro = new Livro("Livro Emprestado", "Autor", "Romance");
        biblioteca.cadastraLivro(livro);

        assertNull(biblioteca.getDataEmprestimoDoLivro(livro));

        EmprestimoDosLivros emprestimo = new EmprestimoDosLivros(livro, new Date());
        biblioteca.getListaDeEmprestimos().add(emprestimo);

        assertNotNull(biblioteca.getDataEmprestimoDoLivro(livro));
    }
}
