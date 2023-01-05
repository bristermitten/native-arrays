public class Main {

    public static void main(String[] args) throws InterruptedException {
        Array<String> strArr = new Array<>(3);
        strArr.set(0, "Hello");
        String s2 = strArr.get(0);
        System.out.println(s2);
    }
}
