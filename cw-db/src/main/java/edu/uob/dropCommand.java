package edu.uob;

import java.io.File;
import java.nio.file.Files;

public class dropCommand extends DBFunction{

    DBServer server = new DBServer();
    String path = server.getStorageFolderPath();
    String curFolder = server.getCurrentFolder();

    // <Drop> ::=  "DROP DATABASE " [DatabaseName] | "DROP TABLE " [TableName]

    @Override
    public void parser(String[] tokens) {
        checkColon(tokens);
        if (tokens.length!=4) {
            Parser.returnInfo = "[ERROR] invalid query length";
        } else if (!isPlainText(tokens[2])){
            Parser.returnInfo = "[ERROR] invalid database or table name";
        } else if (!(tokens[1].equalsIgnoreCase("TABLES") || tokens[1].equalsIgnoreCase("DATABASE"))){
            Parser.returnInfo = "[ERROR] invalid query: table";
        }
        if (Parser.returnInfo.equals("[OK]")){
            run(tokens);
        }
    }

    @Override
    public void run(String[] tokens) {
        if (tokens[1].equalsIgnoreCase("TABLE")){
            File dir = new File(path+File.separator+tokens[2]);
            if (!dir.exists()){
                Parser.returnInfo = "[ERROR] table doesnt exist";
            } else if (!dir.delete()) {
                Parser.returnInfo = "[ERROR] drop failed";
            }
        } else {
            // Java has an option to delete a directory. However, this requires the directory to be empty.
            File dir = new File(path+File.separator+tokens[2]);
            File[] tables = dir.listFiles();
            if (tables != null) {
                for (File file : tables) {
                    if (!file.delete()){
                        Parser.returnInfo = "[ERROR] drop failed";
                    }
                }
            }
            if (!dir.delete()){
                Parser.returnInfo = "[ERROR] drop failed";
            }
        }
    }
}
