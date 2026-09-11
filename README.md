# 🏦 Simulador Financeiro Bancário
Simulador_Financeir
Simulador bancário desenvolvido em **Java 21** com **Gradle**, focado em boas práticas de orientação a objetos, arquitetura em camadas e princípios SOLID. Projeto acadêmico para portfólio.

---

## 📋 Sobre o Projeto

O **Simulador Financeiro** é uma aplicação de linha de comando (CLI) que simula operações bancárias do mundo real, incluindo criação de contas, depósitos, saques, transferências e investimentos em ativos financeiros.

O objetivo é demonstrar domínio em:

- **Programação Orientada a Objetos** — herança, polimorfismo, encapsulamento e abstração
- **Arquitetura em Camadas** — separação clara entre Model, Repository, Service e CLI
- **Injeção de Dependência** — componentes desacoplados via construtor
- **Boas Práticas** — uso de `BigDecimal` para valores monetários, `Optional` para evitar `NullPointerException`, validação de dados (CPF)

---

## 🏗️ Arquitetura

O projeto segue uma **arquitetura em camadas** com responsabilidades bem definidas:

```
┌─────────────────────────────────────────┐
│            CLI (Interface)              │
│  MenuPrincipal · EntradaSaidaConsole    │
│  ValidarCPF                             │
├─────────────────────────────────────────┤
│           SERVICE (Negócio)             │
│  ContaService · TransferenciaService    │
│  MercadoFinanceiroService               │
├─────────────────────────────────────────┤
│         REPOSITORY (Dados)              │
│  ContaRepository                        │
│  ArquivoExtratoRepository               │
├─────────────────────────────────────────┤
│            MODEL (Domínio)              │
│  Conta · ContaCorrente                  │
│  ContaInvestimento · Cliente            │
│  Transacao · AtivoFinanceiro            │
└─────────────────────────────────────────┘
```

---

## 🛠️ Tecnologias

| Tecnologia | Versão |
|------------|--------|
| Java       | 21     |
| Gradle     | 9.2.0  |
| JUnit      | 5.12.1 |
| Guava      | 33.4.6 |

---

## 🚀 Como Executar

### Pré-requisitos

- **Java 21** (JDK) instalado — ou deixe o Gradle baixar automaticamente via Toolchain

### Passos

```bash
# 1. Clone o repositório
git clone https://github.com/nunesxfx/Simulador_Financeiro.git
cd Simulador_Financeiro

# 2. Execute o projeto (Linux/macOS)
./gradlew run

# 2. Execute o projeto (Windows)
gradlew.bat run
```

> **Nota:** Não é necessário ter o Gradle instalado. O projeto inclui o **Gradle Wrapper** que baixa a versão correta automaticamente.

---

## 📁 Estrutura do Projeto

```
Simulador_Financeiro/
├── app/
│   ├── build.gradle
│   └── src/main/java/banco/
│       ├── Main.java                          # Ponto de entrada
│       ├── cli/
│       │   ├── EntradaSaidaConsole.java        # Utilitário de I/O do console
│       │   ├── MenuPrincipal.java             # Menu interativo principal
│       │   └── ValidarCPF.java                # Validação de CPF
│       ├── exception/
│       │   ├── AtivovNaoEncontradoException.java
│       │   └── SaldoInsuficienteException.java
│       ├── model/
│       │   ├── Conta.java                     # Classe abstrata base
│       │   ├── ContaCorrente.java             # Conta com cheque especial
│       │   ├── ContaInvestimento.java         # Conta de investimentos
│       │   ├── Cliente.java                   # Modelo de cliente
│       │   ├── Transacao.java                 # Registro de transações
│       │   └── ativos/
│       │       ├── AtivoFinanceiro.java       # Base para ativos
│       │       ├── CertificadoRendaFIxa.java  # CDB/LCI/LCA
│       │       └── FundoImobiliario.java      # FIIs
│       ├── repository/
│       │   ├── ContaRepository.java           # Armazenamento em memória
│       │   └── ArquivoExtratoRepository.java  # Persistência de extratos
│       ├── service/
│       │   ├── ContaService.java              # Lógica de contas
│       │   ├── TransferenciaService.java      # Lógica de transferências
│       │   └── MercadoFinanceiroService.java  # Simulação de mercado
│       └── util/
│           ├── FormatadorMoeda.java           # Formatação monetária
│           └── GeradorIdentificador.java      # Geração de IDs
├── gradle/
│   ├── libs.versions.toml                     # Catálogo de versões
│   └── wrapper/                               # Gradle Wrapper
├── settings.gradle
├── gradle.properties
├── gradlew                                    # Script Unix
└── gradlew.bat                                # Script Windows
```

---

## ✅ Checklist de Funcionalidades

### 🟢 Implementado e Funcional

