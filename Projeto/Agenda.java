import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        ArrayList<String[]> agenda = new ArrayList<>();  // Lista que armazena os contatos (nome, celular, email)
        int opcao;
        String nome;

        do {
            exibeMenu();  // Exibe o menu principal

            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());
            System.out.println();

            switch (opcao) {
                case 1:
                    // Adiciona um novo contato à agenda
                    novo(agenda, entrada);
                    break;

                case 2:
                    // Edita um contato existente, realizando busca por nome
                    System.out.println("_____ Editando (Pesquise o Contato):");
                    System.out.print("Digite o nome......: ");
                    nome = entrada.nextLine();
                    int linha = pesquisarContato(agenda, nome);
                    if (linha == -1) {
                        System.out.println("Contato não cadastrado!");
                    } else {
                        exibirContato(agenda, linha);
                        novo(agenda, entrada, linha); // Sobrescreve o contato encontrado
                    }
                    break;

                case 3:
                    // Pesquisa um contato pelo nome e exibe seus dados
                    System.out.println("_______ PESQUISAR CONTATO: ");
                    System.out.print("Digite o nome......: ");
                    nome = entrada.nextLine();
                    linha = pesquisarContato(agenda, nome);
                    if (linha == -1) {
                        System.out.println("Contato não cadastrado!");
                    } else {
                        exibirContato(agenda, linha);
                    }
                    break;

                case 4:
                    // Lista todos os contatos presentes na agenda
                    listarAgenda(agenda);
                    break;

                case 5:
                    // Apaga um contato pesquisado pelo nome, após confirmação
                    System.out.println("_______ EXCLUINDO (PESQUISE O CONTATO): ");
                    System.out.print("Digite o nome......: ");
                    nome = entrada.nextLine();
                    apagarContato(agenda, nome, entrada);
                    break;

                case 6:
                    // Finaliza a execução do programa
                    System.out.println("OBRIGADO POR UTILIZAR A NOSSA AGENDA :)");
                    break;

                default:
                    // Trata opções inválidas no menu
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();

        } while (opcao != 6);

        entrada.close();
    }

    /**
     * Remove um contato da agenda após confirmação do usuário.
     * @param agenda Lista de contatos.
     * @param nome Nome do contato a ser removido.
     * @param entrada Scanner para entrada de dados.
     */
    public static void apagarContato(ArrayList<String[]> agenda, String nome, Scanner entrada) {
        int linha = pesquisarContato(agenda, nome);
        if (linha != -1) {
            exibirContato(agenda, linha);
            System.out.print("Confirma a exclusão do contato? [S]im ou [N]ão: ");
            String opcao = entrada.nextLine();
            if (opcao.equalsIgnoreCase("s")) {
                agenda.remove(linha);
                System.out.println("Contato excluído com sucesso!");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } else {
            System.out.println("Contato não encontrado!");
        }
    }

    /**
     * Lista todos os contatos armazenados na agenda.
     * @param agenda Lista de contatos.
     */
    public static void listarAgenda(ArrayList<String[]> agenda) {
        System.out.println("_______ CONTATOS DA AGENDA: ");
        if (agenda.isEmpty()) {
            System.out.println("A agenda está vazia.");
        } else {
            for (int i = 0; i < agenda.size(); i++) {
                exibirContato(agenda, i);
                System.out.println("____________________________________");
            }
        }
        System.out.println("_______ FIM DA AGENDA.");
    }

    /**
     * Exibe os dados de um contato específico.
     * @param agenda Lista de contatos.
     * @param indice Índice do contato a ser exibido.
     */
    public static void exibirContato(ArrayList<String[]> agenda, int indice) {
        String[] contato = agenda.get(indice);
        System.out.println("Nome......: " + contato[0]);
        System.out.println("Celular...: " + contato[1]);
        System.out.println("E-mail....: " + contato[2]);
    }

    /**
     * Pesquisa um contato pelo nome na agenda.
     * @param agenda Lista de contatos.
     * @param nome Nome a ser pesquisado.
     * @return índice do contato encontrado ou -1 se não existir.
     */
    public static int pesquisarContato(ArrayList<String[]> agenda, String nome) {
        for (int i = 0; i < agenda.size(); i++) {
            if (agenda.get(i)[0].equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Exibe o menu principal com as opções disponíveis.
     */
    public static void exibeMenu() {
        System.out.println("******* MENU *******");
        System.out.println("1 - Adicionar um novo contato");
        System.out.println("2 - Editar contato");
        System.out.println("3 - Pesquisar contato");
        System.out.println("4 - Listar contatos");
        System.out.println("5 - Apagar um contato");
        System.out.println("6 - Sair");
        System.out.println("********************");
    }

    /**
     * Adiciona um novo contato à agenda.
     * @param agenda Lista de contatos.
     * @param entrada Scanner para entrada de dados.
     */
    public static void novo(ArrayList<String[]> agenda, Scanner entrada) {
        System.out.println("______ PREENCHA O CONTATO: ");
        System.out.print("Nome......: ");
        String nome = entrada.nextLine();
        System.out.print("Celular...: ");
        String celular = entrada.nextLine();
        System.out.print("E-mail....: ");
        String email = entrada.nextLine();

        String[] contato = { nome, celular, email };
        agenda.add(contato);
        System.out.println("Contato adicionado com sucesso!");
        System.out.println("Contato: " + nome + " | " + celular + " | " + email);
    }

    /**
     * Atualiza os dados de um contato existente.
     * @param agenda Lista de contatos.
     * @param entrada Scanner para entrada de dados.
     * @param indice Índice do contato a ser atualizado.
     */
    public static void novo(ArrayList<String[]> agenda, Scanner entrada, int indice) {
        System.out.println("______ EDITANDO O CONTATO: ");
        System.out.print("Nome......: ");
        String nome = entrada.nextLine();
        System.out.print("Celular...: ");
        String celular = entrada.nextLine();
        System.out.print("E-mail....: ");
        String email = entrada.nextLine();

        String[] contato = { nome, celular, email };
        agenda.set(indice, contato);
        System.out.println("Contato editado com sucesso!");
        System.out.println("Contato atualizado: " + nome + " | " + celular + " | " + email);
    }
}
