package simpleCalculator;

public class SumExpression implements Expression {

    private final Expression right;
    private final Expression left;

    SumExpression(Expression right, Expression left) {

        this.right = right;
        this.left = left;
    }

    @Override
    public Double calculate() {

        return right.calculate() + left.calculate();

    }
}