
import org.junit.Test;
import testClasses.DriveSparkMax;

import  org.junit.Assert.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class DriveTrainTests {

    @Test
    public  void move(){

        DriveSparkMax driveSparkMax = new DriveSparkMax();

            double[] expected = {1.0,1.0};
            driveSparkMax.move(1.0,1.0);
            double[] speeds = driveSparkMax.getSpeeds();


            assertEquals(expected[0], speeds[0],.02);

            assertEquals(expected[1], speeds[1], 02);
    }

    @Test
    public void stop(){
        DriveSparkMax driveSparkMax = new DriveSparkMax();
        for (double i = 0; i < 1.01 ; i+=.01) {

            driveSparkMax.move(i,i);

            driveSparkMax.stop();

            assertEquals(0.0, driveSparkMax.getSpeeds()[0], 0);
            assertEquals(0.0, driveSparkMax.getSpeeds()[1], 0);

        }
    }
    
}
