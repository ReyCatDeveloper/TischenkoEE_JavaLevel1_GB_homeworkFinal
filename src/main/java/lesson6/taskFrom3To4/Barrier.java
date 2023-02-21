package lesson6.taskFrom3To4;

public class Barrier {
    public static class Type{
        public static Type waterBarrier = new Type();
        public static Type groundBarrier = new Type();
    }
    int distance;
    Type type;

    Barrier (Type type, int distance){
        this.type = type;
        this.distance = distance;
    }


}
