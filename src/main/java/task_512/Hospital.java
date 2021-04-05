package task_512;

public class Hospital {

    static final int PATIENT_COUNT = 30;
    static final float MAX_T = 40.0f;
    static final float MIN_T = 32.0f;
    static final float MIN_T_H = 36.2f;
    static final float MAX_T_H = 36.9f;


    public static void main(String[] args) {

        float[] temperature = new float[PATIENT_COUNT];
        double summa = 0;
        int health = 0;
        System.out.print("Температуры пациентов: ");
        for (int i = 0; i < PATIENT_COUNT; i++) {//инициализируем массив
            temperature[i] = MIN_T + (float) Math.ceil((Math.random() * (MAX_T - MIN_T) * 10)) / 10;

            if (temperature[i] >= MIN_T_H && temperature[i] <= MAX_T_H) {//ищем здоровых
                health++;
            }
            summa += temperature[i];

            System.out.print(temperature[i] + " ");

        }
        System.out.println();
        System.out.println("Средняя температура: " + Math.ceil((summa / PATIENT_COUNT) * 100) / 100);
        System.out.println("Количество здоровых: " + health);
    }
}
