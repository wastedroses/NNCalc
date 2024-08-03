import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Put your name here
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        //declaring variables
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber two = new NaturalNumber2(2);
        view.updateBottomDisplay(bottom);
        boolean allowed = false;
        /*
         * Update view to reflect changes in model
         */
        //if bottom > 0, can be divisible.
        if (bottom.compareTo(zero) > 0) {
            allowed = true;
            view.updateDivideAllowed(allowed);
        }
        //if number is greater / equal to zero then the sqrt can be found.
        if (bottom.compareTo(two) >= 0) {
            allowed = true;
            view.updateRootAllowed(allowed);
        }
        //if the bottom is less than the top, can be subtracted.
        if (bottom.compareTo(top) < 0) {
            allowed = true;
            view.updateSubtractAllowed(allowed);
        }
        //if number doesn't cause stack overflow error, then can be used.
        if (bottom.compareTo(INT_LIMIT) < 1) {
            allowed = true;
            view.updatePowerAllowed(allowed);
        }
        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    //lets user put number at top
    public void processEnterEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        top.copyFrom(bottom);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    //allows user to add number
    public void processAddEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        top.add(bottom);
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    //allows user to subtract number without negative numbers.
    public void processSubtractEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        bottom.subtract(top);
        top.transferFrom(bottom);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    //allows user to multiply numbers.
    public void processMultiplyEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        bottom.multiply(top);
        top.transferFrom(bottom);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    //allows user to divide numbers, with the remainder being the answer
    //the calculator puts out.
    public void processDivideEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        NaturalNumber remainder = top.divide(bottom);
        top.transferFrom(bottom);
        top.copyFrom(remainder);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    //allows user to get the power of a number. I used to int because integer is
    //required in .power contract.
    public void processPowerEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        int powerTo = bottom.toInt();
        top.power(powerTo);
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    //allows user to find the square root of a number. I used to int because
    //integer is required in .root contract.
    public void processRootEvent() {

        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        int rootOf = bottom.toInt();
        bottom.transferFrom(top);
        top.root(rootOf);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        NaturalNumber bottom = this.model.bottom();
        bottom.multiplyBy10(digit);
        updateViewToMatchModel(this.model, this.view);

    }

}
