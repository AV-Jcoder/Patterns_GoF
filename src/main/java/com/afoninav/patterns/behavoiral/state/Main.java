package com.afoninav.patterns.behavoiral.state;

/**
 * Пример шаблона State(Состояние)
 */
public class Main {
    public static void main(String[] args) {

        Station station1 = new Radio7();
        Radio radio = new Radio();
        radio.play();
        radio.nextStation();
        radio.play();
        radio.nextStation();
        radio.play();

    }
}

/**
 * Состояние
 */
interface Station {
    void play();
}

class Radio7 implements Station {
    @Override
    public void play() {
        System.out.println("Radio 7 ...");
    }
}

class RadioDFM implements Station {
    @Override
    public void play() {
        System.out.println("Radio DFM ...");
    }
}

class VestiFM implements Station {
    @Override
    public void play() {
        System.out.println("Radio VestiFM ...");
    }
}

/**
 * Контекст
 */
class Radio {
    private Station station = new Radio7();

    public void setStation(Station station) {
        this.station = station;
    }

    public void nextStation() {
        if (station instanceof Radio7){
            setStation(new RadioDFM());
        } else if (station instanceof RadioDFM) {
            setStation(new VestiFM());
        } else if (station instanceof VestiFM) {
            setStation(new Radio7());
        }
    }

    public void play() {
        station.play();
    }
}















