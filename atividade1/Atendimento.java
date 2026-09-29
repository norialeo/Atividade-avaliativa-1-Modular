public class Atendimento {
    private int codigo;
    private String nomeAnimal;
    private String especie;
    private String nomeTutor;
    private String data;
    private String horario;
    private String status;
    private String observacoes;
    private Procedimento procedimento;
    private Sala sala;

    public Atendimento(int codigo, String nomeAnimal, String especie, String nomeTutor, String data, String horario, String observacoes, Procedimento procedimento) {
        this.codigo = codigo;
        this.nomeAnimal = nomeAnimal;
        this.especie = especie;
        this.nomeTutor = nomeTutor;
        this.data = data;
        this.horario = horario;
        this.observacoes = observacoes;
        this.procedimento = procedimento;
        this.status = "agendado";
        this.sala = null;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getStatus() {
        return status;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public Sala getSala() {
        return sala;
    }

    public void iniciar(Sala sala) {
        this.sala = sala;
        this.status = "em andamento";
    }

    public void finalizar() {
        this.status = "finalizado";
    }

    public String toString() {
        String texto = "Codigo: " + codigo + "\n";
        texto += "Animal: " + nomeAnimal + " | Especie: " + especie + "\n";
        texto += "Tutor: " + nomeTutor + "\n";
        texto += "Data: " + data + " | Horario: " + horario + "\n";
        texto += "Status: " + status + "\n";
        texto += "Observacoes: " + observacoes + "\n";
        texto += procedimento.toString() + "\n";
        if (sala != null) {
            texto += sala.toString() + "\n";
            if (sala.getVeterinario() != null) {
                texto += sala.getVeterinario().toString() + "\n";
            }
        } else {
            texto += "Sem sala atribuida\n";
        }
        return texto;
    }
}
