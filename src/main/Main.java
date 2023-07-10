package main;

import Frames.MenuFrame;

//javac -Xlint -cp "./src/Libraries\log4j-core-2.20.0.jar;./src/Libraries/jdatepicker-1.3.4.jar;./src/Libraries/poi-bin-5.2.3-20220909/poi-bin-5.2.3/poi-5.2.3.jar;./src/Libraries/poi-bin-5.2.3-20220909/poi-bin-5.2.3/poi-ooxml-5.2.3.jar" ./src/main/* ./src/Panels/* ./src/Listeners/* ./src/Frames/* ./src/IOFormatters/* ./src/Data/*
//javadoc -d .\src\Documentazione -cp "./src/Libraries\log4j-core-2.20.0.jar;./src/Libraries/jdatepicker-1.3.4.jar;./src/Libraries/poi-bin-5.2.3-20220909/poi-bin-5.2.3/poi-5.2.3.jar;./src/Libraries/poi-bin-5.2.3-20220909/poi-bin-5.2.3/poi-ooxml-5.2.3.jar" .\src\Data\*.java .\src\Frames\*.java .\src\IOFormatters\*.java .\src\Listeners\*.java .\src\main\*.java .\src\Panels\*.java

/**
 * Classe Main del progetto
 * @author Christofer Fanò
 */
public class Main {
    public static void main(String[] args) {
        MenuFrame f = new MenuFrame("Gestione bilancio");

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}