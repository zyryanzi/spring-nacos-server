package cloud.spring.my.study.algorithm;

public class IntToIp {

    private final int N = 4;

    public IntToIp() {
    }

    public String convert(String str) {
        // ipv4 -> int
        if (str.contains(".")) {
            String[] fields = str.split("\\.");
            long result = 0;
            for (int i = 0; i < N; i++) {
                result = result * 256 + Integer.parseInt(fields[i]);
            }
            return "" + result;
        }
        // int -> ipv4
//        else {
//            long ipv4 = Long.parseLong(str);
//            String result = "";
//            for (int i = 0; i < N; i++) {
//                result = ipv4 % 256 + "." + result;
//                ipv4 /= 256;
//            }
//            return result.substring(0, result.length() - 1);
//        }
        return "";
    }

    public static void main(String[] args) {
        IntToIp solution = new IntToIp();
        System.out.println(solution.convert("10.123.23.21"));
    }

}
