import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Baatvindu extends JFrame
{
	private JTextField navnefelt, adressefelt, medlemsnrfelt, medlem2felt, regnrfelt, merkefelt, modellfelt, lengdefelt, hkfelt, fargefelt;
	private JButton regEier, regBåt, visAlt, visEier, slettBåt, byttEier, fjernEier;
	private JTextArea utskriftsområde;
	private EierListe liste = new EierListe();
	private Eier first;

	public Baatvindu()
	{
		super( "Småbåtregisteret" );

	try
	{
		lastInn();
	}
	catch (IOException ex)
	{
		System.out.println("Feil under lasting");
	}

	liste.nestenummeret();
	liste.nestebåtnr();

	addWindowListener(new WindowAdapter()
	{
		public void windowClosing( WindowEvent e )
		{
			try
			{
				lagre();
	     	System.out.println("Lagret");
			}
			catch (IOException ex)
			{
				System.out.println("Feil underlagring ved lukking");
			}

	    System.exit( 0 );
	  }
  });


	navnefelt = new JTextField( 20 );
	adressefelt = new JTextField( 20 );
	medlemsnrfelt = new JTextField( 5 );
	medlem2felt = new JTextField (5);
	regnrfelt = new JTextField( 5 );
	merkefelt = new JTextField( 15 );
	modellfelt = new JTextField( 15 );
	lengdefelt = new JTextField( 2 );
	hkfelt = new JTextField( 3 );
	fargefelt = new JTextField( 20 );
	regnrfelt = new JTextField (10);

	regEier = new JButton( "Registrer båteier" );
	regBåt = new JButton( "Registrer båt" );
	visAlt = new JButton( "Vis fullt register" );
	visEier = new JButton( "Vis båteier" );
	slettBåt = new JButton( "Slett båt" );
	byttEier = new JButton( "Bytt eier" );
	fjernEier = new JButton( "Slett båteier" );

	utskriftsområde = new JTextArea( 35, 70 );
  utskriftsområde.setEditable( false );

  Container c = getContentPane();
	c.setLayout( new FlowLayout() );
	c.add (new JLabel( "Navn:" ) );
	c.add( navnefelt );
	c.add (new JLabel( "Adresse:" ) );
	c.add( adressefelt );

	c.add (new JLabel( "Båtmerke:" ) );
	c.add( merkefelt );
	c.add (new JLabel( "Årsmodell:" ) );
	c.add( modellfelt );
	c.add (new JLabel( "Lengde i fot:" ) );
	c.add( lengdefelt );
	c.add (new JLabel( "Motoreffekt (HK):" ) );
	c.add( hkfelt );
	c.add (new JLabel( "Skrogfarge:" ) );
	c.add( fargefelt );
	c.add (new JLabel( "Medlemsnr:" ) );
	c.add( medlemsnrfelt );
	c.add (new JLabel( "Reg.nr:" ) );
	c.add (regnrfelt);
	c.add (new JLabel ( "Medlemsnr ny eier (kun ved eierskifte)" ));
	c.add (medlem2felt);

	c.add( regEier );
	c.add( regBåt );
	c.add( slettBåt );
	c.add( fjernEier );
	c.add( visEier );
	c.add( visAlt );
	c.add(byttEier);

	c.add( new JScrollPane( utskriftsområde ) );

	Knappelytter lytter = new Knappelytter();

	regEier.addActionListener( lytter );
	regBåt.addActionListener( lytter );
	slettBåt.addActionListener( lytter );
	fjernEier.addActionListener( lytter );
	visEier.addActionListener( lytter );
	visAlt.addActionListener( lytter );
	byttEier.addActionListener(lytter);
	setSize( 820, 800 );
  setVisible( true );

}// end public Båtvindu

private class Knappelytter implements ActionListener
{
  public void actionPerformed( ActionEvent e )
  	{
    if ( e.getSource() == regEier )
    nyEier();

    else if ( e.getSource() == regBåt )
    nyBåt();
    else if ( e.getSource() == slettBåt )
    fjernBåt();
    else if ( e.getSource() == fjernEier )
    fjernEier();
    else if ( e.getSource() == visEier )
    visEier();
    else if ( e.getSource() == visAlt )
    visAlt();
    else if ( e.getSource() == byttEier )
   	byttEier();
    }
} //End knappelytter

public void nyEier()
{
	String navn = navnefelt.getText();
	String adresse = adressefelt.getText();

	if (!navn.equals("") && !adresse.equals(""))
	{
		liste.leggTilEier(new Eier(navn, adresse));
		utskriftsområde.setText("Ny båteier er registrert");
	}
	else
	{
		utskriftsområde.setText("Fyll ut både navn og adresse");
	}
	slettFelter();
} // end nyEier


public void nyBåt()
{
	String medlem = medlemsnrfelt.getText();
	String lengdetekst = lengdefelt.getText();
	String årsmodelltekst = modellfelt.getText();
	String hktekst = hkfelt.getText();
	int medlemsnr = Integer.parseInt(medlem);
	int lengde = Integer.parseInt(lengdetekst);
	int årsmodell = Integer.parseInt(årsmodelltekst);
	int hk = Integer.parseInt(hktekst);
	String merke = merkefelt.getText();
	String farge = fargefelt.getText();

	if (lengdefelt.getText().length()>0 && modellfelt.getText().length()>0 && hkfelt.getText().length()>0
	&& merkefelt.getText().length()>0 && fargefelt.getText().length()>0 && medlemsnrfelt.getText().length()>0)
	{
		liste.nestebåtnr();
		Båt b = new Båt(merke, årsmodell, lengde, hk, farge);

		Eier båteier = liste.finn(medlemsnr);

		båteier.båtliste.settInn(b);

		utskriftsområde.setText("Båten er registrert\nReg.nr for båten er: " + b.getRegNr());
	}
	else
	{
		utskriftsområde.setText("Alle nødvendige felter må fylles ut:\n Merke\n Årsmodell\n Lengde\n Motorstørrelse\n Farge\n\nHusk også å fylle inn medlemsnr på båtens eier. Er ikke eieren registrert, må denne registreres før båten legges inn");
	}
		slettFelter();
} //end nybåt

public void fjernBåt()
{
	try
	{

		int regNr = Integer.parseInt(regnrfelt.getText());
		int medlemsnr = Integer.parseInt(medlemsnrfelt.getText());
		Eier båteier = liste.finnBåteier(regNr);

		if (regnrfelt.getText().length()>0)
		{
			båteier.slettBåt(regNr);
			utskriftsområde.setText("Båten er slettet");
		}
	}
	catch (NumberFormatException e)
	{
		utskriftsområde.setText("Vennligst fyll ut reg.nr og båteiers medlemsnr korrekt");
	}
	slettFelter();
} //end fjernbåt

public void fjernEier()
{
	try
	{
		int mNr = Integer.parseInt(medlemsnrfelt.getText());
		if (liste.fjernEier(mNr))
		{
			utskriftsområde.setText("Eieren har blitt slettet");
		}
		else
		{
			utskriftsområde.setText("Eieren har ikke blitt slettet siden han/hun eier en eller flere båt(er). Vennligst slett alle båter først.");
		}
	}
	catch (NumberFormatException e)
	{
		utskriftsområde.setText("Vennligst fyll ut medlemsnr korrekt");
	}
	slettFelter();
} //end fjerneier

public void visEier()
{
	try
	{
		int regnr = Integer.parseInt(regnrfelt.getText());

		Eier eieren = liste.finnBåteier(regnr);
		utskriftsområde.setText(eieren.toString());

	}
	catch (NumberFormatException e)
	{
		utskriftsområde.setText("Vennligst fyll inn regnr på båten korrekt");
	}
	slettFelter();
}//end viseier

public void visAlt()
{
	System.out.println("inne i visalt");
	utskriftsområde.setText(liste.skrivRegister());

}//end visalt

public void byttEier()
{

	int mnr = ( Integer.parseInt(medlemsnrfelt.getText()));
	int mnr2 = ( Integer.parseInt(medlem2felt.getText()));
	int regnr = ( Integer.parseInt(regnrfelt.getText()));

	Eier eier1 = liste.sokEier (mnr);
	Eier eier2 = liste.sokEier (mnr2);
	Båt båt1 = eier1.finnBåt(regnr);

	eier2.setBåt(båt1);
	eier1.slettBåt(regnr);

	utskriftsområde.setText("Båt med reg.nr " + regnr + " er nå registrert på " + eier2.getNavn());

}//end bytteier


private void visMelding( String melding )
{
	JOptionPane.showMessageDialog( null, melding );
}//end vismelding

private void slettFelter()
{
	navnefelt.setText( "" );
	adressefelt.setText( "" );
	medlemsnrfelt.setText( "" );
	merkefelt.setText( "" );
	modellfelt.setText( "" );
	lengdefelt.setText( "" );
	hkfelt.setText( "" );
	fargefelt.setText( "" );
}//end slettfelter

public void lastInn() throws IOException
{
	try
	{
		FileInputStream fil = new FileInputStream("eierliste.dat");
		ObjectInputStream inn = new ObjectInputStream(fil);
		liste = (EierListe) inn.readObject();
	}
	catch (FileNotFoundException ex)
	{
		System.out.println("fant ikke fil");
	}
	catch (ClassNotFoundException ex)
	{
		System.out.println("Classnotfound exception");
	}
	catch (EOFException ex)
	{
		System.out.println("Ferdig lastet");
	}
}//end lastinn


public void lagre() throws IOException
{
	try
	{
		ObjectOutputStream ut = new ObjectOutputStream(new FileOutputStream("eierliste.dat"));
		ut.writeObject(liste);
	}
	catch (FileNotFoundException ex)
	{
		System.out.println("Filenotfound exception");
	}
} //end lagre



} //end of class Båtvindu.java