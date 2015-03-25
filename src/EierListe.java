

import java.io.Serializable;
import javax.swing.*;


public class EierListe implements Serializable {

  private Eier første;

  public EierListe()
  {
    første = null;
  }

	public Eier getFørste()
  {
		return første;
	}


  public void leggTilEier(Eier eier) {

    if (første == null)
    {
      første = eier;
    }

    else {
      Eier denne = første;
      while (denne.neste != null)
      {
        denne = denne.neste;
      }
      denne.neste = eier;
     }

  }

public void nestebåtnr() //metoden sørger for at nestenr i klassen Båt for verdi som er en høyere enn forrige registrerte båt
{
	int nr = 100;

	while (finnBåteier(nr) != null)
	{
		nr++;
	}
	Båt.nesteRnr = nr;

}


public void nestenummeret()
  {
		if (første == null)
		{
			int nestenummer = 100;
			Eier.nestenr = nestenummer;
		}
		else
		{
			Eier denne = første;
			while (denne.neste != null)
			{
				denne = denne.neste;
			}//end while
			int nestenummer = denne.getmNr() + 1;
			Eier.nestenr = nestenummer;

		}//end else



	}//end nestenr



		public Eier finnBåteier(int regnr)
		{
			Eier løper = første;


			while (løper != null && løper.båtliste.getBåt(regnr) == null)
			{
				løper = løper.neste;
			}
			System.out.println(løper);
			return løper;

	}//end finnbåteier


  public String skrivRegister()
  {
    String tekst = "";
    Eier denne = første;

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

  }


  public boolean fjernEier(int mNr)
  {
    if (første == null)
    return false;

    if (første.getmNr() == mNr)
    {
    første = første.neste;
    return true;
    }

    Eier denne = første;
    while(denne != null && denne.neste != null && denne.neste.getmNr() != mNr)
    {
      if (denne.neste.neste == null)
      return false;
      denne = denne.neste;
    }
    if(denne.neste != null && denne.neste.båtliste == null)
    {
      denne.neste = denne.neste.neste;
      return true;
    }
    return false;
  }

	public Eier sokEier(int m)
	{
		if (første == null)
		return null;

		Eier denne = første;
		while(denne != null && denne.getmNr() != m)
		{
			denne = denne.neste;
		}
		return denne;
	}//end sokeier


	public Eier finn(int mNr)
	{
    if (første == null)
    return null;

    Eier denne = første;
    while(denne != null && denne.getmNr() != mNr)
    {
      if (denne.neste == null)
      return null;
      denne = denne.neste;
    }
    return denne;
  }


}//End og class EierListe.java