package Others;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderWriter {

    public static List<String> fileReaderLOS(Path path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(String.valueOf(path)));
        return lines;
    }

    public static JSONObject fileReaderJo(Path path) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(String.valueOf(path)));
        JSONObject jo = (JSONObject) obj;
        return jo;
    }

    public static void fileWriterLOS(Path path, List<String> los) throws IOException {
        PrintWriter writer = new PrintWriter(String.valueOf(path),"UTF-8");
        for (String s : los){
            writer.println(s);
        }
        writer.close();
    }

    public static void fileWriterJo (Path path, JSONObject jo) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(String.valueOf(path),"UTF-8");
        writer.write(jo.toJSONString());
        writer.close();
    }



    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
