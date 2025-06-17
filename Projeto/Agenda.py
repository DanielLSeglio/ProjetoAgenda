# Lista que armazena os contatos, cada contato é uma lista [nome, celular, email]
agenda = []

def menu():
    """
    Exibe o menu principal com opções disponíveis
    e solicita uma escolha válida do usuário.
    Retorna um inteiro entre 1 e 6.
    """
    print("""
    Escolha uma opção válida:
    1 - Novo Contato.
    2 - Editar Contato.
    3 - Pesquisar Contato.
    4 - Listar Contato.
    5 - Apagar Contato.
    6 - Sair.
    """)
    return validar("Escolha sua opção: ", 1, 6)

def validar(pergunta, inicio, fim):
    """
    Valida a entrada do usuário para garantir que seja um número inteiro dentro do intervalo especificado.
    Retorna o valor validado ou 0 se fora do intervalo.
    """
    while True:
        try:
            valor = int(input(pergunta))
            if inicio <= valor <= fim:
                return valor
            else:
                print(f"Por favor, digite um número entre {inicio} e {fim}.")
        except ValueError:
            print("Entrada inválida. Digite um número inteiro válido.")

def novo():
    """
    Solicita os dados de um novo contato e adiciona à agenda.
    """
    global agenda
    nome = p_nome()
    celular = input("Celular...: ")
    email = input("E-mail...: ")
    agenda.append([nome, celular, email])
    print("\n---------------------------")
    print("Contato salvo com sucesso!")
    print(f"Nome: {nome} | Celular: {celular} | E-mail: {email}")
    print("---------------------------\n")

def p_nome():
    """
    Solicita o nome do contato.
    """
    return input("Nome...: ")

def pesquisa(nome):
    """
    Pesquisa na agenda um contato pelo nome, ignorando maiúsculas/minúsculas.
    Retorna o índice do contato ou None se não encontrado.
    """
    name = nome.lower()
    for i, contato in enumerate(agenda):
        if contato[0].lower() == name:
            return i
    return None

def editar():
    """
    Permite editar um contato existente, preservando o nome original.
    """
    pos = pesquisa(p_nome())
    if pos is not None:
        nome = agenda[pos][0]
        print(f"Nome...: {nome}")
        celular = input("Celular...: ")
        email = input("E-mail...: ")
        agenda[pos] = [nome, celular, email]
        print("\n-----------------------------")
        print("Contato editado com sucesso!")
        print(f"Nome: {nome} | Celular: {celular} | E-mail: {email}")
        print("-----------------------------\n")
    else:
        print("Contato não encontrado!\n")

def listar_dados(nome, celular, email):
    """
    Exibe os dados de um contato formatados.
    """
    print(f"Nome: {nome}\nCelular: {celular}\nE-mail: {email}\n")

def pesquisar():
    """
    Solicita o nome do contato e exibe seus dados se encontrado.
    """
    pos = pesquisa(p_nome())
    if pos is not None:
        nome, celular, email = agenda[pos]
        listar_dados(nome, celular, email)
    else:
        print("Contato não encontrado.\n")

def apagar():
    """
    Solicita o nome do contato e apaga-o da agenda após confirmação.
    """
    nome = p_nome()
    pos = pesquisa(nome)
    if pos is not None:
        nome_contato, celular, email = agenda[pos]
        print("Contato encontrado:")
        listar_dados(nome_contato, celular, email)
        confirm = input("Confirma a exclusão deste contato? [S/N]: ")
        if confirm.strip().lower() == 's':
            del agenda[pos]
            print("\n-----------------------------")
            print("Contato apagado com sucesso!")
            print("-----------------------------\n")
        else:
            print("Exclusão cancelada.\n")
    else:
        print("Contato não encontrado!\n")

def listar():
    """
    Exibe todos os contatos armazenados na agenda.
    """
    if not agenda:
        print("\nA agenda está vazia.\n")
        return

    print("\nCONTATOS DA AGENDA ######################\n")
    for contato in agenda:
        listar_dados(contato[0], contato[1], contato[2])
    print("FIM DA AGENDA ############################\n")

# Loop principal do programa
while True:
    opcao = menu()

    if opcao == 6:
        print("Encerrando o programa... Até logo!")
        break
    elif opcao == 1:
        novo()
    elif opcao == 2:
        editar()
    elif opcao == 3:
        pesquisar()
    elif opcao == 4:
        listar()
    elif opcao == 5:
        apagar()
    else:
        print("Opção inválida. Por favor, tente novamente.\n")
