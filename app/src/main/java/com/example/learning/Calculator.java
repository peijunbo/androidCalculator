package com.example.learning;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {
    private static final Map<Character, Integer> priority = new HashMap<Character, Integer>();

    static {
        priority.put('=', -1);
        priority.put('(', 0);
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put(')', 3);
    }


    private static float calTwoNum(float num1, float num2, Character opt) {
        float result = 0.0f;
        switch (opt) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                break;
        }
        return result;
    }

    private static void calAll(Stack<Character> symbolStack, Stack<Float> numStack, boolean isBracket) {
        Character opt = symbolStack.pop();
        Float num2 = numStack.pop();
        Float num1 = numStack.pop();
        numStack.push(calTwoNum(num1, num2, opt));
        Log.d("calAll", "" + num1 + opt + num2);

        if (isBracket) {
            if (symbolStack.peek() == '(') {
                symbolStack.pop();
            }
            else {
                calAll(symbolStack, numStack, true);
            }
        }
        else {
            if (!symbolStack.empty()) {
                calAll(symbolStack, numStack, false);
            }
        }
    }

    public static float calculate(String expression) {
        float ans = 0.0f;
        Stack<Float> numStack = new Stack<Float>();
        Stack<Character> symbolStack = new Stack<Character>();
        StringBuilder numBuilder = new StringBuilder(20);
        for (int i = 0; i < expression.length(); i++) {
            Character c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                numBuilder.append(c);
            }
            else {
                if (numBuilder.length() > 0) {
                    //不为空，求数字的值并入栈
                    numStack.push(Float.parseFloat(numBuilder.toString()));
                    numBuilder.delete(0, numBuilder.length());
                }

                if (symbolStack.empty()) {
                    symbolStack.push(c);
                }
                else {
                    if (c == '(') {
                        symbolStack.push(c);
                    }
                    else if (c == ')') {
                        calAll(symbolStack, numStack, true);
                    }
                    else if (c == '=') {
                        calAll(symbolStack, numStack, false);
                        break;
                    }
                    else  {
                        int a = priority.get(c), b = priority.get(symbolStack.peek());
                        if (a > b) {
                            symbolStack.push(c);
                        }
                        else {
                            float result, num1, num2;
                            Character opt;
                            while (!symbolStack.empty() && a <= b) {
                                num2 = numStack.pop();
                                num1 = numStack.pop();
                                opt = symbolStack.pop();
                                Log.d("cal", "" + num1 + opt + num2);
                                numStack.push(calTwoNum(num1, num2, opt));
                                if (!symbolStack.empty()) {
                                    b = priority.get(symbolStack.peek());
                                }
                                else {
                                    break;
                                }
                            }
                            symbolStack.push(c);
                        }
                    }
                }


            }
        }
        ans = numStack.pop();
        return ans;
    }
}
