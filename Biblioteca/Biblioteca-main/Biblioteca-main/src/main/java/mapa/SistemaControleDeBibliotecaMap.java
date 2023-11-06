package mapa;

import emprestimo.EmprestimoDosLivros;
import entidades.Livro;
import exceptions.LivroJaExisteException;
import exceptions.LivroNaoExisteException;
import exceptions.ValorInvalidoException;

import java.util.*;

public class SistemaControleDeBibliotecaMap implements SistemaControleDeBiblioteca {

    private Map<String, Livro> livroMap = new HashMap<>();
    private List<EmprestimoDosLivros> listaDeEmprestimos = new ArrayList<>();


    public SistemaControleDeBibliotecaMap(){
    }

    public SistemaControleDeBibliotecaMap(Map<String, Livro> livroMap, List<EmprestimoDosLivros> listaDeEmprestimos) {
        this.livroMap = livroMap;
        this.listaDeEmprestimos = listaDeEmprestimos;
    }

    public Map<String, Livro> getLivrosMap() {
        return livroMap;
    }

    public void setLivroMap(Map<String, Livro> livroMap) {
        this.livroMap = livroMap;
    }

    public List<EmprestimoDosLivros> getListaDeEmprestimos() {
        return listaDeEmprestimos;
    }

    public void setListaDeEmprestimos(List<EmprestimoDosLivros> listaDeEmprestimos) {
        this.listaDeEmprestimos = listaDeEmprestimos;
    }

    @Override
    public void cadastraLivro(Livro livro) throws LivroJaExisteException {

        if (this.livroMap.containsKey(livro.getTitulo())) {
            throw new LivroJaExisteException("Já existe livro com o nome: " + livro.getTitulo());
        } else {
            this.livroMap.put(livro.getTitulo(), livro);
        }
    }

    @Override
    public void removeLivro(Livro livro) throws LivroNaoExisteException {

        if (!this.livroMap.containsKey(livro.getTitulo())) {
            throw new LivroNaoExisteException("Não existe livro com o nome: " + livro.getTitulo());
        } else {
            this.livroMap.remove(livro.getTitulo(), livro);
        }
    }

    @Override
    public boolean existeLivroComTitulo(String titulo) {
        return this.livroMap.containsKey(titulo);
    }


    @Override
    public Livro pesquisaLivroNaBiblioteca(String titulo) throws LivroNaoExisteException {

        if (existeLivroComTitulo(titulo)) {
            return livroMap.get(titulo);
        } else {
            throw new LivroNaoExisteException("Não existe livro com o título: " + titulo);
        }
    }

    @Override
    public List<Livro> verTodosOsLivrosNaBiblioteca() throws ValorInvalidoException {

        List<Livro> listaDeLivros = new ArrayList<>();

        for (Map.Entry<String, Livro> entry : livroMap.entrySet()) {
            Livro livro = entry.getValue();
            if (livro == null) {
                throw new ValorInvalidoException("Valor inválido encontrado na biblioteca.");
            }
            listaDeLivros.add(livro);
        }
        return listaDeLivros;
    }

    @Override
    public List<Livro> listarLivrosEmOrdemAlfabetica() {

        List<Livro> todosOsLivros = new ArrayList<>(livroMap.values());

        Comparator<Livro> comparadorPorTitulo = Comparator.comparing(Livro::getTitulo);
        Collections.sort(todosOsLivros, comparadorPorTitulo);

        return todosOsLivros;
    }

    @Override
    public boolean livroEstaEmprestado(Livro livro) {

        for (EmprestimoDosLivros emprestimo : listaDeEmprestimos) {
            if (emprestimo.getLivro().equals(livro)) {
                return true;
            }
        }
        return false;
    }

    public boolean livroEstaEmprestadoSimOuNao(String resposta){
        if(resposta.equals("Sim")){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public Date getDataEmprestimoDoLivro(Livro livro) {

        for (EmprestimoDosLivros emprestimo : listaDeEmprestimos) {
            if (emprestimo.getLivro().equals(livro)) {
                return emprestimo.getDataEmprestimo();
            }
        }
        return null;
    }
}

