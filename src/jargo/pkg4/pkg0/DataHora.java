/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jargo.pkg4.pkg0;

import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author User
 */
public class DataHora {
    public String DT(String DT){
//        String DT = null;
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Brazil/East"));
        if(DT.contains("!segundos"))
            DT = DT.replaceAll("!segundos", Integer.toString(calendar.get(Calendar.SECOND)));
        if(DT.contains("!ano"))
            DT = DT.replaceAll("!ano", Integer.toString(calendar.get(Calendar.YEAR)));
        if(DT.contains("!dia")){
            if(calendar.get(Calendar.DAY_OF_MONTH)<10){
                DT = DT.replaceAll("!dia", "0"+Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
            }else{
                DT = DT.replaceAll("!dia", Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
            }   
        }
        if(DT.contains("!amanha")){
            if(calendar.get(Calendar.DAY_OF_MONTH)+1<10){
                DT = DT.replaceAll("!amanha", "0"+Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)+1));
            }else{
                DT = DT.replaceAll("!amanha", Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)+1));
            }   
        }
        if(DT.contains("!ontem")){
            if(calendar.get(Calendar.DAY_OF_MONTH)-1<10){
                DT = DT.replaceAll("!ontem", "0"+Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)-1));
            }else{
                DT = DT.replaceAll("!ontem", Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)-1));
            }   
        }
        if(DT.contains("!horas")){
            
            String fm = "";
            String fh = "";
            if(calendar.get(Calendar.HOUR_OF_DAY)<10)
                fh = "0"+Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
            else
                fh = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
            if(calendar.get(Calendar.MINUTE)<10)
                fm = "0"+Integer.toString(calendar.get(Calendar.MINUTE));
            else
                fm = Integer.toString(calendar.get(Calendar.MINUTE));

            DT = DT.replaceAll("!horas", fh +":"
                    + fm);
        }
        
        if(DT.contains("!mesn")){
            if(calendar.get(Calendar.MONTH)+1<10){
                DT = DT.replaceAll("!mesn", "0"+Integer.toString(calendar.get(Calendar.MONTH)+1));
            }else{
                DT = DT.replaceAll("!mesn", Integer.toString(calendar.get(Calendar.MONTH)+1));
            }
        }
        
        if(DT.contains("!mes")){
            switch (calendar.get(Calendar.MONTH)+1){
                case 1:
                    DT = DT.replaceAll("!mes", "Janeiro");
                    break;
                case 2:
                    DT = DT.replaceAll("!mes", "Fevereiro");
                    break;
                case 3:
                    DT = DT.replaceAll("!mes", "Março");
                    break;
                case 4:
                    DT = DT.replaceAll("!mes", "Abril");
                    break;
                case 5:
                    DT = DT.replaceAll("!mes", "Maio");
                    break;
                case 6:
                    DT = DT.replaceAll("!mes", "Junho");
                    break;
                case 7:
                    DT = DT.replaceAll("!mes", "Julho");
                    break;
                case 8:
                    DT = DT.replaceAll("!mes", "Agosto");
                    break;
                case 9:
                    DT = DT.replaceAll("!mes", "Setembro");
                    break;
                case 10:
                    DT = DT.replaceAll("!mes", "Outubro");
                    break;
                case 11:
                    DT = DT.replaceAll("!mes", "Novembro");
                    break;
                case 12:
                    DT = DT.replaceAll("!mes", "Dezembro");
                    break;
                
            }
//                        DT = Integer.toString(calendar.get(Calendar.MONTH)+1);
        }
        if(DT.contains("!dia da semana")){
            switch (calendar.get(Calendar.DAY_OF_WEEK)+1){
                case 1:
                    DT = DT.replaceAll("!dia da semana", "Sábado");
                    break;
                case 2:
                    DT = DT.replaceAll("!dia da semana", "Domingo");
                    break;
                case 3:
                    DT = DT.replaceAll("!dia da semana", "Segunda");
                    break;
                case 4:
                    DT = DT.replaceAll("!dia da semana", "Terça");
                    break;
                case 5:
                    DT = DT.replaceAll("!dia da semana", "Quarta");
                    break;
                case 6:
                    DT = DT.replaceAll("!dia da semana", "Quinta");
                    break;
                case 7:
                    DT = DT.replaceAll("!dia da semana", "Sexta");
                    break;
                }
//            DT = Integer.toString(calendar.get(Calendar.DAY_OF_WEEK));
            }
        
        
            return DT;
    }
}
