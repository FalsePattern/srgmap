import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//Use this to update params.csv after editing srgmap.cfg
public class ToCsv {
    public static void main(String[] args) throws IOException {
        try (var cfg = Files.newBufferedReader(Path.of("srgmap.cfg"));
             var csv = Files.newBufferedWriter(Path.of("params.csv"))) {
            csv.append("param,name\n");
            cfg.lines()
               .filter(line -> line.startsWith("p_"))
               .map(line -> line.replace('=', ','))
               .forEach(line -> {
                   try {
                       csv.append(line).append('\n');
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               });
        }
    }
}