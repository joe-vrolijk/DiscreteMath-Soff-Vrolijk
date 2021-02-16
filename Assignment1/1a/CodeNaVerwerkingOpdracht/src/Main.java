import java.util.Scanner;
import java.util.function.DoublePredicate;

public class Main {
    public static void main(String[] args) {

        Vak[] vakken = new Vak[7];
        String[] vakNamen = {"Fasten your seatbelts", "Programming", "User Interaction", "Personal Skills", "Databases", "OOP1", "Project Skills"};
        int[] vakPunten = {12, 3, 2, 2, 3, 3, 3};

        System.out.println("Voer behaalde cijfers in:");

        //initialisatie object vak
        initialiseerObjectVak(vakken, vakNamen, vakPunten);

        //print output
        printOutput(vakken);

        //print Bsa Advies
        printBsaAdvies(vakken, vakPunten);


    }

    /**
     * Deze method vraagt de user om een cijfer voor een vak. Deze input wordt gevalideerd en returned met het correcte cijfer.
     * Als men een verkeerde input of range invult dan blijft de method loopen totdat het cijfer valideerd.
     *
     * @param vakNaam Naam van het specificeerde vak.
     * @return returned een gevalideerd cijfer voor een vak.
     */
    public static double getUserCijfer(String vakNaam) {
        Scanner input = new Scanner(System.in);
        System.out.print(vakNaam + ": ");

        if (input.hasNextDouble()) {
            double num = input.nextDouble();
            if (isValidGrade().test(num)) {
                return num;
            } else {
                System.out.println("Verkeerde invoer. Gebruik een nummer tussen de 0 en 10");
                return -1;
            }
        } else {
            System.out.println("Verkeerde invoer. Gebruik een cijfer!");
            return -1;
        }
    }

    public static DoublePredicate isValidGrade() {
        DoublePredicate isAboveLowerBound = grade -> grade >= 0;
        DoublePredicate isBelowUpperBound = grade -> grade <= 10;

        return isAboveLowerBound
            .and(isBelowUpperBound);
    }

    public static DoublePredicate isValidGradeDoubleNegation() {
        DoublePredicate isAboveLowerBound = grade -> grade >= 0;
        DoublePredicate isBelowUpperBound = grade -> grade <= 10;

        return isAboveLowerBound.negate().negate()
            .and(isBelowUpperBound.negate().negate());
    }

    public static DoublePredicate isValidGradeWithMorganRules() {
        DoublePredicate isAboveLowerBound = grade -> grade >= 0;
        DoublePredicate isBelowUpperBound = grade -> grade <= 10;

        return (isAboveLowerBound.negate()
            .or(isBelowUpperBound.negate())).negate();
    }

    /**
     * Deze method kijkt naar de gedefineerde arrays en looped door alle array en initialiseerd deze.
     * De individuele posities van de drie arrays hebben een relatie met elkaar.
     * Hier worden alle vakken geinitialiseerd en gevult met data van de andere twee arrays.
     * Ook wordt hier de "getUserCijfer" methode gebruikt om de input te verkrijgen van de user
     * en deze op te slaan in de class Vak.
     *
     * @param vakken    Object van de class Vak.
     * @param vakNamen  Naam van het vak.
     * @param vakPunten Studiepunten te behalen voor het vak.
     */
    public static void initialiseerObjectVak(Vak[] vakken, String[] vakNamen, int[] vakPunten) {
        for (int i = 0; i < vakken.length; i++) {
            vakken[i] = new Vak(vakNamen[i], vakPunten[i]);

            double cijferVak = -1;

            do {
                cijferVak = getUserCijfer(vakken[i].getNaam());
                vakken[i].setCijfer(cijferVak);
            } while (cijferVak == -1);

        }
        System.out.printf("%n");
    }

    /**
     * Deze methode print de output van de vakken uit.
     * Hierbij worden de vakken genoemd, het behaalde cijfer en het aantal behaalde studiepunten.
     *
     * @param vakken Object van de class Vak
     */
    public static void printOutput(Vak[] vakken) {
        for (Vak vak : vakken) {
            System.out.printf("%-13s%-26s %-8s%-5.1f%-17s%-2d%n", "Vak/project: ", vak.getNaam(), "Cijfer: ", vak.getCijfer(), " Behaalde Punten:", vak.gehaaldePunten());
        }
    }

    /**
     * Deze method wordt gebruikt om het BSA advies te printen.
     *
     * @param vakken    Object van de class Vak.
     * @param vakPunten Het aantal te behalen studie punten voor het vak
     */
    public static void printBsaAdvies(Vak[] vakken, int[] vakPunten) {
        int totaal = 0;
        int behaald = 0;
        for (int i = 0; i < vakken.length; i++) {
            totaal += vakPunten[i];
            behaald += vakken[i].gehaaldePunten();
        }

        System.out.println("\nTotaal behaalde studiepunten: " + behaald + "/" + totaal);

        if (((double) totaal / 6 * 5 > (double) behaald)) {
            System.out.println("PAS OP: je ligt op schema voor een negatief BSA!");
        }


    }


}
