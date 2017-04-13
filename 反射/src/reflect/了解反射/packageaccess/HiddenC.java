package reflect.了解反射.packageaccess;

import reflect.了解反射.interfacea.A;

class C implements A {
    public void f() {
        System.out.println("public C.f()");
    }

    public void g() {
        System.out.println("public C.g()");
    }

    protected void v() {
        System.out.println("protected C.v()");
    }

    void u() {
        System.out.println("package C.u()");
    }

    private void w() {
        System.out.println("private C.w()");
    }
}

public class HiddenC {
    public static A makeA() {
        return new C();
    }
}