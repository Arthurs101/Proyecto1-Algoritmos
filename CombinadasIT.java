import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 *
 * @author imarg
 */
public class CombinadasIT {
             @Test
public void testEvaluate() {
   Combinadas combinada1=new Combinadas();
    Double resultado=(Double) combinada1.evaluate((List)combinada1.conver("(- ( * 1  2 3  4 ) 3 )"));
    Double expresut = 21.00;
    assertEquals(expresut, resultado);
}
             @Test
public void testEvaluate1() {
   Combinadas combinada1=new Combinadas();
    Double resultado=(Double) combinada1.evaluate((List)combinada1.conver("(* ( * 1  2 3  4  5) 3 )"));
    Double expresut = 360.00;
    assertEquals(expresut, resultado);
}
             @Test
public void testEvaluate2() {
   Combinadas combinada1=new Combinadas();
    Double resultado=(Double) combinada1.evaluate((List)combinada1.conver("(+ ( * 1  2 3  4 ) 3 )"));
    Double expresut = 27.00;
    assertEquals(expresut, resultado);
}
             @Test
public void testEvaluate3() {
   Combinadas combinada1=new Combinadas();
    Double resultado=(Double) combinada1.evaluate((List)combinada1.conver("(/ ( * 1  2 3  4 ) 2 )"));
    Double expresut = 12.00;
    assertEquals(expresut, resultado);
}             @Test
public void testEvaluate4() {
   Combinadas combinada1=new Combinadas();
    Double resultado=(Double) combinada1.evaluate((List)combinada1.conver("(- ( *  3  4 ) 3 )"));
    Double expresut = 9.00;
    assertEquals(expresut, resultado);
}



}
