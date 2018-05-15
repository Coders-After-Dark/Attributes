package codersafterdark.attributes.utils;

public class AttributesUtilMethod {
    public static int nextIntInclusive(int min, int max) {
        return AttributesConstants.RANDOM.nextInt(max - min + 1) + min;
    }
}
