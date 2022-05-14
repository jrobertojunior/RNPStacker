import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.HashMap;
import util.Token;
import util.TokenType;

class RPNStacker {

  public static void main(String[] args) {
    String fileName = "./input.txt";
    String fileContents = "";
    try {
      fileContents = new String(Files.readAllBytes(Paths.get(fileName)));

      // split file contents into individual lines
      String[] lines = fileContents.split("\n");
      Token[] tokens = new Token[lines.length];
      // create hashmap of variables
      HashMap<String, Double> variables = new HashMap<String, Double>();

      // scan tokens
      for (int i = 0; i < lines.length; i++) {
        String line = lines[i];

        if (line.matches("[0-9]+")) {
          // parse token as a float
          Double num = Double.parseDouble(line);
          // add token to stack
          tokens[i] = new Token(TokenType.NUM, num.toString());
        } else if (line.matches("[+-/*]")) {
          // operators
          switch (line) {
            case "+":
              tokens[i] = new Token(TokenType.PLUS, "+");
              break;
            case "-":
              tokens[i] = new Token(TokenType.MINUS, "-");
              break;
            case "*":
              tokens[i] = new Token(TokenType.STAR, "*");
              break;
            case "/":
              tokens[i] = new Token(TokenType.SLASH, "/");
              break;
            default:
              System.out.println("Error: Unexpected character: " + line);
              System.exit(1);
          }
        } else if (line.matches("[a-zA-Z]+:[0-9]+")) {
          // variable definition
          String[] parts = line.split(":");
          String varName = parts[0];
          Double varValue = Double.parseDouble(parts[1]);

          // add variable to hashmap
          variables.put(varName, varValue);
          // add token to stack
          tokens[i] = new Token(TokenType.DEF, varName);

        } else if (line.matches("[a-zA-Z]+")) {
          // variable reference
          // check if variable exists
          if (variables.containsKey(line)) {
            // add token to stack
            tokens[i] = new Token(TokenType.VAR, line);
          } else {
            System.out.println("Error: Variable not defined: " + line);
            System.exit(1);
          }
        } else {
          System.out.println("Error: Unexpected character: " + line);
          System.exit(1);
        }
      }

      // print tokens
      for (Token token : tokens) {
        System.out.println(token);
      }

      // create a stack
      Stack<Double> stack = new Stack<Double>();

      // print tokens
      for (Token token : tokens) {
        // if token is a number
        if (token.type == TokenType.NUM) {
          // parse token as a float
          Double num = Double.parseDouble(token.lexeme);
          // add token to stack
          stack.push(num);
        } else if (token.type == TokenType.PLUS || token.type == TokenType.MINUS || token.type == TokenType.STAR
            || token.type == TokenType.SLASH) {
          // token is an operator
          double num1 = stack.pop();
          double num2 = stack.pop();

          // swtich token type operators
          switch (token.type) {
            case PLUS:
              stack.push(num2 + num1);
              break;
            case MINUS:
              stack.push(num2 - num1);
              break;
            case STAR:
              stack.push(num2 * num1);
              break;
            case SLASH:
              stack.push(num2 / num1);
              break;
            default:
              System.out.println("Invalid token: " + token);
              System.exit(1);
          }
        } else if (token.type == TokenType.VAR) {
          // token is a variable
          // add variable value to stack
          stack.push(variables.get(token.lexeme));
        }
      }

      // print last stack item
      System.out.println(stack.pop());

    } catch (IOException e) {
      System.out.println("Error reading file");
      System.exit(1);
    }
  }
}
