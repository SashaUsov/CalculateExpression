import java.util.Iterator;
import java.util.LinkedList;

public class Calculate {

    public static void main(String[] args) {
        printExpressionResult("1*2.0+(3-2*1)+(2*1)+(6/2)");
    }

    private static void printExpressionResult(String expression) {
        // convertExpression(expression)
        System.out.println(covertToExpression(expression).calculate());
    }

    static Expression covertToExpression(String expr) {

        String expression = expr.replaceAll("\\s", "");

        while (expression.contains("(")) {

            expression = HelperUtil.parenthesesDetection(expression);
        }

            return toCalculate(expression);

    }

    static Expression toCalculate(String expression) {

        char[] charArray = expression.replaceAll("\\s", "").toCharArray();

        LinkedList<Character> charQueue = new LinkedList<>();

        LinkedList<Character> signQueue = new LinkedList<>();

        LinkedList<Expression> doubleQueue = new LinkedList<>();

        Iterator<Character> characterIterator = charQueue.iterator();

        Iterator<Character> signIterator = signQueue.iterator();


        for (int i = 0; i < charArray.length; i++) {

            char a = charArray[i];

            if ("!".equals(Character.toString(a))) {

            } else if (".".equals(Character.toString(a)) || Character.isDigit(a)) {

                HelperUtil.ifDigit(charQueue, a, charArray, i, characterIterator, doubleQueue);

            }  else if (!Character.isDigit(a)) {

                if ("*".equals(Character.toString(a))) {

                    HelperUtil.ifMultiple(charArray, i, charQueue, characterIterator, doubleQueue);


                } else if ("/".equals(Character.toString(a))) {

                    HelperUtil.ifDivision(charArray, i, charQueue, characterIterator, doubleQueue);

                } else if (charQueue.isEmpty()) {

                    HelperUtil.ifDigit(charQueue, a, charArray, i, characterIterator, doubleQueue);

                } else {

                    HelperUtil.ifSumSubtruction(signQueue, a, characterIterator, charQueue, doubleQueue);

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
