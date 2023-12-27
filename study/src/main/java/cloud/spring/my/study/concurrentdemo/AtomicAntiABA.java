package cloud.spring.my.study.concurrentdemo;

import cloud.spring.my.study.domain.Car;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicAntiABA {


    public static void main(String[] args) {

        AtomicReference<Car> atomicReference = new AtomicReference();
//        AtomicMarkableReference
    }
}
