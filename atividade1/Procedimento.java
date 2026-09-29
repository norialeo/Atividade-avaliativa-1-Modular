public class Procedimento {
    private String nome;
    private int duracao;
    private double valor;
    private String complexidade;

    public Procedimento(String nome, int duracao, double valor, String complexidade) {
        this.nome = nome;
        this.duracao = duracao;
        this.valor = valor;
        this.complexidade = complexidade;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public double getValor() {
        return valor;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public String toString() {
        return "Procedimento: " + nome + " | Duracao: " + duracao + " min | Valor: R$ " + valor + " | Complexidade: " + complexidade;
    }
}
