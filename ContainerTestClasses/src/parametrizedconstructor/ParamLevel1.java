package parametrizedconstructor;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class ParamLevel1 implements IParamLevel1 {
    public IParamLevel2A paramLevel2A;
    public IParamLevel2B paramLevel2B;

    public ParamLevel1(IParamLevel2A paramLevel2A, IParamLevel2B paramLevel2B) {
        this.paramLevel2A = paramLevel2A;
        this.paramLevel2B = paramLevel2B;
    }
}
