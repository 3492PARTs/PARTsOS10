import frc.robot.subsystems.DriveSparkMax;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class DriveTrainTests {

    @Test
    public  void move(){

        DriveSparkMax driveSparkMax = (DriveSparkMax) mock(DriveSparkMax.class);

            double[] expected = {1,1};
            driveSparkMax.move(1.0,1.0);

            assertEquals( driveSparkMax.getSpeeds()[0],expected[0],.01);

            assertEquals( driveSparkMax.getSpeeds()[1],expected[1],.01);
    }

    @Test
    public void adding(){
        assertEquals(1,1,112);
    }
    
}
