package com.xmhzj.xpForwardSmsMini.xp.hook;

public abstract class BaseSubHook {

    protected ClassLoader mClassLoader;

    public BaseSubHook(ClassLoader classLoader) {
        mClassLoader = classLoader;
    }

    public abstract void startHook();

}
