package anotacoes;
public class Data implements Comparable<Data> {
    private final int dia;
    private final int mes;
    private final int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }
    
    
    @Override
    public int compareTo(Data data) {
        if (ano != data.ano) {
            return Integer.compare(ano, data.ano);
        }
        if (mes != data.mes) {
            return Integer.compare(mes, data.mes);
        }
        return Integer.compare(dia, data.dia);
    }
    
    @Override
    public String toString() {
        return String.format("%d/%d/%d", dia, mes, ano);
    }
}



