package controller;

import gravador.DadosParaSerializacao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SalvarControllerGUI {

    public static void salvarDados() {
        try {
            DadosParaSerializacao dados = new DadosParaSerializacao();

            FileOutputStream livrosFileOut = new FileOutputStream("livros.ser");
            ObjectOutputStream livrosOut = new ObjectOutputStream(livrosFileOut);
            livrosOut.writeObject(DadosParaSerializacao.getLivros());
            livrosOut.close();
            livrosFileOut.close();

            FileOutputStream emprestimosFileOut = new FileOutputStream("emprestimos.ser");
            ObjectOutputStream emprestimosOut = new ObjectOutputStream(emprestimosFileOut);
            emprestimosOut.writeObject(DadosParaSerializacao.getEmprestimos());
            emprestimosOut.close();
            emprestimosFileOut.close();

            JOptionPane.showMessageDialog(null, "Dados salvos!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ActionListener criarSalvarActionListener() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarDados();
            }
        };
    }
}
