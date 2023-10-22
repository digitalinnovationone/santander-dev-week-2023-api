# Santander Dev Week 2023 Java API

Desafio: "Explorando IA Generativa em um Pipeline de ETL com Python" parte do Santander Bootcamp 2023 - Ciência de Dados com Python

## Etapas Realizadas
Arquivo CSV criado usando googgle collab:

%%writefile banco.csv
age,job,marital,education,default,balance,housing,loan
34,unemployed,single,primary,no,1587,no,yes
34,services,married,secondary,no,3789,yes,yes
31,management,single,tertiary,no,1850,yes,no
32,management,married,tertiary,no,1476,yes,no
39,blue-collar,married,secondary,no,0,yes,no
35,management,single,tertiary,no,747,no,no
36,self-employed,married,tertiary,no,307,yes,no
38,technician,single,secondary,no,147,no,no
42,entrepreneur,married,tertiary,no,221,yes,no
45,services,married,primary,no,-88,,yes


#### Extração
Extrair as informações de idades do arquivo anterior:

import pandas as pd
df = pd.read_csv('banco.csv', na_values='na')
df.head(n=10)

Analisando as informações

df.dtypes
df.select_dtypes('object').describe().transpose()

#### Transform

Verificar se alguma coluna tem info faltante:
df.isna().any()

Remover a linha com informação incompleta:
df.dropna(inplace=True)

Revisar informações após ajuste:
df.head(n=10)

#### Load

Extrair as informações de idades do arquivo aque foi ajustado e salvar como novo arquivo:

idades = []
with open(file='idades.csv', mode='w', encoding='utf8') as fp:
  linha = 'idade' + '\n'
  fp.write(linha)
  for idade in idades:
    linha = str(idade) + '\n'
    fp.write(linha)

  

    



