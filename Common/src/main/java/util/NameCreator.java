package util;

import po.MessagePO;
import po.UserPO;

import java.lang.reflect.Field;

public class NameCreator {


    public static String changeName(String arg){
        if(arg.equals("[B")){
            return "";
        }else if(arg.equals("java.time.LocalDate")||arg.equals("java.time.LocalDateTime")){
            return "bigint";
        }else if(arg.equals("java.lang.String")){
            return "varchar(100)";
        }else if(arg.equals("double")){
            return "double";
        }else if(arg.equals("int")){
            return "integer";
        }
        return "integer";
    }


    public static void getClassString(Class clazz){
        for(Field field:clazz.getDeclaredFields()){
            System.out.println(field.getName()+" "+changeName(field.getType().getName())+",");
        }
    }



    public static void main(String args[]){
        getClassString(MessagePO.class);
    }

}
