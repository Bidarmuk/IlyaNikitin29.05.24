import java.util.function.Function;

class GCDFunction implements Function<int[], Integer> {

    @Override
    public Integer apply(int[] numbers) {
        if (numbers.length != 2) {
            throw new IllegalArgumentException("Exactly two numbers are required.");
        }

        int a = numbers[0];
        int b = numbers[1];

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return Math.abs(a); // НОД всегда положителен
    }

    public static void main(String[] args) {
        GCDFunction gcdFunction = new GCDFunction();
        int[] numbers = {100, 250};
        int result = gcdFunction.apply(numbers);
        System.out.println("Наибольший общий делитель: " + result);
    }
}
