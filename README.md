Desenvolva um aplicativo um aplicativo que receba o CPF, e consulte o nome e 
guarde as informações. Após, faça 3 questões e guarde no banco de dados.

1. Você sente dor? Sim ou Não
1.1. (Caso sim) – Qual intensidade (1 a 10)
2. Sente algum desconforto? Sim ou Não
2.1. (Caso sim) – Em qual parte do corpo (Cabeça, braços, tórax, barriga, pernas, 
costas)
3. Passou por alguma cirurgia?
3.1. – (Caso sim) – Quanto tempo? 1 a 2 meses, 3 a 6 meses, 6 a 12 meses, mais 
de 1 ano.
3.2. – Que tipo de cirurgia? – Estética ou Saúde
3.3. – Você sentiu alguma dor após a cirurgia? Sim ou Não

Após as perguntas, o sistema deve exibir uma mensagem de agradecimento 
pela colaboração.
Deve haver uma tela para que os dados respondidos possam ser visualizados, 
através de uma lista de pacientes, na qual deve ser selecionado o paciente, e as 
perguntas e respostas exibidas.


# Classes
## Patient 
|     Atributo    	|          Valor          	|
|:---------------:	|:-----------------------:	|
| name            	| 'José Pereira'          	|
| CPF             	| '033.314.628-46'        	|
| last_service    	| '23/04/2020 19:22'      	|
| medical_records 	| List\<MedicalRecord\>() 	|

## MedicalRecord 
|      Atributo       	|         Valor         |
|:-------------------:	|:--------------------:	|
| pain            	    | true                 	|
| pain_intensity        | 9                     |
| discomfort      	    | true                  |
| discomfort_body_part  | 6                     |
| surgery               | true                  |
| surgery_how_long      | 5                     |
| surgery_type          | 2                     |
| surgery_pain_after    | false                 |
| create_at             | '23/04/2020 19:22'    |

**O Android Studio não buildar o projeto se as variaveis SDK estiverem num caminho diferente. Tente criar um novo projeto vazio com o mesmo dominio *br.com.labtecs.sacbj* e copiar a pasta */src* e o arquivo *build.gradle***