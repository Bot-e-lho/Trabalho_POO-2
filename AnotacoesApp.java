package anotacoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnotacoesApp {
    private List<Anotacoes> anotacoes;

    public AnotacoesApp() {
        anotacoes = new ArrayList<>();
    }

    public void adicionarAnotacao(Anotacoes anotacao) {
        anotacoes.add(anotacao);
    }

    public void removeAnotacao(int indice) {
        if (indice >= 0 && indice < anotacoes.size()) {
            anotacoes.remove(indice);
        }
    }

    public void editarAnotacao(int ind, String titulo, String conteudo) {
        if (ind >= 0 && ind < anotacoes.size()) {
            Anotacoes anotacao = anotacoes.get(ind);
            anotacao.setTitulo(titulo);
            anotacao.setConteudo(conteudo);
        }
    }


 public void ordenarPorTitulo() {
    Collections.sort(anotacoes, (anotacao1, anotacao2) -> anotacao1.getTitulo().compareTo(anotacao2.getTitulo()));
}

    public List<Anotacoes> getAnotacoes() {
        return anotacoes;
    }
}