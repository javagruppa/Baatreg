import java.io.*;

public class Båt implements Serializable
{
	private int regNr = 0;
	private String merke;
	private int modell;
	private int lengde;
	private int HK;
	private String farge;
	public static int nesteRnr = 100;
	Båt neste = null;

	public Båt( String m, int mod, int l, int hk, String f)
	{
		regNr = nesteRnr++;
		merke = m;
		modell = mod;
		lengde = l;
		HK = hk;
		farge = f;
	}

	public Båt()
	{}

public String getMerke()
{
	return merke;
}

public int getModell()
{
	return modell;
}

public String getFarge()
{
	return farge;
}

public int getHK()
{
	return HK;
}

public int getLengde()
{
	return lengde;
}

public int getRegNr()
{
	return regNr;
}

public String toString()
{
	String ts = "Regnr: " + regNr + "\nBåtmerke: " + merke + "\nÅrsmodell: " + modell + "\nAntall fot: " + lengde + "\nMotorstørrelse: " + HK + "\nFarge: " + farge;
	return ts;
}


}//end of class Båt.java