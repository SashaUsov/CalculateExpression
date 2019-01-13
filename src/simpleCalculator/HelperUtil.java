package simpleCalculator;

import java.util.Iterator;
import java.util.LinkedList;

class HelperUtil {

    static void ifSumSubtruction(LinkedList<Character> signQueue,
                                 char a,
                                 Iterator<Character> characterIterator,
                                 LinkedList<Character> charQueue,
                                 LinkedList<Expression> doubleQueue
    )
    {

        signQueue.add(a);

        String leftDigit = "";

        while (characterIterator.hasNext()) {

            leftDigit = leftDigit + charQueue.poll();

        }

        double left = Double.parseDouble(leftDigit);

        doubleQueue.add(new Calculate.DoubleExpression(left));
    }

    static void ifDigit(LinkedList<Character> charQueue,
                        char a,
                        char[] charArray,
                        int i,
                        Iterator<Character> characterIterator,
                        LinkedList<Expression> doubleQueue
    )
    {

        charQueue.add(a);

        if (i == charArray.length - 1) {

            String leftDigit = "";

            while (characterIterator.hasNext()) {

                leftDigit = leftDigit + charQueue.poll();

            }

            double left = Double.parseDouble(leftDigit);

            doubleQueue.add(new Calculate.DoubleExpression(left));
        }
    }

    static void ifMultiple(char[] charArray,
                           int i,
                           LinkedList<Character> charQueue,
                           Iterator<Character> characterIterator
    )
    {

        charArray[i] = "!".toCharArray()[0];

        String leftDigit = "";

        while (characterIterator.hasNext()) {

            leftDigit = leftDigit + charQueue.poll();

        }

        Expression left = new Calculate.DoubleExpression(Double.parseDouble(leftDigit));

        String rightDigit = "";

        for (int j = i + 1; j < charArray.length &&
                (Character.isDigit(charArray[j]) || ".".equals(Character.toString(charArray[j]))); j++ ) {

            rightDigit = rightDigit + charArray[j];

            charArray[j] = "!".toCharArray()[0];
        }

        Expression right = new Calculate.DoubleExpression(Double.parseDouble(rightDigit));

        Expression result = new MultiplicationExpression(left, right);

        char[] chars = result.calculate().toString().toCharArray();

        for (char x : chars) {

            charQueue.add(x);
        }

    }

    static void ifDivision(char[] charArray,
                           int i,
                           LinkedList<Character> charQueue,
                           Iterator<Character> characterIterator
    )
    {

        charArray[i] = "!".toCharArray()[0];

        String leftDigit = "";

        while (characterIterator.hasNext()) {

            leftDigit = leftDigit + charQueue.poll();

        }

        Expression left = new Calculate.DoubleExpression(Double.parseDouble(leftDigit));

        String rightDigit = "";

        for (int j = i + 1; j < charArray.length && Character.isDigit(charArray[j]); j++ ) {

            rightDigit = rightDigit + charArray[j];

            charArray[j] = "!".toCharArray()[0];
        }

        Expression right = new Calculate.DoubleExpression(Double.parseDouble(rightDigit));

        Expression result = new DivisionExpression(left, right);

        char[] chars = result.calculate().toString().toCharArray();

        for (char x : chars) {

            charQueue.add(x);
        }
    }

    static int parenthesesReplacement(char[] charArray,
                                      int i,
                                      LinkedList<Character> charQueue

    )
    {
        String expression = "";

        int x = i;

        for (int j = i +1; !")".equals(Character.toString(charArray[j])); j++) {

            expression = expression + charArray[j];

            x = j;

        }

        char[] array = expression.toCharArray();

        LinkedList<Character> parenthesesCharQueue = new LinkedList<>();

        Iterator<Character> characterIterator1 = parenthesesCharQueue.iterator();

        LinkedList<Character> parenthesesSignQueue = new LinkedList<>();

        Iterator<Character> characterIterator2 = parenthesesSignQueue.iterator();

        LinkedList<Expression> parenthesesDoubleQueue = new LinkedList<>();

        Expression expression1 = Calculate.toCalculate(array,
                parenthesesCharQueue,
                parenthesesSignQueue,
                parenthesesDoubleQueue,
                characterIterator1,
                characterIterator2);

        char[] chars = String.valueOf(expression1.calculate()).toCharArray();

        for (char z : chars) {

            charQueue.add(z);
        }

        return x + 1;
    }
}
