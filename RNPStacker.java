import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import util.Token;
import util.TokenType;

class RNPStacker {

  public static void main(String[] args) {
    String fileName = "./input.txt";
    String fileContents = "";
    try {
      fileContents = new String(Files.readAllBytes(Paths.get(fileName)));

      // split file contents into individual lines
      String[] lines = fileContents.split("\n");
      Token[] tokens = new Token[lines.length];

      // scan tokens
      for (int i = 0; i < lines.length; i++) {
        String line = lines[i];

        if (line.matches("[0-9]+")) {
          // parse token as a float
          Double num = Double.parseDouble(line);
          // add token to stack
          tokens[i] = new Token(TokenType.NUM, num.toString());
        } else {
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
              System.out.println("Invalid token: " + line);
              System.exit(1);
          }
        }
      }

      // print tokens
      for (Token token : tokens) {
        System.out.println(token);
      }

      // create a stack
      Stack<Double> stack = new Stack<Double>();

      // print tokens
      for (String line : lines) {
        // if token is a number
        if (line.matches("[0-9]+")) {
          // parse token as a float
          Double num = Double.parseDouble(line);
          // add token to stack
          stack.push(num);
        } else {
          // token is an operator
          double num1 = stack.pop();
          double num2 = stack.pop();

          switch (line) {
            case "+":
              stack.push(num2 + num1);
              break;
            case "-":
              stack.push(num2 - num1);
              break;
            case "*":
              stack.push(num2 * num1);
              break;
            case "/":
              stack.push(num2 / num1);
              break;
            default:
              System.out.println("Invalid operator");
          }
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
