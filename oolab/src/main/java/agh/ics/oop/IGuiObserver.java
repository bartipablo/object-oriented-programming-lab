package agh.ics.oop;

import java.io.FileNotFoundException;

public interface IGuiObserver {
    void positionChanged() throws InterruptedException, FileNotFoundException;
}
