import java.util.ArrayList;


public class Sala {
    private int numero;
    private String bloco;
    private int capacidade;
    private String tipo;
    private Veterinario veterinario;
    private ArrayList<Atendimento> atendimentos;

    public Sala(int numero, String bloco, int capacidade, String tipo) {
        this.numero = numero;
        this.bloco = bloco;
        this.capacidade = capacidade;
        this.tipo = tipo;
        this.atendimentos = new ArrayList<Atendimento>();
    }

    public int getNumero() {
        return numero;
    }

    public String getBloco() {
        return bloco;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getTipo() {
        return tipo;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public boolean adicionarAtendimento(Atendimento a) {
        if (atendimentos.size() >= capacidade) {
            return false;
        }
        if (atendimentos.size() > 0) {
            String nomeProc = atendimentos.get(0).getProcedimento().getNome();
            if (!nomeProc.equals(a.getProcedimento().getNome())) {
                return false;
            }
        }
        atendimentos.add(a);
        return true;
    }

    public void removerAtendimento(Atendimento a) {
        atendimentos.remove(a);
    }

    public String toString() {
        String vet = "sem veterinario";
        if (veterinario != null) {
            vet = veterinario.getNome();
        }
        return "Sala " + numero + " | Bloco: " + bloco + " | Capacidade: " + capacidade + " | Tipo: " + tipo + " | Responsavel: " + vet;
    }
}


