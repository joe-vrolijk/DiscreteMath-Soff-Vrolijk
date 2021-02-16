public class Vak {

    //define attributes
    private String naam;
    private int punten;
    private double cijfer;


    //create constructor
    public Vak(String naam, int punten){
        this.naam = naam;
        this.punten = punten;
    }

    // create getters
    public String getNaam() {
        return naam;
    }

    public int getPunten() {
        return punten;
    }

    public double getCijfer() {
        return cijfer;
    }

    // create setter
    public void setCijfer(double cijfer) {
        this.cijfer = cijfer;
    }


    /**
     * Deze method berekend of het cijfer voldoende is om de
     * studie punten te halen
     * @return Returned het aantal te behalen studiepunten als de score
     * hoger is als een 5.5 anders wordt er een 0 gereturned.
     */
    public int gehaaldePunten(){
        if (this.cijfer >= 5.5){
            return this.punten;
        } else return 0;
    }

}
