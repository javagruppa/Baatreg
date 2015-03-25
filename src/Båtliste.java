import java.io.*;

import javax.swing.JTextArea;

public class Båtliste implements Serializable
	{
	private Båt første;

	public Båtliste()
	{
		første = null;
	}




	public Båt getFørste()
	{
		return første;
	}

	public Båt getBåt(int regnr)
	{
		if (første == null)
		return null;

		Båt denne = første;
		while (denne != null && denne.getRegNr() != regnr)
		{
			denne = denne.neste;
		}
		return denne;
	}



	public void settInn ( Båt ny )
	{
		if (ny == null)
		return;

		if (første == null)
		{
			første = ny;
			return;
		}
		else
		{
			Båt løper = første;
			while (løper.neste != null)
			{
				løper = løper.neste;
			}

			løper.neste = ny;
		}
	}//end settinn


public Båt finnBåt(int regnr)
{
	if (første == null)
	return null;

	Båt løper = første;
	while (løper != null)
	{
		if (løper.getRegNr() == regnr)
		{
			return løper;
		}
		løper = løper.neste;

	}//end while

	return null;
}//end finnbåt


public String toString()
{
	String tekst = "";
	Båt denne = første;

	while (denne != null)
	{
	      tekst += denne.toString() + "\n\n";
	      denne = denne.neste;
	}

	    if (!tekst.equals("")) {
	      return tekst;
	    }

	    else {
	      return "Listen er tom";
    }

}//end tostring



public String slettBåt(int regnr)
{
	if (første == null)
	return "Det er ingen båter i registeret";

	while (første != null && første.getRegNr() != regnr)
	{
		første = første.neste;
	}
	første = første.neste;
	return "Båten er slettet";


}//end slettbåt














}//end båtliste