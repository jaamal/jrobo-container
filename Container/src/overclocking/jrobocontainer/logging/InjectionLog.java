package overclocking.jrobocontainer.logging;

import overclocking.jrobocontainer.storages.ClassNode;

public class InjectionLog extends Log implements IInjectionLog {

    public InjectionLog()
    {
        super();
    }

    @Override
    public void beginGet(ClassNode clazz) {
        begin("Getting " + clazz.getClassName());
    }

    @Override
    public void endGet(ClassNode clazz) {
        end("End getting " + clazz.getClassName());
    }

    @Override
    public void beginCreate(ClassNode clazz) {
        begin("Creating " + clazz.getClassName());
    }

    @Override
    public void endCreate(ClassNode clazz) {
        end("End creating " + clazz.getClassName());
    }

    @Override
    public void beginGetAll(ClassNode clazz) {
        begin("Getting all " + clazz.getClassName());
    }

    @Override
    public void endGetAll(ClassNode clazz) {
        end("End getting all " + clazz.getClassName());
    }

    @Override
    public void reuse(ClassNode clazz) {
        append("Reusing " + clazz.getClassName());
    }
}
