import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    static void ifNotDigit(LinkedList<Character> signQueue,
                        LinkedList<Character> charQueue,
                        char a,
                        char[] charArray,
                        int i,
                        Iterator<Character> characterIterator,
                        LinkedList<Expression> doubleQueue
    ) {

        signQueue.add(a);

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
                           Iterator<Character> characterIterator,
                           LinkedList<Expression> doubleQueue
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
                           Iterator<Character> characterIterator,
                           LinkedList<Expression> doubleQueue
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

    static String parenthesesDetection(String expr) {

        String expression = expr;

        String result = "";

        String[] charArray = expression.split("");

        List<String> strings = Arrays.asList(charArray);


        for (int i = 0; !"!".equals(charArray[i]); i++) {

            String a = charArray[i];

            int count = 0;

            if ("(".equals(a)) {

                charArray[i] = "!";


                String s = "";

                String replace = "";

                for (int j = i + 1; !")".equals(charArray[j]); j++) {

                    s = s.concat(charArray[j]);

                    charArray[j] = "!";

                    count++;
                }

                charArray[i + count + 1] = "!";

                for (int k = 0; k < count + 2; k++) {

                    replace = replace.concat("!");
                }

                count = 0;

                String expression1 = String.valueOf(Calculate.toCalculate(s).calculate());

                String s1 = "";

                for (String z : charArray) {

                    s1 = s1.concat(z);
                }

                expression = s1.replaceAll(replace, expression1);

                result = result.concat(expression);

            }

        }

        return result;
    }

//    static String parenthesesDetection(String expr) {
//
//        String expression = expr;
//
//        char[] charArray = expression.toCharArray();
//
//        for (int i = 0; i < charArray.length; i++) {
//
//            char a = charArray[i];
//
//            int count = 0;
//
//            if ("(".equals(Character.toString(a))) {
//
//                charArray[i] = "!".toCharArray()[0];
//
//                String s = "";
//
//                String replace = "";
//
//                for (int j = i + 1; !")".equals(Character.toString(charArray[j])); j++) {
//
//                    s = s + charArray[j];
//
//                    charArray[j] = "!".toCharArray()[0];
//
//                    count++;
//                }
//
//                charArray[i + count + 1] = "!".toCharArray()[0];
//
//                for (int k = 0; k < count + 2; k++) {
//
//                    replace = replace.concat("!");
//                }
//
//                count = 0;
//
//                String expression1 = String.valueOf(Calculate.toCalculate(s).calculate());
//
//                String s1 = "";
//
//                for (char z : charArray) {
//
//                    s1 = s1 + z;
//                }
//
//                expression = s1.replaceAll(replace, expression1);
//
//            }
//
//        }
//
//        return expression;
//    }
}
