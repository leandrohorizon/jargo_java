package jargo.pkg4.pkg0;

import java.util.ArrayList;

public class StringMath {
    
    public double Calcular(String entrada){
        int i = 0;
        System.out.println(entrada);
        while(entrada.contains("(") && entrada.contains(")")){
            entrada = parentese(entrada);
            if(i>=3)
                break;
            else
                i++;
        }
        
        while(entrada.contains("*")){
            entrada = Multiplicar(entrada);
            if(i>=100)
                break;
            else
                i++;
            System.out.println(entrada);
        }
        
        while(entrada.contains("/")){
            entrada = Dividir(entrada);
            if(i>=100)
                break;
            else
                i++;
        }
        return somar(entrada);
    }

    public static Double somar(String entrada){
        String numPositivo = "";
        String numNegativo = "";

        boolean isPositive = entrada.matches("^\\d.*");
        
        int cont = 0;
        for(int i = 0; i < entrada.length(); i++){
            if(entrada.substring(i,i+1).equals("+")){
               isPositive = true; 
            }
            if(entrada.substring(i,i+1).equals("-")){
               isPositive = false; 
            }
            if(isPositive){
                numPositivo += entrada.substring(i,i+1);
            }else{
                numNegativo += entrada.substring(i,i+1);
            }
        }
        
        String[] p = numPositivo.split("\\+");
        String[] n = numNegativo.split("-");
        
        double total = 0;      
        
        for (String n1 : p) {
            if (!n1.equals("")) {
                total += Double.valueOf(n1);
            }
        }
        
        for (String n1 : n) {
            if (!n1.equals("")) {
                total -= Double.valueOf(n1);
            }
        }
        return total;
    }
    
    public static String Multiplicar(String entrada){

        int cont = 0;
        String numM = "";
        boolean segundoN = false;
        ArrayList<Double> m = new ArrayList<>();
        String substitui = "";
        
         for(int i = 0; i < entrada.length(); i++){
            if(m.size()<2 && i == entrada.length()-1){
                m.add(Double.valueOf(numM+entrada.substring(i,i+1)));
                substitui += "\\*"+numM+entrada.substring(i,i+1);
            }
            if(segundoN){
                if(entrada.substring(i,i+1).matches("[+,-,/,*]")){
                    m.add(Double.valueOf(numM));
                    substitui += "\\*"+numM;
                    break;
                }
            }
            switch (entrada.substring(i,i+1)) {
                case "*":
                    m.add(Double.valueOf(numM));
                    substitui += numM;
                    segundoN = true;
                    numM = "";
                    break;
                case "+":
                    numM = "";
                    break;
                case "/":
                    numM = "";
                    break;
                case "-":
                    numM = "";
                    break;
                default:
                    numM += entrada.substring(i,i+1);
                    break;
            }
         }
            double total = 1;
         for(Double m1 : m)
             total *= m1;
         
         return entrada.replaceAll(substitui, String.valueOf(total));
    }

    private static String Dividir(String entrada) {
        int cont = 0;
        String numM = "";
        boolean segundoN = false;
        ArrayList<Double> m = new ArrayList<>();
        String substitui = "";
        
         for(int i = 0; i < entrada.length(); i++){
            if(m.size()<2 && i == entrada.length()-1){
                m.add(Double.valueOf(numM+entrada.substring(i,i+1)));
                substitui += "/"+numM+entrada.substring(i,i+1);
            }
            if(segundoN){
                if(entrada.substring(i,i+1).matches("[+,-,/,*]")){
                    m.add(Double.valueOf(numM));
                    substitui += "/"+numM;
                    break;
                }
            }
            switch (entrada.substring(i,i+1)) {
                case "/":
                    m.add(Double.valueOf(numM));
                    substitui += numM;
                    segundoN = true;
                    numM = "";
                    break;
                case "+":
                    numM = "";
                    break;
                case "*":
                    numM = "";
                    break;
                case "-":
                    numM = "";
                    break;
                default:
                    numM += entrada.substring(i,i+1);
                    break;
            }
         }
        double total = m.get(0)/m.get(1);
        return entrada.replaceAll(substitui, String.valueOf(total)); 
    }
    
    private String parentese(String entrada){
        String pegar = entrada;
        int l = 0;
        
        int t = 0;
        
        int in = 0;
        
        if(entrada.contains("(")){
            for(int i = 0; i < entrada.length(); i++){
                if(entrada.substring(i,i+1).equals("(")){
                    l++;
                }
                if(entrada.substring(i,i+1).equals(")")){
                    l--;
                }
                if(l>t){
                    t = l;
                    in = i;
                }
                if(entrada.substring(i,i+1).equals(")")){
                    pegar = entrada.substring(in+1, i);
                    break;
                }
            }
        }
        String sub = pegar;
        sub = "\\("+sub.replace("+", "\\+").replace("*", "\\*")+"\\)";
        
        return entrada.replaceAll(sub,String.valueOf(Calcular(pegar)));
    }
    
    public static void main(String[] args){
        String m = new Formatar().Matematica("10+12/3+3");
//        if((m.contains("(") && m.contains(")") && m.matches(".*\\d+"))||m.matches("\\d+[/*-+]+\\d+")){
        if(m.matches(".*\\d+[/*+-]\\d+.*")){
            String n = String.valueOf(new StringMath().Calcular(m));
            if(n.endsWith(".0")){
                n = n.replace(".0", "");
            }
        System.out.println(n);
//        System.out.println(10+12/3+3);
        }
    }
}