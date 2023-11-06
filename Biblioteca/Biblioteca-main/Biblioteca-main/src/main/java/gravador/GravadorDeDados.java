package gravador;

import emprestimo.EmprestimoDosLivros;
import entidades.Livro;


import java.io.*;
import java.util.List;

public class GravadorDeDados {
    private static final String DADOS_FILENAME = "dados.ser";

    public static void salvarDados(List<Livro> livros, List<EmprestimoDosLivros> emprestimos) {
        try {
            FileOutputStream dadosFileOut = new FileOutputStream(DADOS_FILENAME);
            ObjectOutputStream dadosOut = new ObjectOutputStream(dadosFileOut);

            DadosParaSerializacao dados = new DadosParaSerializacao(livros, emprestimos);
            dadosOut.writeObject(dados);
            dadosOut.close();
            dadosFileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void carregarDados(List<Livro> livros, List<EmprestimoDosLivros> emprestimos) {
        try {
            FileInputStream dadosFileIn = new FileInputStream(DADOS_FILENAME);
            ObjectInputStream dadosIn = new ObjectInputStream(dadosFileIn);

            DadosParaSerializacao dados = (DadosParaSerializacao) dadosIn.readObject();

            livros.clear();
            livros.addAll(dados.getLivros());
            emprestimos.clear();
            emprestimos.addAll(dados.getEmprestimos());

            dadosIn.close();
            dadosFileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
