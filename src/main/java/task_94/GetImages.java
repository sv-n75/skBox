package task_94;
//Напишите программу, которая:
//        •	получает с помощью библиотеки jsoup HTML-код страницы https://lenta.ru;
//        •	находит в HTML-коде теги img и получает абсолютные ссылки на изображения из атрибута src;
//        •	скачивает изображения в папку images проекта, при этом сохраняя оригинальные названия файлов;
//        •	выводит в консоль список c названиями скачанных файлов.
//        Рекомендации
//        •	Все варианты подключения библиотеки jsoup в проект на странице скачивания библиотеки
//        •	Для получения ссылок воспользуйтесь префиксом abs при получении атрибута src у тега img. Пример из официальной документации.

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImages {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://lenta.ru")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();
        int i = 0;
        Elements img = doc.getElementsByTag("img");//находим тэги имг
        for (Element el : img) {
            String src = el.absUrl("src");

            if (src.substring(src.length() - 3).equals("jpg")) {
                checkNameImage(el, i);

                BufferedImage bufferedImage = ImageIO.read(checkNameImage(el, i).getUrl());
                File file = new File("D:\\skBox\\images\\" + checkNameImage(el, i).getImageName() + ".jpg");
                ImageIO.write(bufferedImage, "jpg", file);
                System.out.println(checkNameImage(el, i).getImageName());

            }
            i++;
        }
    }

    public static ReadImage checkNameImage(Element img, int i) throws MalformedURLException {
        String nameImage = img.attr("alt");
        if (nameImage.isEmpty())
            nameImage = "noName " + i;

        return new ReadImage(new URL(img.absUrl("src")), nameImage);

    }

}

class ReadImage {
    private URL url;
    private String imageName;

    public ReadImage(URL url, String imageName) {
        this.url = url;
        this.imageName = imageName;
    }

    public URL getUrl() {
        return url;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public String toString() {
        return "ReadImage{" +
                "url=" + url +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
