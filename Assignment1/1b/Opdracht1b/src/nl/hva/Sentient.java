package nl.hva;

public interface Sentient {
    boolean likes(Sentient other);

    void setLikes(Sentient other);

    String getIdentity();
}
