package tp1.control;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitialConfiguration {
    private List<String> descriptions;
    private int numeros [];
    public static final InitialConfiguration NONE = new InitialConfiguration();

    private InitialConfiguration() {}

    private InitialConfiguration(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public List<String> getShipDescription(){
        return Collections.unmodifiableList(descriptions);
    }

    public static InitialConfiguration readFromFile(String fileName) throws IOException {
        try {
            File file = new File(fileName);
            FileInputStream iStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
            String line;
            List<String> descriptions = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                descriptions.add(line);
            }
            InitialConfiguration initConfig = new InitialConfiguration(descriptions);

            return initConfig;
        } catch (FileNotFoundException e){
            throw new FileNotFoundException();
        } catch (IOException e){
            throw new IOException();
        }
    }
}