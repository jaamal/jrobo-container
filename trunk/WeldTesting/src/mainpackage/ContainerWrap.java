package mainpackage;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class ContainerWrap {
	@Inject
	@Any
	public Instance<Object> instance;
}
