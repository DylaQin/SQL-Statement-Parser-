package edu.uob;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class dropExecute implements Execute{
    @Override
    public void execute(String[] tokens) {

    }

//    @Override
//    public void execute(String[] tokens) {
//        if (tokens[1].equalsIgnoreCase("database")){
//            // if the database has tables inside, how to iteratively delete all of those?
//            File dir = new File();
//
//        } else {
//            File obj = new File(Parser.curFolder++tokens[2]);
//            if (obj.delete()){
//                Parser.returnInfo = "[OK] database is deleted";
//            } else {
//                Parser.returnInfo = "[ERROR] cannot delete the table";
//            }
//        }
//    }
}
