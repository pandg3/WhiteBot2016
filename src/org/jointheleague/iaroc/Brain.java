package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;
import org.wintrisstech.irobot.ioio.IRobotCreateAdapter;
import org.wintrisstech.irobot.ioio.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

public class Brain extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;
    int As;
    int An;

    public Brain(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
    }

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Hello! I'm a Clever Robot!");
        //what would you like me to do, Clever Human?





    }
    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException {
        readSensors(0);
        int aS = getDistance();
        As+=aS;
        int aN = getAngle();
        An += aN;
        dashboard.log("Total Distance= " + As + "Total Angle= " + An);
       int INF = getInfraredByte();
        dashboard.log("Sensor = " +INF);
        if (INF == 244){
            driveDirect(200,200);}
        else if (isBumpLeft() == true || isBumpRight() == true){
            driveDirect(-20, -20);
            SystemClock.sleep(500);
            driveDirect(350, -350);
            SystemClock.sleep(750);

            driveDirect(0,0);
        }
    }
    public void bump() throws ConnectionLostException{
        driveDirect(0, 0);
        SystemClock.sleep(500);
        driveDirect(-350, -350);
        SystemClock.sleep(1500);
        driveDirect(350, -350);
        SystemClock.sleep(750);
        driveDirect(0, 0);
    }
}