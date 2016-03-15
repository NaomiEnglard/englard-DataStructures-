package School;

public enum Major {
	ACCT ("Accounting"), ART ("ART"), BIOL ("Biology"), CHEM ("Chemistry"), CPSC ("Computer Science"),	ECON ("Economics"),
	EDUC ("Education"), ENGL ("English"), ENGR ("Engineering") ,FRCH ("French"), GEOG ("Geology"), GERM ("German"), 
	GREE ("Greek"), HIST ("HISTORY"), MATH ("MATH") , MUSC ("Music"), NURS ("Nursing"), PHIL ("Philosophy"),PE ("Physical Ed"),
	PHYS ("Physics"), POLS ("Political Science"), PSYC ("Psychology"), RELI ("Religion"), SOCI ("Sociology"), SPEE ("Speech") , 
	UDCD ("Undecided");
	
	private String nameOfMajor;
	private Major (String name){
		this.nameOfMajor = name;
	}
}	

