package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaGUI extends JFrame {

    public BibliotecaGUI() {
        setTitle("Biblioteca");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        String imagePath = "livrosbiblioteca.jpg";

        JLabel backgroundLabel = new JLabel(new ImageIcon(imagePath));

        setLayout(new BorderLayout());

        add(backgroundLabel, BorderLayout.CENTER);

        createMenuBar();
        createButtonsPanel();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Opções: ");
        menuBar.add(menu);


        JMenuItem cadastro = new JMenuItem("Cadastro");
        JMenuItem pesquisa = new JMenuItem("Pesquisa");
        JMenuItem operacoes = new JMenuItem("Operações");
        JMenuItem salvar = new JMenuItem("Salvar");
        JMenuItem sair = new JMenuItem("Sair");

        menu.add(cadastro);
        menu.add(pesquisa);
        menu.add(operacoes);
        menu.add(salvar);
        menu.add(sair);


        cadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CadastroDeLivroController cadastroDoLivro = new CadastroDeLivroController();
            }
        });

        pesquisa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PesquisaDeLivroController pesquisaDoLivro = new PesquisaDeLivroController();
            }
        });

        operacoes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OperacoesControllerGUI operacoesController = new OperacoesControllerGUI();
            }
        });

        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SalvarControllerGUI salvar = new SalvarControllerGUI();
            }
        });

        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fechar o programa
            }
        });
    }

    private void createButtonsPanel() {

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());


        JButton botao1 = new JButton("1 - Cadastro");
        JButton botao2 = new JButton("2 - Pesquisa");
        JButton botao3 = new JButton("3 - Operações");
        JButton botao4 = new JButton("4 - Salvar");
        JButton botao5 = new JButton("5 - Sair");


        centerPanel.add(botao1);
        centerPanel.add(botao2);
        centerPanel.add(botao3);
        centerPanel.add(botao4);
        centerPanel.add(botao5);


        add(centerPanel, BorderLayout.NORTH);


        botao1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CadastroDeLivroController cadastroDoLivro = new CadastroDeLivroController();
            }
        });

        botao2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PesquisaDeLivroController pesquisaDoLivro = new PesquisaDeLivroController();
            }
        });

        botao3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OperacoesControllerGUI operacoesController = new OperacoesControllerGUI();
            }
        });

        botao4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SalvarControllerGUI salvar = new SalvarControllerGUI();
            }
        });

        botao5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BibliotecaGUI();
            }
        });
    }
}

