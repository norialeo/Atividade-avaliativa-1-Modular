import java.util.ArrayList;

import java.util.Scanner;

public class Main {
    static ArrayList<Veterinario> veterinarios = new ArrayList<Veterinario>();
    static ArrayList<Sala> salas = new ArrayList<Sala>();
    static ArrayList<Atendimento> atendimentos = new ArrayList<Atendimento>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        criarDados();
        int op = -1;
        while (op != 0) {
            System.out.println("CLINICA VETERINARIA");
            System.out.println("1 - Cadastrar atendimento");
            System.out.println("2 - Associar veterinario a uma sala");
            System.out.println("3 - Atribuir atendimento a uma sala");
            System.out.println("4 - Exibir atendimentos de uma sala");
            System.out.println("5 - Total de atendimentos finalizados por sala");
            System.out.println("6 - Buscar atendimentos por status");
            System.out.println("7 - Exibir detalhes de um atendimento");
            System.out.println("8 - Finalizar atendimento");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            if (op == 1) {
                cadastrarAtendimento();
            } else if (op == 2) {
                associarVeterinario();
            } else if (op == 3) {
                atribuirAtendimento();
            } else if (op == 4) {
                exibirAtendimentosSala();
            } else if (op == 5) {
                totalFinalizados();
            } else if (op == 6) {
                buscarPorStatus();
            } else if (op == 7) {
                exibirDetalhes();
            } else if (op == 8) {
                finalizarAtendimento();
            } else if (op != 0) {
                System.out.println("Opcao invalida");
            }
        }
    }

    public static void criarDados() {
        veterinarios.add(new Veterinario("Ana", "111.111.111-11", "Clinico Geral", "3199999-1111"));
        veterinarios.add(new Veterinario("Bruno", "222.222.222-22", "Cirurgia", "3199999-2222"));
        veterinarios.add(new Veterinario("Carla", "333.333.333-33", "Dermatologia", "3199999-3333"));
        salas.add(new Sala(1, "A", 5, "Consulta"));
        salas.add(new Sala(2, "B", 3, "Cirurgia"));
        salas.add(new Sala(3, "C", 4, "Exames"));
    }

    public static Sala buscarSala(int numero) {
        for (Sala s : salas) {
            if (s.getNumero() == numero) {
                return s;
            }
        }
        return null;
    }

    public static Atendimento buscarAtendimento(int codigo) {
        for (Atendimento a : atendimentos) {
            if (a.getCodigo() == codigo) {
                return a;
            }
        }
        return null;
    }

    public static void cadastrarAtendimento() {
        System.out.print("Codigo: ");
        int codigo = Integer.parseInt(sc.nextLine());
        if (buscarAtendimento(codigo) != null) {
            System.out.println("Ja existe atendimento com esse codigo");
            return;
        }
        System.out.print("Nome do animal: ");
        String animal = sc.nextLine();
        System.out.print("Especie: ");
        String especie = sc.nextLine();
        System.out.print("Nome do tutor: ");
        String tutor = sc.nextLine();
        System.out.print("Data: ");
        String data = sc.nextLine();
        System.out.print("Horario: ");
        String horario = sc.nextLine();
        System.out.print("Observacoes: ");
        String obs = sc.nextLine();
        System.out.print("Nome do procedimento: ");
        String nomeProc = sc.nextLine();
        System.out.print("Duracao estimada (min): ");
        int duracao = Integer.parseInt(sc.nextLine());
        System.out.print("Valor: ");
        double valor = Double.parseDouble(sc.nextLine());
        System.out.print("Nivel de complexidade: ");
        String complexidade = sc.nextLine();

        Procedimento p = new Procedimento(nomeProc, duracao, valor, complexidade);
        atendimentos.add(new Atendimento(codigo, animal, especie, tutor, data, horario, obs, p));
        System.out.println("Atendimento cadastrado!");
    }

    public static void associarVeterinario() {
        for (int i = 0; i < veterinarios.size(); i++) {
            System.out.println(i + " - " + veterinarios.get(i).getNome());
        }
        System.out.print("Escolha o veterinario: ");
        int i = Integer.parseInt(sc.nextLine());
        if (i < 0 || i >= veterinarios.size()) {
            System.out.println("Veterinario invalido");
            return;
        }
        Veterinario v = veterinarios.get(i);

        for (Sala s : salas) {
            if (s.getVeterinario() == v) {
                System.out.println("Esse veterinario ja e responsavel pela sala " + s.getNumero());
                return;
            }
        }

        for (Sala s : salas) {
            System.out.println(s);
        }
        System.out.print("Numero da sala: ");
        Sala s = buscarSala(Integer.parseInt(sc.nextLine()));
        if (s == null) {
            System.out.println("Sala nao encontrada");
            return;
        }
        s.setVeterinario(v);
        System.out.println("Veterinario associado a sala!");
    }

    public static void atribuirAtendimento() {
        System.out.print("Codigo do atendimento: ");
        Atendimento a = buscarAtendimento(Integer.parseInt(sc.nextLine()));
        if (a == null) {
            System.out.println("Atendimento nao encontrado");
            return;
        }
        if (!a.getStatus().equals("agendado")) {
            System.out.println("Esse atendimento ja foi atribuido a uma sala");
            return;
        }
        System.out.print("Numero da sala: ");
        Sala s = buscarSala(Integer.parseInt(sc.nextLine()));
        if (s == null) {
            System.out.println("Sala nao encontrada");
            return;
        }
        if (s.adicionarAtendimento(a)) {
            a.iniciar(s);
            System.out.println("Atendimento atribuido a sala!");
        } else {
            System.out.println("Nao foi possivel atribuir (sala cheia ou procedimento diferente)");
        }
    }

    public static void exibirAtendimentosSala() {
        System.out.print("Numero da sala: ");
        Sala s = buscarSala(Integer.parseInt(sc.nextLine()));
        if (s == null) {
            System.out.println("Sala nao encontrada");
            return;
        }
        for (Atendimento a : s.getAtendimentos()) {
            System.out.println(a);
        }
        System.out.println("Total de atendimentos: " + s.getAtendimentos().size());
    }

    public static void totalFinalizados() {
        for (Sala s : salas) {
            int total = 0;
            for (Atendimento a : atendimentos) {
                if (a.getStatus().equals("finalizado") && a.getSala() == s) {
                    total++;
                }
            }
            System.out.println("Sala " + s.getNumero() + ": " + total + " atendimento(s) finalizado(s)");
        }
    }

    public static void buscarPorStatus() {
        System.out.print("Status (agendado, em andamento, finalizado): ");
        String status = sc.nextLine();
        int total = 0;
        for (Atendimento a : atendimentos) {
            if (a.getStatus().equals(status)) {
                System.out.println(a);
                total++;
            }
        }
        if (total == 0) {
            System.out.println("Nenhum atendimento encontrado");
        }
    }

    public static void exibirDetalhes() {
        System.out.print("Codigo do atendimento: ");
        Atendimento a = buscarAtendimento(Integer.parseInt(sc.nextLine()));
        if (a == null) {
            System.out.println("Atendimento nao encontrado");
            return;
        }
        System.out.println(a);
    }

    public static void finalizarAtendimento() {
        System.out.print("Codigo do atendimento: ");
        Atendimento a = buscarAtendimento(Integer.parseInt(sc.nextLine()));
        if (a == null) {
            System.out.println("Atendimento nao encontrado");
            return;
        }
        if (!a.getStatus().equals("em andamento")) {
            System.out.println("So e possivel finalizar atendimentos em andamento");
            return;
        }
        a.getSala().removerAtendimento(a);
        a.finalizar();
        System.out.println("Atendimento finalizado!");
    }
}
