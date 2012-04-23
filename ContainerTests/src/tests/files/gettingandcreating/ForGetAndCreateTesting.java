package tests.files.gettingandcreating;

public class ForGetAndCreateTesting implements IForGetAndCreateTesting {

	private int value;

	public ForGetAndCreateTesting() {
		value = 0;
	}

	@Override
	public int inc() {
		return ++value;
	}

}
