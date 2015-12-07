package buttonhero;

/**
 * Created by William on 12/5/2015.
 */
public class HitObject {
    private MixedNumber beat;
    private MixedNumber holdDuration;

    public HitObject(int beatWhole, int beatNumerator, int beatDenominator, String holdDuration) {
        beat = new MixedNumber(beatWhole, beatNumerator, beatDenominator);
        this.holdDuration = new MixedNumber(holdDuration);
    }

    public HitObject(int beatWhole, int beatNumerator, int beatDenominator) {
        this(beatWhole, beatNumerator, beatDenominator, "0");
    }
}
