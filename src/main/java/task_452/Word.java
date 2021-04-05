package task_452;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {

    public static void main(String[] args) {

        String text = "Christmas is one of the most popular holidays on the planet. It seems strange but not everybody celebrate such a great holiday. It depends on the religion and the culture of people. So, who does not celebrate this holiday?\n" +
                "\n" +
                "Firstly, it’s a religious holiday. Christians commemorate the birth of Christ. So people who are neither Christian nor believers don’t have holiday mood on this day.\n" +
                "\n" +
                "Besides, other people refuse to take part in Christmas, because they consider that this holiday became too commercial. It’s true that in December stores are full of Christmas goods. And people spend a lot of money to have a good time with their friends, family and relatives. Most of them are ready to waste money for small useless presents. It is clear that they celebrate Christmas because it’s a family party and a great opportunity to be together.\n" +
                "\n" +
                "Nevertheless this cheerful and commercial side of this holiday attracts some countries, for example, China. They have been celebrating Christmas for several years. But this holiday is not in their culture.\n" +
                "\n" +
                "And in many countries we can find the same traditions. Families decorate a tree, prepare a holiday meal, and wait for Santa Claus and his gifts. But each country also has its things. In the USA there are garlands of popcorn, and in Sweden there are goats made of straw.\n" +
                "\n" +
                "Someone doesn’t celebrate Christmas, but two billion people, nearly every third person in the world, celebrate Christmas. And this year it will be again, and it will be magic.";

        Pattern pattern = Pattern.compile("\\b[\\w]+\\b");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
//ЗАДАНИЕ 3 ЛИБО АНАЛОГИЧНО ДАННОМУ ЛИБО ЧЕРЕЗ СПЛИТ - ПРОБЕЛ КАК И БЫЛО СДЕЛАНО