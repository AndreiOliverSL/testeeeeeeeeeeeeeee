package controller;

import exceptions.LivroNaoExisteException;
import mapa.SistemaControleDeBibliotecaMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OperacoesControllerGUI extends JFrame {

    private JTextField livroField;
    private JButton verificarButton;
    SistemaControleDeBibliotecaMap sistemaControleDeBibliotecaMap = new SistemaControleDeBibliotecaMap();
    public OperacoesControllerGUI() {
        setTitle("Operações");
        setSize(250, 150);
        setResizable(false);

        createComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        SistemaControleDeBibliotecaMap sistemaControleDeBibliotecaMap = new SistemaControleDeBibliotecaMap();
    }

    private void createComponents() {
        setLayout(new FlowLayout());

        JLabel livroLabel = new JLabel("Qual o nome do livro?");
        livroField = new JTextField(15);
        verificarButton = new JButton("Verificar");

        add(livroLabel);
        add(livroField);
        add(verificarButton);

        verificarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String NomeLivro = livroField.getText();
                String resposta = JOptionPane.showInputDialog("O livro será emprestado? (Sim ou Não)");

                if (resposta != null && resposta.equalsIgnoreCase("Sim")) {
                    if (sistemaControleDeBibliotecaMap.existeLivroComTitulo(NomeLivro)) {
                        Date dataEmprestimo;
                        try {
                            dataEmprestimo = sistemaControleDeBibliotecaMap.getDataEmprestimoDoLivro(
                                    sistemaControleDeBibliotecaMap.pesquisaLivroNaBiblioteca(NomeLivro)
                            );
                        } catch (LivroNaoExisteException ex) {
                            throw new RuntimeException(ex);
                        }

                        if (dataEmprestimo != null) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            String mensagem = "Livro emprestado com sucesso.\nLivro: " + NomeLivro
                                    + "\nData de empréstimo: " + dateFormat.format(dataEmprestimo);

                            JOptionPane.showMessageDialog(OperacoesControllerGUI.this, mensagem);
                        }
                    } else {
                        JOptionPane.showMessageDialog(OperacoesControllerGUI.this, "Livro não encontrado.");
                    }
                }
            }
        });
    }
}
