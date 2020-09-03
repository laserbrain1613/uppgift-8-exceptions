package se.lexicon.exceptions.workshop.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader_Writer {
    /**
     * This method getMaleFirstNames should use a try-catch-finally without resources
     * Should catch FileNotFoundException and IOException
     * You should also close the Buffered reader in the finally block
     *
     * @return List<String>of male firstnames
     */
    public static List<String> getMaleFirstNames() {

        BufferedReader reader = null;
        List<String> names = null;

        try {
            reader = Files.newBufferedReader(Paths.get("firstname_males.txt")); // Reader variable was declared outside the try scope
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return names;
    }


    /**
     * This method getFemaleFirstNames should make use of a try-catch with resources
     *
     * @return names
     */
    public static List<String> getFemaleFirstNames() {

        List<String> names = null;

        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("firstname_female.txt")); // This time, the reader is within the local scope
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return names;
    }


    /**
     * This method fetches strings from a file and put them into a list
     * This method might throw IOException which due to the throws clause need to
     * be handled by the caller.
     *
     * @return List <String> of last names
     * @throws IOException
     */
    public static List<String> getLastNames() throws IOException {

        List<String> names = null;
        BufferedReader reader = null;

        try { // Catch is handled by the caller
            reader = Files.newBufferedReader(Paths.get("lastnames.txt"));
            names = reader.lines()
                    .flatMap(line -> Stream.of(line.split(",")))
                    .collect(Collectors.toList());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return names;
    }


    public static void saveLastNames(List<String> lastNames) {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("lastnames.txt"))) {
            for (String toWrite : lastNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void saveFemaleNames(List<String> femaleNames) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_female.txt"))) {
            for (String toWrite : femaleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void saveMaleNames(List<String> maleNames) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("firstname_males.txt"))) {
            for (String toWrite : maleNames) {
                writer.append(toWrite + ",");
            }
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
