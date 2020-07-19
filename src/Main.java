import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Collection<People> peoples = Arrays.asList(
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMEN),
                new People("Иван Иванович", 69, Sex.MAN)
        );

        System.out.println("Военнообязанные: ");
        peoples.stream()
                .filter(value -> 18 <= value.getAge() && value.getAge() < 35)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("Средний возраст мужчин: "
                + peoples.stream()
                .filter(value -> value.getSex() == Sex.MAN)
                .mapToInt(People::getAge)
                .average()
                .getAsDouble());

        /* Если в выборке будут только женщины и ни одного мужчины
           будет исключение NoSuchElementException: No value present связано это с тем,
           что выборка будет пустая по фильтру "только мужчины"
           в качестве решения может быть следующий код:

            OptionalDouble aDouble = peoples.stream()
                    .filter(value -> value.getSex() == Sex.MAN)
                    .mapToInt(People::getAge)
                    .average();
            if (aDouble.isPresent()) {
                System.out.println("Средний возраст мужчин: " +aDouble.getAsDouble());
            } else {
                System.out.println("Мужчин в выборке нет");
            }

         */

        System.out.println("Количество потенциальных работоспособных людей: "
                + peoples.stream()
                .filter(value -> (value.getAge() >= 18) && (value.getSex() == Sex.MAN ? value.getAge() < 65 : value.getAge() < 60))
                .count());
    }
}
