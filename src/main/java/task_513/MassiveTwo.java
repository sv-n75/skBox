package task_513;

public class MassiveTwo {

    public static void main(String[] args) {

        String[][] ix = new String[7][7];

        for (int i = 0; i < ix.length; i++) {
            for (int j = 0; j < ix[i].length; j++) {
                ix[i][j] = (j == i || j == ix.length - 1 - i) ? "x" : " ";
                System.out.print(ix[i][j]);
            }
            System.out.println();
        }

    }
}
