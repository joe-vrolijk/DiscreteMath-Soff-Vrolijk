package nl.hva;

import java.util.ArrayList;
import java.util.List;

public class SentientImpl implements Sentient {
    public final String name;
    public final List<Sentient> likesList = new ArrayList<>();

    public SentientImpl(String name) {
        this.name = name;
    }

    @Override
    public boolean likes(Sentient other) {
        return likesList.contains(other);
    }

    @Override
    public void setLikes(Sentient other) {
        likesList.add(other);
    }

    @Override
    public String getIdentity() {
        return name;
    }
}
