package controller;
import entidades.Livro;
import exceptions.LivroNaoExisteException;
import mapa.SistemaControleDeBibliotecaMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PesquisaDeLivroController extends JFrame {

    private JTextField pesquisaTituloField;
    private JButton pesquisarButton;
    private JButton ordemAlfabeticaButton;
    private JTextArea resultadoTextArea;

    private SistemaControleDeBibliotecaMap sistemaControleDeBibliotecaMap;

    public PesquisaDeLivroController() {
        setTitle("Pesquisa de Livros");
        setSize(300, 200);
        setResizable(false);
        createComponents();

        sistemaControleDeBibliotecaMap = new SistemaControleDeBibliotecaMap();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void createComponents() {
        setLayout(new BorderLayout());

        pesquisaTituloField = new JTextField(10);
        pesquisarButton = new JButton(" Pesquisar ");
        ordemAlfabeticaButton = new JButton(" Ver todos ");

        JPanel pesquisaPanel = new JPanel();
        JPanel pesquisaPanelAlfabetico = new JPanel();
        pesquisaPanel.add(new JLabel("Título: "));
        pesquisaPanel.add(pesquisaTituloField);
        pesquisaPanel.add(pesquisarButton);
        pesquisaPanelAlfabetico.add(ordemAlfabeticaButton);

        add(pesquisaPanel, BorderLayout.NORTH);
        add(pesquisaPanelAlfabetico,BorderLayout.SOUTH);
        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);

        add(new JScrollPane(resultadoTextArea), BorderLayout.CENTER);

        pesquisarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pesquisarLivro();
            }
        });

        ordemAlfabeticaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarLivrosEmOrdemAlfabetica();
            }
        });
    }

    private void pesquisarLivro() {
        String tituloPesquisado = pesquisaTituloField.getText();
        try {
            Livro livroEncontrado = sistemaControleDeBibliotecaMap.pesquisaLivroNaBiblioteca(tituloPesquisado);
            resultadoTextArea.setText("Livro encontrado:\n" + livroEncontrado.toString());
        } catch (LivroNaoExisteException e) {
            resultadoTextArea.setText("Livro não encontrado" );
        }
    }

    private void listarLivrosEmOrdemAlfabetica() {
        List<Livro> livrosEmOrdemAlfabetica = sistemaControleDeBibliotecaMap.listarLivrosEmOrdemAlfabetica();
        resultadoTextArea.setText("Livros:\n");
        for (Livro livro : livrosEmOrdemAlfabetica) {
            resultadoTextArea.append(livro.toString() + "\n");
        }
    }
}
