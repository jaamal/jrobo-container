package tests.files.oneimplementationtests;

/**
 * Created with IntelliJ IDEA.
 * User: mr146
 * Date: 19.05.12
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class ClassWithTwoInterfaces implements LowerInterface {
    public int value;

    public ClassWithTwoInterfaces() {
        value = 0;
    }

    public void increment() {
        value++;
    }
}
