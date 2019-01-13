package simpleCalculator;

public class SubtructionExpression implements Expression {

    private final Expression right;
    private final Expression left;

    SubtructionExpression(Expression right, Expression left) {

        this.right = right;
        this.left = left;
    }

    @Override
    public Double calculate() {

        return right.calculate() - left.calculate();

    }
}