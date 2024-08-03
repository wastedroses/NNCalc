import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Model class.
 *
 * @author Put your name here
 */
public final class NNCalcModel1 implements NNCalcModel {

    /**
     * Model variables.
     */
    private final NaturalNumber top, bottom;

    /**
     * No argument constructor.
     */
    public NNCalcModel1() {
        this.top = new NaturalNumber2();
        this.bottom = new NaturalNumber2();
    }

    @Override
    //returning text at top
    public NaturalNumber top() {

        return this.top;

    }

    /**
     * Reports this.bottom.
     *
     * @return this.bottom
     * @aliases reference returned by {@code bottom}
     * @ensures <pre>
     * {@code bottom = this.bottom}
     * </pre>
     */
    @Override
    //returning text in the bottom
    public NaturalNumber bottom() {

        return this.bottom;

    }

}
