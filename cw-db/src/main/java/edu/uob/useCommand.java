package edu.uob;

import java.io.File;
import java.io.IOException;

public class useCommand extends DBFunction {
    DBServer server = new DBServer();
    String path = server.getStorageFolderPath();

    // <USE> ::= "USE" [DatabaseName]
    // <DatabaseName> ::= [PlainText]

    @Override
    public void parser(String[] tokens){
        checkColon(tokens);
        if (tokens.length!=3){
            Parser.returnInfo = "[ERROR] wrong command length";
        } else if (!isPlainText(tokens[1])){
            Parser.returnInfo = "[ERROR] invalid database name";
        }
        if (Parser.returnInfo.equalsIgnoreCase("[OK]")){
            run(tokens);
        }
    }

    @Override
    public void run(String[] tokens){
        File obj = new File(path+File.separator+tokens[1]);
        if (!obj.isDirectory()){
            Parser.returnInfo = "[ERROR] no such database exist";
        } else {
            server.setCurrentFolder(path+File.separator+tokens[1]);
        }
    }
}
