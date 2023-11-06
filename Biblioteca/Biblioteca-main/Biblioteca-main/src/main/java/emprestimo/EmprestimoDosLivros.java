package emprestimo;

import entidades.Livro;

import java.util.Date;
import java.util.List;

public class EmprestimoDosLivros {
    private Livro livro;
    private Date dataEmprestimo;

    public EmprestimoDosLivros(){
    }

    public EmprestimoDosLivros(Livro livro, Date dataEmprestimo) {
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
    }

    public Livro getLivro() {
        return livro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
}
