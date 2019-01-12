class DivisionExpression implements Expression {

    private final Expression right;
    private final Expression left;

    DivisionExpression(Expression right, Expression left) {
        this.right = right;
        this.left = left;
    }

    @Override
    public Double calculate() {
        return right.calculate() / left.calculate();
    }
}
