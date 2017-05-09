public class PowerTransformerEnd extends Substation {

	double r;
	double x;
	String transformer_rdf;
	String baseVoltage_rdf;
	
	public PowerTransformerEnd() {	
	}
	
	public PowerTransformerEnd(String rdf,String Name, double R, double X, String t, String b) {
		//shortcut
		rdfID = rdf;
		name = Name;
		r=R;
		x=X;
		transformer_rdf=t;
		baseVoltage_rdf=b;
	}

}