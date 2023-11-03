package edu.uob;

import java.util.ArrayList;
import java.util.List;

public class DBFunction {

    public void parser(String[] tokens){};
    public void run(String[] tokens){};
    public void checkColon(String[] tokens){
        int last = tokens.length-1;
        if (tokens[last].charAt(0)!=';'){
            Parser.returnInfo = "[ERROR] missing colon";
        }
    }

    public boolean isAttributeList(ArrayList<String> list){
        // <AttributeList> ::= [AttributeName] | [AttributeName]","<AttributeList>
        int start = 0;
        while (start<list.size()){
            if (start!=0 && list.get(start).charAt(0)==',' && isAttributeName(list.get(start-1))) {
                continue;
            } else if (!isAttributeName(list.get(start))) {
                Parser.returnInfo = "[ERROR] invalid attribute name or missing comma";
                break;
            }
            start++;
        }
        return true;
    }
    public boolean isAttributeName(String name){
        // <AttributeName> ::= [PlainText] | [TableName]"."[PlainText]
        boolean isDot = false;
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        int i = 0;
        while (i<name.length()) {
            if (name.charAt(i)!='.'){
                left.append(name.charAt(i));
                i++;
            } else {
                isDot = true;
                while (i<name.length()){
                    right.append(name.charAt(i));
                    i++;
                }
                break;
            }
        }
        if (isDot){
            // <TableName> ::= [PlainText]
            return isPlainText(right.toString()) && isPlainText(left.toString());
        }
        return isPlainText(left.toString());
    }
    public boolean isPlainText(String command) {
        // <PlainText> ::= [Letter] | [Digit] | [PlainText] [Letter] | [Plaintext] [Digit]
        int i=0;
        while (i<command.length()){
            char a = command.charAt(i);
            if (Character.isLetter(a) || Character.isDigit(a)){
                i++;
            } else {
                return false;
            }
        }
        return true;
    }
//    public boolean isAlteration(String command) {
//        // [AlterationType]  ::=  "ADD" | "DROP"
//        return (command.equalsIgnoreCase("ADD") || command.equalsIgnoreCase("DROP"));
//    }

//    public boolean isValueList(String[] tokens, int start){
//        // <ValueList> ::=  [Value] | [Value] "," <ValueList>
//        String value = null;
//        while (start<tokens.length){
//            if ()
//        }
//    }

//    public boolean isValue(List<String> command){
        // [Value] ::=  "'" [StringLiteral] "'" | [BooleanLiteral] | [FloatLiteral] | [IntegerLiteral] | "NULL"
        // trim quote if its a StringLiteral

//    }
//    public boolean isStrLiteral(ArrayList<String> value){
        // [StringLiteral] ::=  "" | [CharLiteral] | [StringLiteral] [CharLiteral]
//        for (String s : value){
//            if (Objects.equals(s, "") || isCharLiteral(s)){
//
//            }
//        }
//    }
//    public boolean isCharLiteral(char literal){
        // [CharLiteral] ::=  [Space] | [Letter] | [Symbol] | [Digit]
        // [Space] ::=  " "
        // [Letter] ::=  [Uppercase] | [Lowercase]
        // [Symbol] ::=  "!" | "#" | "$" | "%" | "&" | "(" | ")" | "*" | "+" | "," | "-" | "." | "/" | ":" | ";" | ">" | "=" | "<" | "?" | "@" | "[" | "\" | "]" | "^" | "_" | "`" | "{" | "}" | "~"
        // [Digit] ::=  "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
//        if (literal==' ' || Character.isLetter(literal) || isSymbol(literal) || Character.isDigit(literal)){
//            return true;
//        }
//        return false;
//    }
    //    public boolean isSymbol(char literal){
//        List<Character> symbol = Arrays.asList('!','#','$','%','&','(',')','*','+',',','-','.','/',':',';','>','=','<','?','@','[');
//        symbol.add('!');
//        symbol.add('!');
//        symbol.add('!');
//        symbol.add('!');
//
//    }
}
