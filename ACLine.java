public class ACLine extends Substation {

	Double r;
	Double x;
	
	public ACLine() {	
	}
	
	public ACLine(String rdf,String Name, Double R, Double X) {
		//shortcut
		rdfID = rdf;
		name = Name;
		r=R;
		x=X;
		
	}

}