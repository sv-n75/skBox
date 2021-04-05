package task_454_455;

public class StringReg {

    public static void main(String[] args) {

        String number = "8 01(234)56rtw89";
        number = number.replaceAll("[^0-9]", "");
        System.out.println(number);
        char[] digit = number.toCharArray();
        int start = Integer.parseInt(number.substring(0, 1));
        StringBuilder stringBuilder = new StringBuilder(number);

        if (digit.length == 10) {
            stringBuilder.insert(0, 7);
        }

        if (digit.length == 11) {
            if (start != 7 && start != 8) {
                System.out.println("Неверный формат");
                return;
            }
            if (start == 8) {
                stringBuilder.replace(0, 1, "7");
            }
        }
        System.out.println(stringBuilder);


        // задание 4,5,5

        String text = "ertyjnbfs   fggth<hhfh   ie>hiuu   ig<gug>ooo";
        String placeholder = "URA";
        System.out.println(searchAndReplaceDiamonds(text, placeholder));


    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        text = text.replaceAll("<.+?>", placeholder);
        return text;
    }
} 
