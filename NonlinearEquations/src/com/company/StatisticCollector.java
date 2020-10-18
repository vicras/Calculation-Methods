package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticCollector {




    public static void collect(String fileName, List<Parameters> list){
        var line =  list.stream().map(Parameters::toString).collect(Collectors.joining("\n"));
        try {
            Files.write(Paths.get(fileName), Collections.singleton(line), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
