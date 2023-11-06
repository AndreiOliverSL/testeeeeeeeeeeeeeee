package mapa;

import emprestimo.EmprestimoDosLivros;
import entidades.Livro;
import exceptions.LivroJaExisteException;
import exceptions.LivroNaoExisteException;
import exceptions.ValorInvalidoException;

import java.util.Date;
import java.util.List;

public interface SistemaControleDeBiblioteca {
    void cadastraLivro(Livro livro) throws LivroJaExisteException;

    void removeLivro(Livro livro) throws LivroNaoExisteException;

    boolean existeLivroComTitulo(String titulo);

    Livro pesquisaLivroNaBiblioteca(String titulo) throws LivroNaoExisteException;

    List<Livro> verTodosOsLivrosNaBiblioteca() throws ValorInvalidoException;

    List<Livro> listarLivrosEmOrdemAlfabetica();

    boolean livroEstaEmprestado(Livro livro);

    Date getDataEmprestimoDoLivro(Livro livro);

    List<EmprestimoDosLivros> getListaDeEmprestimos();


}
