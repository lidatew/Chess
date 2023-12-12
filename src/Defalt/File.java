package Defalt;

//вертикаль(снизу)
public enum File {
    A, B, C, D, E, F,G, H;

    // из чара в файл
    public static  File fromChar(char c) {
        try {
            return File.valueOf(String.valueOf(c).toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
