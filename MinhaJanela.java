package anotacoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinhaJanela extends JFrame {
    private JPanel painel_1, painel_2, painel_3;
    private JButton botaoAdicionar, botaoOrdenarCrescente, botaoOrdenarDecrescente, botaoOrdenarTitulo, botaoExibir;
    private JList<String> listaAnotacoes;
    private DefaultListModel<String> listaModel;
    private List<Anotacoes> anotacoes;

    public MinhaJanela() {
        setSize(1024, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Anotacoes");
        setLocationRelativeTo(null );
        anotacoes = new ArrayList<>();
        criaJanela();
        setVisible(true);
    }

    private void criaJanela() {
        painel_1 = new JPanel();
        painel_2 = new JPanel();
        painel_2.setLayout(new BorderLayout());
        painel_3 = new JPanel();
        painel_3.setLayout(new FlowLayout());
        setLayout(new GridLayout(3, 1));
        add(painel_1);
        add(painel_2);
        add(painel_3);

        botaoOrdenarCrescente = new JButton("Ordenar (Crescente)");
        botaoOrdenarDecrescente = new JButton("Ordenar (Decrescente)");
        botaoOrdenarTitulo = new JButton("Ordenar (Titulo)");
        botaoAdicionar = new JButton("Adicionar");
        
        painel_1.add(botaoOrdenarCrescente);
        painel_1.add(botaoOrdenarDecrescente);
        painel_1.add(botaoOrdenarTitulo);
        painel_1.add(botaoAdicionar);

        listaModel = new DefaultListModel<>();
        listaAnotacoes = new JList<>(listaModel);
        JScrollPane scroll = new JScrollPane(listaAnotacoes);
        painel_2.add(scroll, BorderLayout.CENTER);

        botaoExibir = new JButton("Exibir");
        painel_3.add(botaoExibir);
        
        JButton botaoRemover = new JButton("Remover");
        painel_3.add(botaoRemover, new FlowLayout());

        botaoOrdenarCrescente.addActionListener((ActionEvent event) -> {
            ordenarPorData();
            atualizaLista();
        });

        botaoOrdenarDecrescente.addActionListener((ActionEvent event) -> {
            Collections.sort(anotacoes, Collections.reverseOrder(Comparator.comparing(Anotacoes::getData)));
            atualizaLista();
        });


        botaoOrdenarTitulo.addActionListener((ActionEvent event) -> {
            ordenarPorTitulo();
            atualizaLista();
        });

        botaoAdicionar.addActionListener((ActionEvent event) -> {
            JanelaAdd();
        });

        botaoExibir.addActionListener((ActionEvent event) -> {
            int ind = listaAnotacoes.getSelectedIndex();
            if (ind >= 0) {
                JanelaShow(ind);
            }
        });
        
        botaoRemover.addActionListener((ActionEvent event) -> {
            int ind = listaAnotacoes.getSelectedIndex();
            if (ind >= 0) {
                removeAnotacao(ind);
        }
        });

        atualizaLista();
    }

    private void ordenarPorData() {
        Collections.sort(anotacoes, Comparator.comparing(Anotacoes::getData));
    }

    private void ordenarPorTitulo() {
        Collections.sort(anotacoes, Comparator.comparing(Anotacoes::getTitulo));
    }

    private void atualizaLista() {
        listaModel.clear();
        anotacoes.forEach(anotacao -> listaModel.addElement(anotacao.getTitulo()));

    }

    private void JanelaAdd() {
        JFrame janelaAdd = new JFrame("Adicionar");
        janelaAdd.setSize(500, 500);
        janelaAdd.setLayout(new GridLayout(4, 1));

        JTextField tit_text = new JTextField();
        JTextArea cont_text = new JTextArea();
        JTextField data_text = new JTextField();

        JButton botaoSalvar = new JButton("Salvar");

        janelaAdd.add(new JLabel("Titulo:"));
        janelaAdd.add(tit_text);
        janelaAdd.add(new JLabel("Conteudo:"));
        janelaAdd.add(cont_text);
        janelaAdd.add(new JLabel("Data (dd/mm/aaaa):"));
        janelaAdd.add(data_text);
        

        
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painel.add(botaoSalvar);
        janelaAdd.add(painel);
        
        botaoSalvar.addActionListener((ActionEvent eventvent) -> {
        String titulo = tit_text.getText();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "É necessário um titulo");
            return;
        }
        String conteudo = cont_text.getText();
        
        String data_txt = data_text.getText();
        String[] diamesano = data_txt.split("/");
        if (diamesano.length != 3) {
            JOptionPane.showMessageDialog(null, "Insira uma data valida (dd/mm/aaaa)");
            return;
        }

    try {
        int dia = Integer.parseInt(diamesano[0]);
        int mes = Integer.parseInt(diamesano[1]);
        int ano = Integer.parseInt(diamesano[2]);

        Data data = new Data(dia, mes, ano);
        Anotacoes anotacao = new Anotacoes(data, titulo, conteudo);

        anotacoes.add(anotacao);
        atualizaLista();
        janelaAdd.dispose();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Data Invalida");
    }
});

        janelaAdd.setVisible(true);
    }

    private void JanelaShow(int indice) {
    JFrame janela = new JFrame("Anotação");
    janela.setSize(500, 600);
    janela.setLayout(new GridLayout(5, 1));

    Anotacoes anotacao = anotacoes.get(indice);
    JTextArea ExibirTitulo = new JTextArea(anotacao.getTitulo());
    ExibirTitulo.setEditable(false);
    JTextArea ExibirData = new JTextArea(anotacao.getData().toString());
    ExibirData.setEditable(false);
    JTextArea ExibirConteudo = new JTextArea(anotacao.getConteudo());
    ExibirConteudo.setEditable(false);

    JPanel painelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel labelTitulo = new JLabel("Anotação");
    painelTitulo.add(labelTitulo);

    JPanel painelTituloExibir = new JPanel(new BorderLayout());
    painelTituloExibir.add(new JLabel("Título:"), BorderLayout.NORTH);
    painelTituloExibir.add(ExibirTitulo, BorderLayout.CENTER);

    JPanel painelDataExibir = new JPanel(new BorderLayout());
    painelDataExibir.add(new JLabel("Data:"), BorderLayout.NORTH);
    painelDataExibir.add(ExibirData, BorderLayout.CENTER);

    JPanel painelConteudo = new JPanel(new BorderLayout());
    painelConteudo.add(new JLabel("Conteúdo:"), BorderLayout.NORTH);
    painelConteudo.add(new JScrollPane(ExibirConteudo), BorderLayout.CENTER);

    janela.add(painelTitulo);
    janela.add(painelTituloExibir);
    janela.add(painelDataExibir);
    janela.add(painelConteudo);

    janela.setVisible(true);
}

    
    public void removeAnotacao(int indice) {
    if (indice >= 0 && indice < anotacoes.size()) {
        anotacoes.remove(indice);
        atualizaLista();
    }
}
}