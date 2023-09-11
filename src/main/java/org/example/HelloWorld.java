package org.example;
import java.util.ArrayList;

class HelloWorld {
    private ArrayList<Printable> list2print = new ArrayList<>();

    interface Printable {
        void print();
    }

    public HelloWorld(Printable p) {
        list2print.add(p);
    }

    public void add(Printable p) {
        list2print.add(p);
    }

    public void print() {
        for (Printable p : list2print) {
            p.print();
        }
    }

    public static void main(String[] args) {
        HelloWorld hw = new HelloWorld(()->System.out.println("First Line"));

        /*hw.add(new Printable() {
            public void print() {
                System.out.println("Hello World!");
            }
        });*/

        hw.add(()-> System.out.println("Hello World!"));
        hw.add(()-> System.out.println("und noch einmal... HELLLLLLOOOOO WOOOOORLD!!!!"));
        hw.print();
    }
}