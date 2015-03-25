import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Båtregister
{
	private Eier første;

	//registrerer et eierobjekt
	  public void settInnForrest( Eier ny )  // sortert
	  { if(ny == null) return;
	    ny.neste = første;
	    første = ny;
	  }

	    private void visFeilmelding(String melding)
		  {
		    JOptionPane.showMessageDialog(null, melding + "");
  }
	  public void settInn( Eier ny )
		{
	    if( ny == null ) return;

	    if( første == null ) // tom liste:
	    {
	      første = ny;
	      return;
	    }
						// objektet skal inn forrest:
	    if( ( ny.getNavn().compareToIgnoreCase( første.getNavn() ) == 0 &&
	          ny.getNrString().compareTo( første.getNrString() ) < 0 )
			 || ( ny.getAdresse().compareTo( første.getAdresse() ) < 0 ) )
	    {
		    settInnForrest( ny );
	      return;
	    }

	    Eier løper = første;
	    while( løper.neste != null )
	    {
	      if( ( ny.getNavn().compareTo(løper.neste.getNavn() ) == 0 &&
	            ny.getNrString().compareTo(løper.neste.getNrString() ) < 0 )
	       || ( ny.getAdresse().compareTo(løper.neste.getAdresse() ) < 0 ) )
	      {
	        ny.neste = løper.neste;
	        løper.neste = ny;
						  return;
	      }
	      else
	        løper = løper.neste;
	    }
	   // setter inn eieren sist i lista.
	    løper.neste = ny;
	  }



		public void skrivListeAlt( JTextArea allInfo )
		  {
		    if ( første == null )
		      allInfo.append( "Registeret er tomt. Feel free to fill it up." );
		    else
		    {
		      allInfo.setText( "" );
		      Eier løper = første;



		    while ( løper != null )
		      {
		        allInfo.append( løper.toString() + "\n" );

		        løper = løper.neste;
		      }


		    }
		  }

		  public void skrivInfoEier (int n, JTextArea info)
		  {
				int nr = n;
				Eier løper = første;

				while (løper != null)
				{
					if (løper.getmNr() == nr)
					{
						info.append ("" + løper.toString());
					}
				}
				info.append ("tomt register");

					}


			public void TilFil ( String filnavn )
			{
				try
				{
					ObjectOutputStream utfil = new ObjectOutputStream( new FileOutputStream (filnavn));
					Eier løper = første;

					while (løper != null)
					{
					utfil.writeObject(løper);
					løper = løper.neste;
					}

				} //end try
				catch( NotSerializableException nse )
    		{
    			  visFeilmelding("Objektet er ikke serialisert!");
    		}
    		catch( IOException ioe )
    		{
    			  visFeilmelding("Problem med utskrift til fil.");
    		}



				} // end tilfil


public void lesFil(String filnavn)
  {
    try (ObjectInputStream innfil = new ObjectInputStream( new FileInputStream( filnavn ))){
    while (true)
    {
			if (innfil.readObject() instanceof Eier)
     	{
				Eier eier = new Eier();
				System.out.println("Ny eier lest og registrert");
		 	}
		 	else if (innfil.readObject() instanceof Båt)
		 	{
			 Båt båt = new Båt();
			 System.out.println("Ny båt lest og registrert");
			}
    }
}
    catch(FileNotFoundException fne)
    {
     visFeilmelding("Finner ikke fil");
    }
    catch(ClassNotFoundException cnfe)
		{
		    System.out.println("Classnotfoundexception i båtregister");
    }
    catch(IOException ioe)
    {
      visFeilmelding("Problem med innlesing av fil i Båtregister/lesFil.");
    }
  }








			} //end of file







