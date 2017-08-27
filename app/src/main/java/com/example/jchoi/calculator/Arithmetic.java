package com.example.jchoi.calculator;

import java.util.Scanner;
import java.util.Stack;

class Arithmetic {
    private int prioritize(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String infixToPostfix(String exp) {
        Stack<Character> stack = new Stack<>();
        String result = "";

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c))
                result += c;
            else if (c == '(') {
                result += ' ';
                stack.push(c);
            } else if (c == ')') {
                result += ' ';
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();

                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression";
                else
                    stack.pop();
            } else {
                result += ' ';
                while (!stack.isEmpty() && prioritize(c) <= prioritize(stack.peek()))
                    result += stack.pop();
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result += ' ';
            result += stack.pop();
        }

        return result;
    }

    String calculate(String exp) {
        Stack<Integer> stack = new Stack<>();
        String postfix = infixToPostfix(exp);
        String buf;

        Scanner scanner = new Scanner(postfix);

        while (scanner.hasNext()) {
            buf = scanner.next();

            if (isNumber(buf))
                stack.push(Integer.parseInt(buf));
            else {
                int result = 0;
                int num1 = stack.pop();
                int num2 = stack.pop();

                switch (buf.charAt(0)) {
                    case '+':
                        result = num2 + num1;
                        break;
                    case '-':
                        result = num2 - num1;
                        break;
                    case '*':
                        result = num2 * num1;
                        break;
                    case '/':
                        result = num2 / num1;
                        break;
                    default:
                        System.out.println("Error");
                }
                stack.push(result);
            }
        }
        return stack.pop().toString();
    }
}
/*
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);

            if (Character.isLetterOrDigit(c))
                stack.push(Character.getNumericValue(c));
            else {
                int result = 0;
                int num1 = stack.pop();
                int num2 = stack.pop();

                switch (c) {
                    case '+':
                        result = num2 + num1;
                        break;
                    case '-':
                        result = num2 - num1;
                        break;
                    case '*':
                        result = num2 * num1;
                        break;
                    case '/':
                        result = num2 / num1;
                        break;
                    default:
                        System.out.println("Error");
                }
                stack.push(result);
            }
        }
        return stack.pop().toString();
    }
}

*/