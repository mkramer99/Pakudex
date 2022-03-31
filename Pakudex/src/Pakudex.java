import java.util.ArrayList;
import java.util.Collections;

public class Pakudex {
    // contains all the Pakuri objects
    Pakuri[] pack;

    public Pakudex() {
        pack = new Pakuri[20];
    }

    public Pakudex(int capacity) {
        pack = new Pakuri[capacity];
    }

    public int getSize() {
        // returns num of Pakuri in the Pakudex
        int num = 0;
        for(int i = 0; i < pack.length; i++) {
            if (pack[i] != null) {
                num++;
            }
        }
        return num;
    }

    public int getCapacity() {
        // returns size of the Pakudex
        return pack.length;
    }
    // ADD- IF NULL RETURN NULL
    public String[] getSpeciesArray() {
        if (getSize() == 0) {
            return null;
        }
        int count = 0;
        String[] speciesArray = new String[getSize()];
        ArrayList<String> speciesList = new ArrayList<>();
        try {
            for (Pakuri p : pack) {
                String species = p.getSpecies();
                speciesList.add(species);
                count++;
            }
        }
        // catch exception for null values
        catch (Exception e) {
            //speciesArray = speciesList.toArray(speciesArray);
        }
        return speciesList.toArray(speciesArray);
    }
    // find pakuri in pack and return stats
    public int[] getStats(String species) {
        int[] stats = new int[3];
        for(Pakuri p: pack) {
            if (p == null) {
                stats = null;
                break;
            }
            if (p.getSpecies().equals(species)) {
                stats[0] = p.getAttack();
                stats[1] = p.getDefense();
                stats[2] = p.getSpeed();
                break;
            }
        }
        return stats;
    }

    public void sortPakuri() {
        Pakuri temp;
        // selection sort
        // referenced this site for guidance
        // https://www.baeldung.com/java-selection-sort
        for(int i = 0; i < getSize()-1; i++) {
            // null check
            if (i + 1 < getSize()) {
                if (pack[i + 1] == null) {
                    break;
                }
            }
            for (int j = i + 1; j < getSize(); j++) {
                if (pack[i].getSpecies().compareToIgnoreCase(pack[j].getSpecies()) > 0) {
                    temp = pack[i];
                    pack[i] = pack[j];
                    pack[j] = temp;
                }
            }
        }
    }
    // add pakuri to pakudex
    public boolean addPakuri(String species) {
        boolean b = true;
            Pakuri p = new Pakuri(species);
            // check for existing species names
            for (int i = 0; i < pack.length; i++) {
                // if pack is empty, assign species to location 0
                if (pack[0] == null) {
                    pack[i] = p;
                    break;
                }
                // if added pakuri matches an existing pakuri in pack
                try {
                    if (pack[i].getSpecies().equals(p.getSpecies()) == true) {
                        b = false;
                        System.out.print("Error: Pakudex already contains this species!\n");
                        break;
                    }
                }
                // catch for null exception
                catch (Exception e) { }
                if (pack[i] == null) {
                    pack[i] = p;
                    break;
                }
            }
            return b;
    }
    // evolve the species stats
    public boolean evolveSpecies(String species) {
        boolean b = true;
        for (Pakuri p : pack) {
            // if null
            if (p == null) {
                b = false;
                break;
            }
            // if pakuri found in pack, evolve
            if (p.getSpecies().equals(species)) {
                p.evolve();
                break;
            }
            else {
                b = false;
            }
        }
        return b;
    }
}
