package task_432;

public class GeometryCalculator {
//    В проекте GeometryCalculator реализуйте в классе GeometryCalculator методы:
//            — подсчёта площади круга, площади треугольника и объёма сферы;
//— проверки возможности построения треугольника по трём длинам сторон.
//
//    Рекомендации для реализации формул
//•	площадь треугольника по трём сторонам:
//
//            •	Условие возможности построения треугольника: у треугольника сумма любых двух сторон
//            должна быть больше третьей.

    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {

        return Math.PI * Math.pow(Math.abs(radius), 2);
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return 4 * Math.PI * Math.pow(Math.abs(radius), 2);
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        boolean isTriangle = false;
        if ((a + b > c && a + c > b && b + c > a))
            isTriangle = true;
        return isTriangle;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (!isTriangleRightAngled(a, b, c)) {
            return -1.0;
        }
        return Math.sqrt((a + b + c) / 2 * ((a + b + c) / 2 - a) * ((a + b + c) / 2 - b) * ((a + b + c) / 2 - c));
    }
}
