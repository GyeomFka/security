package modulestudy.sample.config;

public class ConventionTest {

	private void testOfConvention(String args, String argsP) {
		System.out.println(args + " :: " + argsP);
		if (args.equals("NOTHING")) {
			args = "THIS";
		}
	}
}
