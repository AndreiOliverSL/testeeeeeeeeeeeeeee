package controller;

import entidades.Livro;
import exceptions.LivroJaExisteException;
import exceptions.LivroNaoExisteException;
import mapa.SistemaControleDeBibliotecaMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroDeLivroControllerGUI extends JFrame {

    private JTextField tituloField;
    private JTextField autorField;
    private JTextField generoField;
    private JButton cadastrarButton;
    private JButton removerButton;

    public CadastroDeLivroControllerGUI() {
        setTitle("Cadastro de Livro");
        setSize(300, 250);
        setResizable(false);
        createComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        String imagePath = "livrosbiblioteca.jpg";

        JLabel backgroundLabel = new JLabel(new ImageIcon(imagePath));

        setLayout(new BorderLayout());

        add(backgroundLabel, BorderLayout.CENTER);

    }

    private void createComponents() {

        setLayout(new GridLayout(4, 2));


        JLabel tituloLabel = new JLabel("           Título: ");
        tituloField = new JTextField();
        JLabel autorLabel = new JLabel("            Autor: ");
        autorField = new JTextField();
        JLabel generoLabel = new JLabel("           Gênero: ");
        generoField = new JTextField();

        add(tituloLabel);
        add(tituloField);
        add(autorLabel);
        add(autorField);
        add(generoLabel);
        add(generoField);


        cadastrarButton = new JButton("Cadastrar Livro");
        removerButton = new JButton("Remover Livro");

        add(cadastrarButton);
        add(removerButton);


        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });

        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerLivro();
            }
        });
    }

    private void cadastrarLivro() {

        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String genero = generoField.getText();

        try {
            SistemaControleDeBibliotecaMap sistemaControleDeBibliotecaMap = new SistemaControleDeBibliotecaMap();
            Livro novoLivro = new Livro(titulo, autor, genero);

            if (sistemaControleDeBibliotecaMap.existeLivroComTitulo(titulo)) {
                JOptionPane.showMessageDialog(this, "Livro já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                sistemaControleDeBibliotecaMap.cadastraLivro(novoLivro);
                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!\n\n" + novoLivro, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (LivroJaExisteException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerLivro() {

        String titulo = tituloField.getText();

        try {
            SistemaControleDeBibliotecaMap sistemaControleDeBibliotecaMap = new SistemaControleDeBibliotecaMap();
            if (sistemaControleDeBibliotecaMap.existeLivroComTitulo(titulo)) {
                Livro livro = sistemaControleDeBibliotecaMap.pesquisaLivroNaBiblioteca(titulo);
                sistemaControleDeBibliotecaMap.removeLivro(livro);
                JOptionPane.showMessageDialog(this, "Livro removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Livro não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (LivroNaoExisteException e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
