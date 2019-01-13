package simpleCalculator;

public class MultiplicationExpression implements Expression {

    private final Expression right;
    private final Expression left;

    MultiplicationExpression(Expression right, Expression left) {

        this.right = right;
        this.left = left;
    }

    @Override
    public Double calculate() {

        return right.calculate() * left.calculate();

    }
}