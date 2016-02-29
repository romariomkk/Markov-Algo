package main;


public class neWoNE {

    static int tabIndex=0;
    
    private void print(String string, int tabIndex){
        for (int i = 0; i < tabIndex; i++)
            System.out.print("     ");
        System.out.println(string);
    }
    
    private void activizeMarkov(){
//        System.out.println("rwrqeqerw");
//        formatString("rwrqeqerw");
        System.out.println("abcdef\n");
        formatString("abcdef");
    }
    
    private void formatString(String format){
        
        String helper;
        
        if (format.contains("ab")){
            //System.out.println(format);
            helper = format.replace("ab","a");
            print(helper, ++tabIndex);
            formatString(helper); 
            tabIndex--;
        }else{
            //tabIndex--;
        }
        
        if (format.contains("cd")){
            //System.out.println(format);
            helper = format.replace("cd","c");
            print(helper, ++tabIndex);
            formatString(helper);
            //tabIndex=1;
            tabIndex--;
        }else{
            //tabIndex--;
        }
        
        if (format.contains("ef")){
            //System.out.println(format);
            helper = format.replace("ef","e");
            print(helper, ++tabIndex);
            formatString(helper);
            //tabIndex=1;
            tabIndex--;
        }else{
            //tabIndex--;
        }
        
        if (format.contains("bc")){
            //System.out.println(format);
            helper = format.replace("bc","b");
            print(helper, ++tabIndex);
            formatString(helper);
           // tabIndex=1;
           tabIndex--;
        }else{
            //tabIndex--;
        }
        
        if (format.contains("de")){
            //System.out.println(format);
            helper = format.replace("de","d");
            print(helper, ++tabIndex);
            formatString(helper);
            //tabIndex=1;
        tabIndex--;
        }else{
            //tabIndex--;
        }
        if (format.contains("af")){
            //System.out.println(format);
            helper = format.replace("af","a");
            print(helper, ++tabIndex);
            formatString(helper);
            //tabIndex=1;
            tabIndex--;
        }else{
            //abIndex--;
        }
    }
    public static void main(String[] args) {
        neWoNE one = new neWoNE();
        one.activizeMarkov();
    }
            
}
