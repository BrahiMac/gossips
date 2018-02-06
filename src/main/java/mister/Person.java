package mister;

import gossip.Gossips;

public abstract class Person {
    String nom;
    String word;
    Person receiver;
    boolean hasHeard;

    public Person() {
    }

    public Person(String nom) {
        hasHeard = false;
        this.nom = nom;
    }

    public void attachReceiver(Person receiver){
        this.receiver = receiver;
    }

    public void attachWord(String myWord){
        this.word = myWord;
        hasHeard = true;
    }

    public void spread(){

        if (cannotSpread() || hasHeard){
            return;
        }

        receiver.attachWord(word);
        word = "";

    }

    private boolean cannotSpread() {
        return (word == null) || (receiver == null);
    }

    public String answer(){
        return word;
    }
}
