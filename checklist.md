### 🔴 Melhorias e Correções Necessárias

- [ ] **Integração do CDB com a conta** — adquirir o ativo ainda não debita o saldo nem registra a posição de investimento
- [ ] **Taxas pré-definidas dos CDBs** — o menu ainda solicita a taxa prefixada ao usuário; cadastrar produtos com taxas próprias
- [ ] **Cálculo de rentabilidade** — implementar cálculo para CDB prefixado, pós-fixado e híbrido
- [ ] **Dados do CDB** — adicionar prazo, vencimento, liquidez, FGC e valor investido
- [ ] **Transferências** — adicionar a operação ao menu e implementar validação de origem, destino e saldo
- [ ] **Extrato** — registrar transações e disponibilizar consulta ao usuário
- [ ] **Persistência** — substituir ou complementar o armazenamento em memória com persistência de contas e extratos
- [ ] **Reflection desnecessária no `ContaRepository`** — método `obterNumero()` usa `Field.setAccessible()` quando poderia usar o getter `getNumero()` já existente
- [ ] **Testes unitários** — criar testes para contas, transações, CDB e validações (JUnit 5 já está configurado como dependência)
- [ ] **Getters faltando em `Conta`** — não há getters para `titular` e `cpf`
- [ ] **Formatação da saída** — usar `FormatadorMoeda` no menu para exibir valores em reais
