package simpleCalculator;

import java.util.Iterator;
import java.util.LinkedList;

public class Calculate {

    public static void main(String[] args) {
        printExpressionResult("1+2+(1*1)");
    }

    private static void printExpressionResult(String expression) {
        // convertExpression(expression)
        Expression expression1 = covertToExpression(expression);
        System.out.println(expression1.calculate());
    }

    public static Expression covertToExpression(String expression) {


        char[] charArray = expression.replaceAll("\\s", "").toCharArray();

        LinkedList<Character> charQueue = new LinkedList<>();

        LinkedList<Character> signQueue = new LinkedList<>();

        LinkedList<Expression> doubleQueue = new LinkedList<>();

        Iterator<Character> characterIterator = charQueue.iterator();

        Iterator<Character> signIterator = signQueue.iterator();

        return toCalculate(charArray, charQueue, signQueue, doubleQueue, characterIterator, signIterator);

    }

    static Expression toCalculate(char[] charArray,
                                  LinkedList<Character> charQueue,
                                  LinkedList<Character> signQueue,
                                  LinkedList<Expression> doubleQueue,
                                  Iterator<Character> characterIterator,
                                  Iterator<Character> signIterator
            )
    {

        for (int i = 0; i < charArray.length; i++) {

            char a = charArray[i];

            if (!"!".equals(Character.toString(a))) {
                if (".".equals(Character.toString(a)) || Character.isDigit(a)) {

                    HelperUtil.ifDigit(charQueue, a, charArray, i, characterIterator, doubleQueue);

                }  else if ("(".equals(Character.toString(a))) {

                    i = HelperUtil.parenthesesReplacement(charArray, i, charQueue);
                }
                else if (!Character.isDigit(a)) {

                    if ("*".equals(Character.toString(a))) {

                        HelperUtil.ifMultiple(charArray, i, charQueue, characterIterator);


                    } else if ("/".equals(Character.toString(a))) {

                        HelperUtil.ifDivision(charArray, i, charQueue, characterIterator);

                    } else if (charQueue.isEmpty()) {

                        HelperUtil.ifDigit(charQueue, a, charArray, i, characterIterator, doubleQueue);

                    } else {

                        HelperUtil.ifSumSubtruction(signQueue, a, characterIterator, charQueue, doubleQueue);

                    }

                }
            }

        }

        if (characterIterator.hasNext()) {

            String leftDigit = "";

            while (characterIterator.hasNext()) {

                leftDigit = leftDigit + charQueue.poll();

            }

            double left = Double.parseDouble(leftDigit);

            doubleQueue.add(new Calculate.DoubleExpression(left));
        }

        while (signIterator.hasNext()) {

            Expression left = doubleQueue.poll();

            Expression right = doubleQueue.poll();

            char sign = signQueue.poll();

            if ("+".equals(Character.toString(sign))) {

                Expression result = new SumExpression(left, right);

                doubleQueue.offerFirst(result);

            } else if ("-".equals(Character.toString(sign))) {

                Expression result = new SubtructionExpression(left, right);

                doubleQueue.offerFirst(result);

            }
        }

        return doubleQueue.poll();
    }

    static class DoubleExpression implements Expression {

        private final Double value;

        DoubleExpression(Double value) {
            this.value = value;
        }

        @Override
        public Double calculate() {
            return value;
        }
    }
}
