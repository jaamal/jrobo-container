package tests.files.gettingandcreating;

public class Incrementer implements IIncrementer {

	private int value;

	public Incrementer() {
		value = 0;
	}

	@Override
	public int inc() {
		return ++value;
	}

}
