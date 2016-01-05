import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


class UIMain extends JFrame implements ActionListener
{
	JTextArea input,output;
	JComboBox fromLanguage,toLanguage;
	JPanel main,buttons,to,from;
	Language language;
	String in,out;
	Border border;
	Font font;
	JButton speakS,speakD,translate;
	Object[] lang={new String("ENGLISH"),new String("FRENCH"),new String("SPANISH"),new String("HINDI"),new String("GERMAN")};
	UIMain()
	{
		try {
		    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {
		    e.printStackTrace();
		}
		border = BorderFactory.createLineBorder(Color.BLACK);
		Translate.setClientId("markdevelopers8");
		Translate.setClientSecret("markdevelopersttssecret");
		to=new JPanel();
		from=new JPanel();
		main=new JPanel();
		buttons=new JPanel();
		to.setLayout(new BorderLayout());
		from.setLayout(new BorderLayout());
		main.setLayout(new GridLayout(1,2));
		buttons.setLayout(new GridLayout(1,3));
		input=new JTextArea(5,10);
		output=new JTextArea(5,10);
		font = new Font("", Font.PLAIN, 18);
		
		input.setFont(font);
		output.setFont(font);
		input.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		output.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		setLayout(new BorderLayout());
		fromLanguage=new JComboBox(lang);
		toLanguage=new JComboBox(lang);
		speakS=new JButton("Play Source");
		speakD=new JButton("Play Translated");
		translate=new JButton("Translate");
		from.add(fromLanguage,BorderLayout.NORTH);
		from.add(input,BorderLayout.CENTER);
		to.add(toLanguage,BorderLayout.NORTH);
		to.add(output,BorderLayout.CENTER);
		main.add(from);
		main.add(to);
		add(main,BorderLayout.CENTER);
		buttons.add(speakS);
		buttons.add(translate);
		buttons.add(speakD);
		add(buttons,BorderLayout.SOUTH);
		setVisible(true);
		setSize(700,500);
		speakS.addActionListener(this);
		translate.addActionListener(this);
		speakD.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		VoiceManager voiceManager = VoiceManager.getInstance();
        Voice helloVoice = voiceManager.getVoice("kevin16");
		
        if(ae.getSource()==speakS && (input.getText()!=""))
		{
        	
	        helloVoice.allocate();
	        helloVoice.speak(input.getText());
	        helloVoice.deallocate();
		}
		else if(ae.getSource()==speakD && (output.getText()!=""))
		{
			helloVoice.allocate();
	        helloVoice.speak(output.getText());
	        helloVoice.deallocate();
		}
		else
		{
			if(input.getText()!="")
			{
			try {
				output.setText(Translate.execute(input.getText(),Language.valueOf(fromLanguage.getSelectedItem().toString()), Language.valueOf(toLanguage.getSelectedItem().toString())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
public String getLanguage(JComboBox l)
{
	String s=(String)l.getSelectedItem();
	if(s.equals("GUJARATI"))
		return new String("gu");
	else if(s.equals("ENGLISH"))
		return new String("en");
	else if(s.equals("SPANISH"))
		return new String("es");
	else if(s.equals("HINDI"))
		return new String("hi");
	else if(s.equals("FRENCH"))
		return new String("fr");
	return s;
}
    
    public static void main(String args[])
    {
    	new UIMain();
    }

    
   
}