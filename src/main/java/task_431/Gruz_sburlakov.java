package task_431;

public class Gruz_sburlakov {
    public static void main(String args[]) {
        int i = 1;
        int totalBoxes = 148;
        int boxPerContainer = 5;
        int containerPerCargo = 9;
        int countContainers = 1;
        int countCargos = 1;

        double totalContainers = Math.ceil((double) totalBoxes / boxPerContainer);
        double totalCargos = Math.ceil(totalContainers / containerPerCargo);
        System.out.println("Всего потребуется контейнеров " + totalContainers);
        System.out.println("Всего потребуется грузовиков " + totalCargos);

        while (i <= totalBoxes) {
            if (i % (boxPerContainer * containerPerCargo) == 1) {

                System.out.println("Грузовик " + countCargos + ":");
                countCargos++;
            }
            if (i % boxPerContainer == 1) {
                System.out.println("Контейнер " + countContainers + ":");
                countContainers++;

            }
            System.out.println("Ящик " + i);
            i++;
        }
    }
}
