package io.khasang.snet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BackupBase {
    @Autowired
    Environment environment;

    public String makeBackUp() {
        String pgDump = environment.getProperty("postgresql.dumpAppPath");
        String dumpFile = environment.getProperty("postgresql.dumpFolder") + getBackupFileName();
        //Добавление комманд для запуска pg_dump
        final List<String> baseCmds = new ArrayList<>();
        //Путь до утилиты pg_dump
        baseCmds.add(pgDump);
        baseCmds.add("-h");
        baseCmds.add("localhost");
        //Порт
        baseCmds.add("-p");
        baseCmds.add("5432");
        //Пользователь
        baseCmds.add("-U");
        baseCmds.add(environment.getProperty("jdbc.postgresql.username"));
        //Включить большие объекты в выгрузку
        baseCmds.add("-b");
        //Включить подробный режим. pg_dump будет выводить в стандартный поток ошибок подробные комментарии к объектам, включая время начала и окончания выгрузки, а также сообщения о прогрессе выполнения.
        baseCmds.add("-v");
        //Отправить вывод в указанный файл. Параметр можно не указывать, если используется формат с выводом в файл.
        baseCmds.add("-f");
        baseCmds.add(dumpFile);
        //Имя базы
        baseCmds.add("snet");
        final ProcessBuilder processBuilder = new ProcessBuilder(baseCmds);

        // Устанавливаем пароль для пользователя
        final Map<String, String> env = processBuilder.environment();
        env.put("PGPASSWORD", environment.getProperty("jdbc.postgresql.password"));

        try {
            final Process process = processBuilder.start();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = reader.readLine();
            while (line != null) {
                System.err.println(line);
                line = reader.readLine();
            }
            reader.close();

            final int dcertExitCode = process.waitFor();
            if (dcertExitCode == 0) {
                return "Backup complete " + dumpFile;
            } else {
                return "Backup failed";
            }
        } catch (IOException | InterruptedException e) {
            return e.toString();
        }
    }

    private String getBackupFileName() {
        long currentTime = System.currentTimeMillis();
        String currentStringDate = new SimpleDateFormat("yyyy_MM_dd_HH-mm").format(currentTime);
        return "snet_" + currentStringDate + ".backup";
    }

}
