package teste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LexicalMessageTest.class, SemanticTest.class,
		SyntaticalMessageTest.class })
public class AllTests {

}