- [x] **Arquitetura em camadas** — separação clara entre CLI → Service → Repository → Model
- [x] **Injeção de dependência** — componentes conectados via construtor no `Main.java`
- [x] **Classe abstrata `Conta`** — com atributos `numero`, `titular`, `cpf`, `saldo` usando `BigDecimal`
- [x] **Depósito** — método `depositar()` com validação de valor positivo
- [x] **Saque básico** — método `sacar()` com verificação de saldo
- [x] **Conta Corrente (`ContaCorrente`)** — herda de `Conta` com cheque especial
- [x] **Saque com cheque especial** — `@Override` do método `sacar()` considerando o limite extra
- [x] **Repositório em memória (`ContaRepository`)** — `HashMap` como banco de dados com `salvar()`, `buscarPorNumero()` e `listarTodas()`
- [x] **Serviço de criação de conta (`ContaService`)** — gera número único com `UUID` e persiste via repository
- [x] **Validação de CPF (`ValidarCPF`)** — algoritmo completo com validação dos dois dígitos verificadores
- [x] **Formatação de CPF** — método `mostrarCPF()` para exibição formatada (XXX.XXX.XXX-XX)
- [x] **Entrada/Saída do console (`EntradaSaidaConsole`)** — leitura de texto e valores financeiros com tratamento de `InputMismatchException`
- [x] **Menu principal (`MenuPrincipal`)** — fluxo de criação de conta com validação de CPF integrada
- [x] **Gradle Wrapper** — projeto executável sem instalação prévia do Gradle

### 🟡 Estrutura Criada (Pendente de Implementação)

- [ ] **Modelo `Cliente`** — classe vazia, falta atributos (nome, CPF, lista de contas)
- [ ] **Modelo `ContaInvestimento`** — classe vazia, falta herdar de `Conta` e adicionar lógica de investimentos
- [ ] **Modelo `Transacao`** — classe vazia, falta atributos (tipo, valor, data, contas envolvidas)
- [ ] **Modelo `AtivoFinanceiro`** — classe vazia, falta definir base para ativos (nome, valor, rentabilidade)
- [ ] **Modelo `CertificadoRendaFIxa`** — classe vazia, falta implementar CDB/LCI/LCA com taxa e vencimento
- [ ] **Modelo `FundoImobiliario`** — classe vazia, falta implementar FIIs com cotas e dividendos
- [ ] **Exceção `SaldoInsuficienteException`** — classe não estende `Exception` / `RuntimeException`
- [ ] **Exceção `AtivovNaoEncontradoException`** — classe não estende `Exception` / `RuntimeException` *(obs: possível typo no nome — "Ativov" → "Ativo")*
- [ ] **`TransferenciaService`** — classe vazia, falta lógica de transferência entre contas
- [ ] **`MercadoFinanceiroService`** — classe vazia, falta simulação de mercado e rendimentos
- [ ] **`ArquivoExtratoRepository`** — classe vazia, falta persistência de extratos em arquivo
- [ ] **`FormatadorMoeda`** — classe vazia, falta formatação de valores em R\$ (Real)
- [ ] **`GeradorIdentificador`** — classe vazia, falta geração padronizada de IDs de conta

### 🔴 Melhorias e Correções Necessárias

- [ ] **Menu incompleto** — opções "Realizar Transferência" e "Sair" não estão implementadas no `switch`
- [ ] **Loop infinito no menu** — a variável `rodando` nunca é setada como `false`, impossibilitando a saída
- [ ] **`mainClass` incorreta no `build.gradle`** — está `simulador_financeiro.App`, deveria ser `banco.Main`
- [ ] **Reflection desnecessária no `ContaRepository`** — método `obterNumero()` usa `Field.setAccessible()` quando poderia usar o getter `getNumero()` já existente
- [ ] **Testes unitários** — nenhum teste foi implementado ainda (JUnit 5 já está configurado como dependência)
- [ ] **Getters faltando em `Conta`** — não há getters para `titular` e `cpf`

---

## 📚 Conceitos Aplicados

| Conceito | Onde é aplicado |
|----------|----------------|
| **Herança** | `ContaCorrente extends Conta` |
| **Polimorfismo** | `@Override` do método `sacar()` na `ContaCorrente` |
| **Abstração** | Classe `Conta` é `abstract` |
| **Encapsulamento** | Campos `private`/`protected` com getters |
| **Injeção de Dependência** | `ContaService` recebe `ContaRepository` no construtor |
| **Optional** | `buscarPorNumero()` retorna `Optional<Conta>` |
| **BigDecimal** | Todos os valores monetários usam `BigDecimal` (precisão financeira) |
| **UUID** | Geração de números de conta únicos |

---

## 📄 Licença

Este projeto é de uso acadêmico e livre para fins de estudo e portfólio.

---

<p align="center">
  Desenvolvido com ☕ Java para fins acadêmicos
</p>

