
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.hal.HAL;
import frc.robot.subsystems.DriveSparkMax;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;


import  org.junit.Assert.*;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DriveTrainTests {



    CANSparkMax Right1Mock = mock(CANSparkMax.class);
    CANSparkMax Right2Mock = mock(CANSparkMax.class);
    CANSparkMax Right3Mock = mock(CANSparkMax.class);

    CANSparkMax Left1Mock = mock(CANSparkMax.class);
    CANSparkMax Left2Mock = mock(CANSparkMax.class);
    CANSparkMax Left3Mock = mock(CANSparkMax.class);



    DriveSparkMax driveSparkMax = new DriveSparkMax(Right1Mock, Right2Mock, Right3Mock, Left1Mock, Left2Mock, Left3Mock);



    @Test
    public  void move(){



            double[] expected = {.5, .5};


            when(driveSparkMax.getLeft1SparkMax().get()).thenReturn(.5);


            driveSparkMax.move(.5,.5);

            assertEquals(expected[0], driveSparkMax.getLeft1SparkMax().get(), .001);


        Right1Mock.close();
        Right2Mock.close();
        Right3Mock.close();

        Left1Mock.close();
        Left2Mock.close();
        Left3Mock.close();
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
