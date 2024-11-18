import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner( System.in);
        System.out.println("Введите выражение");
        String inputStr = in.nextLine();
        System.out.println("Результат: "+calc(inputStr));
    }

    public static String calc(String input) throws IOException {
        if(input.isEmpty())
            throw new IOException("Выражение не может быть пустым");

        char operation = '!';
        char[] chars;
        int operationCount = 0;
        boolean firstDigitReady = false;
        StringBuilder firstDigit = new StringBuilder();
        StringBuilder secondDigit = new StringBuilder();

        input = input.replaceAll(" ", "");
        chars = input.toCharArray();
        for(char ch:chars){
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                operationCount++;
                if(operationCount > 1)
                    throw new IOException("Допустима только одна операция");

                operation = ch;
                firstDigitReady = true;
            } else if (Character.isDigit(ch)) {
                if(!firstDigitReady)
                    firstDigit.append(ch);
                else
                    secondDigit.append(ch);
            } else {
                throw new IOException("Выражение содержит недопустимый символ");
            }
        }

        if(firstDigit.toString().isEmpty())
            throw new IOException("Не передано первое число");
        if(secondDigit.toString().isEmpty())
            throw new IOException("Не передано второе число");

        int a = Integer.parseInt(firstDigit.toString());
        int b = Integer.parseInt(secondDigit.toString());

        if(a < 1 || a > 10 || b < 1 || b > 10)
            throw new IOException("Диапазон допустимых значений: 1 - 10");

        return switch (operation) {
            case '+' -> String.valueOf(a + b);
            case '-' -> String.valueOf(a - b);
            case '*' -> String.valueOf(a * b);
            case '/' -> String.valueOf((int) a / b);
            default -> throw new IOException("Недопустимая операция");
        };
    }
}