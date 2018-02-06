package gossip;

import mister.Mister;
import mister.Person;

import java.util.HashMap;
import java.util.Map;

public class Gossips {

    Map<String,Person> persons;
    String currentPerson;
    String myWord;

    public Gossips(String... misters) {

        persons = new HashMap<String, Person>();

        for (String misterDescription : misters) {
            if (misterDescription.startsWith("Mister ")){
                String name = misterDescription.substring(7);
                persons.put(name, new Mister(name));
            }
        }
    }

    public Gossips from(String gossip) {
        currentPerson = gossip;
        return this;
    }

    public Gossips to(String receiver) {

        if (myWord == null) {
            Person sender = persons.get(currentPerson);
            sender.attachReceiver(persons.get(receiver));
            return this;
        }

        Person person = persons.get(receiver);
        person.attachWord(myWord);
        myWord = null;
        return this;

    }

    public Gossips say(String word) {
        myWord = word;
        return this;
    }

    public String ask(String personName) {

        return persons.get(personName).answer();
    }

    public void spread() {
        for (Map.Entry<String, Person> entry : persons.entrySet())
        {
            persons.get(entry.getKey()).spread();
        }
    }
}
