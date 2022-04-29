# RPNStacker

Implementação da **Reverse Polish Notation** (RPN) com pilha para operações aritméticas na cadeira de Compiladores 2021.2.

## Uso

Escreva a entrada no `input.txt` depois:

```
$ javac RPNStacker.java
$ java RPNStacker
```

## Exemplo

input.txt:
```
10
10
*
20
-
120
/
30
+
```

output:
```
Token [type=NUM, lexeme=10.0]
Token [type=NUM, lexeme=10.0]
Token [type=STAR, lexeme=*]
Token [type=NUM, lexeme=20.0]
Token [type=MINUS, lexeme=-]
Token [type=NUM, lexeme=120.0]
Token [type=SLASH, lexeme=/]
Token [type=NUM, lexeme=30.0]
Token [type=PLUS, lexeme=+]
30.666666666666668
```
