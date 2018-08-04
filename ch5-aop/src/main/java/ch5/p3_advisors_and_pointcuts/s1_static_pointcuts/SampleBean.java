package ch5.p3_advisors_and_pointcuts.s1_static_pointcuts;

class SampleBean {

    void foo(int x) {
        System.out.println("[SampleBean] invoked foo with x = " + x);
    }

    void bar() {
        System.out.println("[SampleBean] invoked bar");
    }
}
