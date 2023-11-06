package gravador;

import emprestimo.EmprestimoDosLivros;
import entidades.Livro;

import java.io.Serializable;
import java.util.List;

public class DadosParaSerializacao implements Serializable {
    private List<Livro> livros;
    private List<EmprestimoDosLivros> emprestimos;

    public DadosParaSerializacao(){
    }
    public DadosParaSerializacao(List<Livro> livros, List<EmprestimoDosLivros> emprestimos) {
        this.livros = livros;
        this.emprestimos = emprestimos;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<EmprestimoDosLivros> getEmprestimos() {
        return emprestimos;
    }
}

