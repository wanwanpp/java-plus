package com.wp;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class ClassTransform implements ClassFileTransformer {

    private Instrumentation inst;

    protected ClassTransform(Instrumentation inst) {
        this.inst = inst;
    }
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        HotAgent.clsnames.add(className);
        return null;
    }
}