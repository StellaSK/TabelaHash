# Análise de Tabelas Hash com Diferentes Funções de Dispersão

## 📌 Objetivo

Este projeto tem como objetivo comparar o desempenho de três funções de dispersão (hash) em diferentes tamanhos de tabela e conjuntos de dados. O foco é avaliar **tempo de inserção**, **número de colisões**, **tempo médio de busca** e **quantidade média de comparações**.

---

## ⚙️ Implementação

A estrutura principal utilizada foi uma **Tabela Hash com encadeamento separado** para tratamento de colisões, utilizando uma **lista encadeada implementada manualmente**.

### Estrutura de Dados:
- `Registro`: representa os dados com chaves inteiras de 9 dígitos.
- `TabelaHash`: tabela que armazena os registros com tratamento de colisão por encadeamento.
- `ListaEncadeadaRegistro`: lista encadeada simples para armazenar registros nas posições com colisão.
- `FuncaoHash`: classe centralizada com três métodos estáticos para cálculo de hash.

---

## 🧠 Funções Hash Testadas

1. **Resto da Divisão**
   - Fórmula: `hash = chave % tamanhoTabela`
   - Simples, rápida, mas depende da boa escolha do tamanho da tabela.

2. **Multiplicação**
   - Fórmula baseada em: `A = 0.6180339887` (constante irracional)
   - `hash = floor(tamanhoTabela * (chave * A % 1))`
   - Reduz padrões e distribui melhor para certos tipos de chave.

3. **Dobramento**
   - A chave é dividida em partes e somada, por exemplo:
     - `123456789` → `123 + 456 + 789 = 1368`
   - Boa distribuição, especialmente quando os dígitos da chave têm significados mistos.

---

## 📊 Tamanhos de Tabela Utilizados

- `1.000`
- `10.000`
- `100.000`

---

## 📁 Conjuntos de Dados

Os conjuntos foram gerados com chaves aleatórias de 9 dígitos:

- `1.000.000` registros
- `5.000.000` registros
- `20.000.000` registros

Os arquivos foram salvos em:
resources/dados_1M.txt
resources/dados_5M.txt
resources/dados_20M.txt
