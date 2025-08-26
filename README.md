# Sistema de Gerenciamento de Restaurante

## Descrição

Sistema completo para gerenciamento de restaurante (CLI). O sistema permite gerenciar cardápio, clientes, pedidos e gerar relatórios.

## Funcionalidades

### Gerenciamento de Cardápio
- Cadastrar novos itens (nome e preço)
- Listar todos os itens do cardápio com códigos e preços

### Gerenciamento de Clientes
- Cadastrar novos clientes (nome e telefone)
- Listar todos os clientes cadastrados com códigos

### Gerenciamento de Pedidos
- Criar novos pedidos para clientes
- Adicionar múltiplos itens com quantidades aos pedidos
- Atualizar status dos pedidos (ciclo automático)
- Consultar pedidos por status específico
- Listar todos os pedidos

### Relatórios
- Relatório Simplificado: total de pedidos e valor arrecadado
- Relatório Detalhado: listagem completa de todos os pedidos

## Ciclo de Status dos Pedidos

O sistema implementa um ciclo fixo de status para os pedidos:

1. **ACEITO** → Pedido foi aceito pelo restaurante
2. **PREPARANDO** → Pedido está sendo preparado na cozinha
3. **FEITO** → Pedido foi finalizado e está pronto
4. **AGUARDANDO ENTREGADOR** → Esperando entregador disponível
5. **SAIU PARA ENTREGA** → Pedido saiu para entrega
6. **ENTREGUE** → Pedido foi entregue ao cliente

## Arquitetura do Sistema

### Classes Principais

#### Entidades de Negócio
- **Cliente**: Representa um cliente com código, nome e telefone
- **ItemCardapio**: Representa um item do cardápio com código, nome e preço
- **Pedido**: Representa um pedido com número, cliente, itens e status
- **PedidoItem**: Classe intermediária que relaciona itens do cardápio com quantidades no pedido
- **StatusPedido**: Enum que define os possíveis status de um pedido

#### Classes de Controle
- **CentralDeDados**: Armazena todas as coleções de dados em memória
- **SistemaRestaurante**: Fachada que orquestra todas as operações do sistema
- **MenuCLI**: Interface de linha de comando com navegação completa
- **Main**: Classe principal que inicializa o sistema

### Relacionamentos

- Um **Cliente** pode ter vários **Pedidos** (1:N)
- Um **Pedido** contém vários **PedidoItens** (1:N)
- Um **ItemCardapio** pode estar em vários **PedidoItens** (1:N)

## Como Executar

### Pré-requisitos
- Java JDK 8 ou superior
- Terminal/Prompt de comando

### Compilação
```bash
javac src/*.java
```

### Execução
```bash
java -cp src Main
```

## Estrutura de Navegação

```
SISTEMA DE GERENCIAMENTO DE RESTAURANTE
├── Gerenciar Cardápio
│   ├── Cadastrar novo item
│   └── Listar cardápio
├── Gerenciar Clientes
│   ├── Cadastrar novo cliente
│   └── Listar clientes
├── Gerenciar Pedidos
│   ├── Criar novo pedido
│   ├── Atualizar status do pedido
│   ├── Consultar pedidos por status
│   └── Listar todos os pedidos
├── Relatórios
│   ├── Relatório Simplificado
│   └── Relatório Detalhado
└── Sair
```

## Regras de Negócio

1. **Códigos Automáticos**: Sistema gera automaticamente códigos únicos para clientes, itens e números sequenciais para pedidos
2. **Validações**: Campos obrigatórios são validados (nomes não vazios, preços positivos, etc.)
3. **Quantidades**: Ao adicionar o mesmo item múltiplas vezes ao pedido, as quantidades são somadas
4. **Status**: Pedidos seguem um ciclo fixo de status que não pode ser revertido
5. **Notificações**: Sistema está preparado para notificar eventos

## Dados Iniciais

O sistema vem com alguns dados pré-cadastrados:

### Cardápio Inicial
- Pizza Margherita - R$ 35,90
- Hambúrguer Clássico - R$ 28,50
- Batata Frita - R$ 12,00
- Refrigerante 350ml - R$ 5,50
- Salada Caesar - R$ 22,90

### Clientes Iniciais
- João Silva - (11) 99999-1234
- Maria Santos - (11) 98888-5678

## Autores

Sistema desenvolvido para a Desenvolvimento Web OOP - OAT 1.1
Versão: 1.0
Autores: Fernando Gustavo B Santos, Gabriel de Jesus, Bryan Ferraz, João Paulo, Felipe Souza
