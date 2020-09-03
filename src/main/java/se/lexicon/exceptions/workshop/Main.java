package se.lexicon.exceptions.workshop;

import java.io.IOException;
import java.util.List;

import se.lexicon.exceptions.workshop.data_access.NameService;
import se.lexicon.exceptions.workshop.domain.Person;
import se.lexicon.exceptions.workshop.exceptions.DuplicateNameException;
import se.lexicon.exceptions.workshop.fileIO.CSVReader_Writer;

public class Main {

    public static void main(String[] args) {

        List<String> maleFirstNames = CSVReader_Writer.getMaleFirstNames();
        List<String> femaleFirstNames = CSVReader_Writer.getFemaleFirstNames();

        List<String> lastNames = null;
        try { // Calling method is responsible of handling IOException
            lastNames = CSVReader_Writer.getLastNames();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        NameService nameService = new NameService(maleFirstNames, femaleFirstNames, lastNames);

        Person test = nameService.getNewRandomPerson(); // Appears to be working
        System.out.println(test);

        // --------- Debugging
        for (int i = 0; i < 2; i++) {
            try {
                nameService.addFemaleFirstName("Anna"); // Should work first time, fail second time
            } catch (DuplicateNameException e) {
                System.out.println(e.getMsg());
            }
        }

        for (int i = 0; i < 2; i++) {
            try {
                nameService.addMaleFirstName("Lars"); // Should work first time, fail second time
            } catch (DuplicateNameException e) {
                System.out.println(e.getMsg());
            }
        }

        for (int i = 0; i < 2; i++) {
            try {
                nameService.addLastName("Stenberg"); // Should work first time, fail second time
            } catch (DuplicateNameException e) {
                System.out.println(e.getMsg());
            }
        }

    }

}