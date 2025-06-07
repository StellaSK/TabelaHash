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

Os arquivos não puderam ser upados via gitHub, devido ao tamanho superior a 25mb, mas estão presentes em: https://drive.google.com/drive/folders/12f7tZ-UZSYXTaUrNiTXrpHSCODNxyfWj?hl

Cada combinação de conjunto de dados × função hash × tamanho de tabela resulta em uma rodada (totalizando 27 rodadas).

📊 Análise dos Resultados

⏱️ Tempo Médio de Busca (ns) e Comparações
![Busca-Dobramento-TempoMedio(NS)](https://github.com/user-attachments/assets/aa6df6bb-abcd-4faf-84dd-47156c994deb) ![Busca-Dobramento-ComparaçõesMedias](https://github.com/user-attachments/assets/0938ce26-035e-44a8-81fc-19a2dd74f996)
![Busca-Multiplicacao-TempoMedio(NS)](https://github.com/user-attachments/assets/e13b974e-a6cc-43c7-9976-97ed439ee67e) ![Busca-Multiplicacao-ComparaçõesMedias](https://github.com/user-attachments/assets/5030e408-77e8-431d-ab2b-1592e5ed5a94)
![Busca-RestoDivisao-TempoMedio(NS)](https://github.com/user-attachments/assets/cdf9f865-d66d-4301-9a0c-4c5b4be174a1) ![Busca-RestoDivisao-ComparaçõesMedias](https://github.com/user-attachments/assets/00ef03c7-aa2a-4add-9b62-2110a4f602aa)

💥 Colisões nas Inserções e tempo medio
![Dobramentos Colicoes](https://github.com/user-attachments/assets/82a2d7b6-9a50-4bd3-b91c-73c9426d2120) ![Dobramento-Tempo de Inserção(MS)](https://github.com/user-attachments/assets/a1dcca53-9f2c-4784-bc32-a1dc4fac04f7)
![Multiplicacao-Colicoes](https://github.com/user-attachments/assets/07b5f4a2-a026-4af5-bde3-d75088cc76e9) ![Multiplicacao-Tempo de Inserção(MS)](https://github.com/user-attachments/assets/0df1cb96-1be8-486a-8468-a207b5bde5b4)
![RestoDivisao-Colições](https://github.com/user-attachments/assets/7652644f-bf1e-4b93-aa0f-3cef42791da4) ![RestoDivisao-Tempo de Inserção(MS)](https://github.com/user-attachments/assets/6e3b3ceb-08c8-4625-8a63-eadde85ed94b)


🧾 Conclusão

As funções de multiplicação e RestoDivisão são as que apresentão os melhores resultados e muito semelhantes em Comparações feitas na busca e no tempo medio da mesma, numero de colições no inset é praticamente o mesmo, diferenciando por 1 comparação a mais em multiplicação, sobrando assim  o tempo de inserção, aonde ambos tambem competem, mutiplicação fica melhor 3 vezes, há um empate, e 5 vitorias do resto, porem por multiplicação se sair melhor nas comparações de 20M de dados nas tabelas de 10mil e 100mil, ela é recomendada para estes casos. Por tanto RestoDivisao e Multiplicacao, podem ser empatadas como as melhores, oque vai pesar na escolha de qual usar no final, sera o proposito,a quantidade de dados e o tamanho das tabelas.

O dobramento apresenta o melhor tempo de insersão na Tabela de 100mil com 20M de dados, porem é o pior em relação as comparações de buscas e tempo medio das mesmas.

O tamanho da tabela impacta diretamente o número de colisões, que são o numero total de dados menos o numero da tabela, com variação de unidades.

As tabelas com 1.000.000 tiveram o menor tempo de busca e comparações feitas em ambos os testes.

O uso de uma lista encadeada personalizada ao invés de listas prontas pode ter sido o responsavel pelo tempo excessivo para rodar o programa por completo, o qual ficou mais de 12
horas rodando, e concerteza o numero elevado de 20M de dados teve impacto importante nisso.



