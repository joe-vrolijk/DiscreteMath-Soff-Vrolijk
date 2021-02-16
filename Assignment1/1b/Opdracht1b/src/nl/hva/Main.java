package nl.hva;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    private static final Sentient trump = new SentientImpl("Trump");

    public static void main(String[] args) {
        List<Sentient> allDomain = prepareForAllDomain();
        List<Sentient> existDomain = prepareThereExistDomain();
        Predicate<Sentient> predicate = preparePredicate();
        boolean resultForAll = forAll(allDomain, predicate);
        boolean resultForAllShouldBeFalse = forAll(existDomain, predicate);
        boolean resultThereExists = thereExists(existDomain, predicate);
        System.out.printf("True: %b%n", resultForAll);
        System.out.printf("False: %b%n", resultForAllShouldBeFalse);
        System.out.printf("True: %b%n", resultThereExists);
    }

    private static <T> boolean thereExists(List<T> domain, Predicate<T> predicate) {
        return domain.stream()
                .anyMatch(predicate);
    }

    private static <T> boolean forAll(List<T> domain, Predicate<T> predicate) {
        return domain.stream()
                .allMatch(predicate);
    }

    private static List<Sentient> prepareForAllDomain() {
        List<Sentient> list = List.of(
            new SentientImpl("alice"),
            new SentientImpl("bob"),
            new SentientImpl("david"),
            new SentientImpl("joe")
        );
        list.forEach(s -> s.setLikes(trump));
        return list;
    }

    private static List<Sentient> prepareThereExistDomain() {
        List<Sentient> list = new ArrayList<>(prepareForAllDomain());
        list.add(new SentientImpl("bernie"));
        return list;
    }

    private static Predicate<Sentient> preparePredicate() {
        return sentient -> sentient.likes(trump);
    }
}
