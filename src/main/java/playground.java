import java.awt.*;

public class playground {
    public static void main(String[] args) {

        Color t = new Color(130,230,30) {
            @Override
            public String toString() {
                return "łokurwategomsieniespodziewał";
            }
        };

        System.out.println(t.toString());
        float[] table = new float[3];
        table = Color.RGBtoHSB(t.getRed(), t.getGreen(), t.getBlue(), table);


    }
}
