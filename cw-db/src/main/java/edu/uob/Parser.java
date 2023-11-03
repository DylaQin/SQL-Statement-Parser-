package edu.uob;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    // 1 总parser下分列小parser，parser的作用是检查愈发返回parse OK
    // 2 从object中继承方法（公有的：如check（））

    public Parser() {
    }

    static String returnInfo = "[OK]";
    static String storageFolderPath;

    public void dispatch(String command) {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.setup(command);
        String[] tokens = tokenizer.tokenise(command);

        int last = tokens.length-1;
//        if (tokens[last].charAt(0)!=';'){
//            Parser.returnInfo = "[ERROR] single colon lost";
//        } else
        if (tokens[0].equalsIgnoreCase("CREATE")){
            createCommand c = new createCommand();
            c.parser(tokens);
        } else if (tokens[0].equalsIgnoreCase("USE")){
            useCommand c = new useCommand();
            c.parser(tokens);
        } else if (tokens[0].equalsIgnoreCase("DROP")){
            dropCommand c = new dropCommand();
            c.parser(tokens);
        }
//        else if (tokens[0].equalsIgnoreCase("ALTER")){
//
//        } else if (tokens[0].equalsIgnoreCase("INSERT")){
//
//        } else if (tokens[0].equalsIgnoreCase("SELECT")){
//
//        } else if (tokens[0].equalsIgnoreCase("UPDATE")){
//
//        } else if (tokens[0].equalsIgnoreCase("DELETE")){
//
//        } else if (tokens[0].equalsIgnoreCase("JOIN")){
//
//        }
        else {
            returnInfo = "[ERROR] No command type.\"" + " ";
        }
    }
}
