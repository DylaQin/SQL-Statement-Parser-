package edu.uob;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class createCommand extends DBFunction {
    DBServer server = new DBServer();
    String path = server.getStorageFolderPath();
    String curFolder = server.getCurrentFolder();


    // <Create> ::= <CreateDatabase> | <CreateTable>
    // <CreateDatabase> ::= "CREATE DATABASE"[DatabaseName]
    // <DatabaseName> ::= [PlainText]

    // <CreateTable> ::= "CREATE TABLE"[TableName] | "CREATE TABLE"[TableName]"("<AttributeList")"
    // <TableName> ::= [PlainText]
    // <AttributeName> ::= [PlainText] | [TableName]"."[PlainText]

    @Override
    public void parser(String[] tokens) {
//        Parser.returnInfo = tokens.length + "long"+ Arrays.toString(tokens);
        checkColon(tokens);
        if (!isPlainText(tokens[2])) {
            Parser.returnInfo = "[ERROR] Wrong Database or Table Name";
        } else if (!(tokens[1].equalsIgnoreCase("DATABASE") || tokens[1].equalsIgnoreCase("TABLE"))) {
            // no database, no table
            Parser.returnInfo = "[ERROR] no Object(database || table) to created";
        }
        // length>=5 means attributeList exist
        int start = 4;
        int last = tokens.length-2;
        if (tokens.length > 4){
            if (tokens[3].charAt(0)!='(' && tokens[last].charAt(0)!=')'){
                Parser.returnInfo = "[ERROR] bracket lost";
            }
            ArrayList<String> attriList = new ArrayList<>();
            while (start<last){
                attriList.add(tokens[start]);
                start++;
            }
            if (!isAttributeList(attriList)){
                Parser.returnInfo = "[ERROR] bad Attribute";
            }
        }
        // interpreter
        if (Parser.returnInfo.equalsIgnoreCase("[OK]")){
            run(tokens);
        }
    }
    @Override
    public void run(String[] tokens){
        if (tokens[1].equalsIgnoreCase("DATABASE")){
            File obj = new File(path + File.separator + tokens[2]);
            if (obj.isDirectory()){
                Parser.returnInfo = "[ERROR] such database already exist";
            } else if (obj.mkdir()){
                Parser.returnInfo = "[OK] database created";
            }
        } else if (tokens[1].equalsIgnoreCase("TABLE")){
            if (curFolder==null){
                Parser.returnInfo = "[ERROR] no database selected yet";
            }
            try {
                File obj = new File(path + File.separator + curFolder + File.separator + tokens[2]);
                if (obj.exists()){
                    Parser.returnInfo = "[ERROR] table already exist!";
                } else if (obj.createNewFile()){
                    Parser.returnInfo = "[OK] table created";
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        if (tokens.length>4){
            ArrayList<String> row = new ArrayList<>();
            row.add(0,"ID");
            int start = 4;
            while (start<tokens.length-2){
                // if the attribute contains ID, ignore it
                if (row.contains(tokens[start])){
                    Parser.returnInfo = "[ERROR] repeated attribute";
                } else {
                    row.add(tokens[start]);
                }
                start++;
            }
//            insertRow(row); // call function in insertCommand()
        }
    }
}
