package matala1;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class JunitTest {

	@Test
	public void test() throws IOException{
		//shortestPath test
		WeighedDigraph test=new WeighedDigraph("mediumEWD");
		DijkstraFind test1=new DijkstraFind(test);
		double result=test1.shortestPath(2,202);
		assertEquals(result,2,202);
		
	
	}

}
