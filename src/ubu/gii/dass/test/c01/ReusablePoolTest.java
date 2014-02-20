package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.Test;

import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

public class ReusablePoolTest {

	@Test
	public void testGetInstance()
	{
		ReusablePool rp1 = ReusablePool.getInstance();
		ReusablePool rp2 = ReusablePool.getInstance();
		assertSame("Instancias iguales patron de diseño Singleton", rp1,rp2);
		assertNotNull("Instancia nula ReusablePool", rp1);
	}

	@Test
	public void testAcquireReusable()
	{
		ReusablePool rp1 = ReusablePool.getInstance();
		Reusable r1 = null;
		Reusable r2 = null;
		try {
			r1 = rp1.acquireReusable();
			r2 = rp1.acquireReusable();
			
			assertNotSame("El pool contiene elementos duplicados", r1, r2);
		} catch (NotFreeInstanceException e) {
			fail("El pool no contiene el numero de instancias necesarias. ¿Se ha sacado alguna instancia antes?");
		}
		
		rp1.releaseReusable(new Reusable());
		rp1.releaseReusable(new Reusable());
		rp1.releaseReusable(new Reusable());
		
		try {
			rp1.acquireReusable().util();
			rp1.acquireReusable().util();
			rp1.acquireReusable().util();
			fail("Hay instancias de mas.");
		} catch (NotFreeInstanceException e) {
			
		}
		
	}

	@Test
	public void testReleaseReusable() {
		fail("Not yet implemented");
	}

}
