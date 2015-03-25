import java.io.*;

public class Eier implements Serializable
{
	private String navn;
	private String adresse;
	private int mNr = 0;
	public static int nestenr = 100;

	Eier neste = null;
	public Båtliste båtliste;
	Båt første;

	public Eier (String n, String a)
	{
		navn = n;
		adresse = a;
		mNr = nestenr++;
		båtliste = new Båtliste();
	}

	public void finnFørste()
	{
		første = båtliste.getFørste();
	}

	public Eier()
	{}

	public int getmNr ()
	{
		return mNr;
	}

	public String getNrString()
	{
	String MedNr = Integer.toString(mNr);
	return MedNr;
	}

	public void setBåt(Båt båt)
	{
		båtliste.settInn(båt);
	}

	public String getNavn ()
	{
		return navn;
	}

	public String getAdresse ()
	{
		return adresse;
	}

  public Båt finnBåt (int regnr)
  {
		return båtliste.getBåt (regnr);
	}

	public String toStringAlt()
	{

		System.out.println("inne i tostringalt");
		String sa = "Navn: " + navn + "\n" + "Medlemsnr: " + mNr + "\n" + "Adresse: " + adresse + "\n\nBåter eid av denne personen:\n" + båtliste.toString() +"\n\n";
		return sa;
	}

	public String toString()
	{
		if (båtliste.getFørste() == null)
		{String s = "Navn: " + navn + "\n" + "Medlemsnr: " + mNr + "\n" + "Adresse: " + adresse;
		return s;
	}
	else
	{
		String s = "Navn: " + navn + "\n" + "Medlemsnr: " + mNr + "\n" + "Adresse: " + adresse + "\n\nBåter eid av denne personen:\n" + båtliste.toString() +"\n\n";
return s;
		}

	}

	public String slettBåt(int regnr)
	{
		System.out.println("inne i slettbåt i eier");
		String s = båtliste.slettBåt(regnr);
		return s;
	}//end slettbåt



}//end og class Eier.java