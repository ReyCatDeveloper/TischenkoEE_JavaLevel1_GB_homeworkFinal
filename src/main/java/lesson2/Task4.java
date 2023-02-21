package lesson2;

public class Task4 {
    public static void main(String[] args) {
        stringRepeat("Java - это интересно",4);
    }
    static void stringRepeat(String string, int repeat){
        for(int i = 0; i < repeat; i++){
            System.out.println(string);
        }
    }
}
