import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/** This is the driver class for this project. It contains the file menu, help menu and a menu bar as well as the help method.
  * 
  * @author A Barkin 
  * @version 1 09.03.15
  */ 
public class DataBaseApp extends JFrame implements ActionListener
{
  /*The AddressBook used to add the panel of AddressBook to the driver JFrame*/
  AddressBook aB = new AddressBook ();
  
  /*JMenuItem used if the user would like to save a file. Must be static so it can be accessed from subclass and changed*/
  static JMenuItem save = new JMenuItem ("Save");
  
  /** This is the constructor for the driver class which adds all of the required elements such as JMenuItem, JMenu, and JMenuBar to the JFrame.
    * 
    * <p>
    * <b>Local variables:</b>
    * <p>
    * <b>fileMenu -</b> JMenu used for all the file options to manipulae the file.
    * <p>
    * <b>helpMenu -</b> JMenu used to hold the JMenuItem for help to help the user.
    * <p>
    * <b>neww -</b> JMenuItem used if the user would like to create a new file.
    * <p>
    * <b>open -</b> JMenuItem used if the user would like to open a file.
    * <p>
    * <b>saveAs -</b> JMenuItem used if the user would like to save a file under a new name.
    * <p>
    * <b>quit -</b> JMenuItem used if the user would like to quit the program.
    * <p>
    * <b>help -</b> JMenuItem used if the user needs help understanding the program. When clicked it will open a help dialogue.
    * <p>
    * <b>myMenus -</b> JMenuBar used to hold all JMenus at the top of the window.
    *
    * <p>
    * <b>Closures:</b>
    * <p>
    * <b>window listener closure -</b> Used to ask the user if they would like to save before clicking the x in the top right corner of the screen
    */ 
  public DataBaseApp()
  {
    super("Java Data Base");
    
    add(aB);
    
    JMenu fileMenu = new JMenu ("File");
    JMenu helpMenu = new JMenu ("Help");
    
    JMenuItem neww = new JMenuItem ("New");
    JMenuItem open = new JMenuItem ("Open");
    JMenuItem quit = new JMenuItem ("Quit");
    JMenuItem help = new JMenuItem ("Help");
    JMenuItem saveAs = new JMenuItem ("Save As");
    
    neww.addActionListener(this);
    open.addActionListener(this);
    save.addActionListener(this);
    saveAs.addActionListener(this);
    quit.addActionListener(this);
    help.addActionListener(this);
    
    save.setEnabled(false);
    
    fileMenu.add(neww);
    fileMenu.add(open);
    fileMenu.add(save);
    fileMenu.add(saveAs);
    fileMenu.add(quit);
    
    helpMenu.add(help);
    
    JMenuBar myMenus = new JMenuBar ();
    
    myMenus.add (fileMenu);
    myMenus.add (helpMenu);
    
    setJMenuBar (myMenus);
    
    setSize (500, 500);
    setVisible (true);
    
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new WindowAdapter (){
      public void windowClosing(WindowEvent e){
        if (!aB.isSaved)
          aB.saveQ ();
        System.exit (0);
      }
    });
  }
  /** This is the help method which contains dialogue to help the user if they select the help JMenuItem. It will stay on the screen until the user clicks ok.
    * 
    * <p>
    * <b>Local variables:</b>
    * <p>
    * <b>label -</b> JLabel used to hold the text for the help menu so that the font can be changed
    */ 
  public void help ()
  {
    JLabel label = new JLabel("<html><center><h1 style='color:red' ><u>Accepted data entry</u></h1></center><ul>"
                                + "<li><b><u>First Name and Last Name</u></b> Any characters will be accepted, and the first character will be capitalised for you, all remaining characters will be made lower case. " 
                                + "<li><b><u>Phone Number</u></b> <b style='color:red'>ONLY</b> 10 digits can be entered with either spaces or dashes splitting them up after 3 digits and the another three digits. E.g. 123-456-7890 or 123 456 7890 or 1234567890"
                                + "<li><b><u>Email address</u></b> - Local section can only contain letters, numbers, or the characters (!#$%&'*+-/=?^_`{|}~.). Additionally, the entry must not start with a period."
                                + "<br> - Domain section must start with a letter, a number, or a dash followed by a period and then as many subdomains as you would like. Also it cannot end with a period."        
                                + "</ul></html>");
    label.setFont(new Font("century gothic", Font.PLAIN, 14));
    JOptionPane.showMessageDialog (null, label, "Help!", JOptionPane.INFORMATION_MESSAGE); 
  }
  
  /** Performs a given action depending on which event occurs (user input). All if statements are used to execute different commands based on which action event happened.
    * @param e An ActionEvent reference which identifies the source of the command.
    */ 
  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals("New"))
      aB.neww();
    else if (e.getActionCommand().equals("Open"))
      aB.open();
    else if (e.getActionCommand().equals("Save"))
      aB.save();
    else if (e.getActionCommand().equals("Save As"))
      aB.saveAs();
    else if (e.getActionCommand().equals("Quit"))
    {
      if (!aB.isSaved)
        aB.saveQ ();
      System.exit (0);
    }
    else 
      help();
  }
  
  /** Main method of the DataBaseApp class which calls the DataBaseApp constructor.
    * @param args | String array used in the main method
    */
  public static void main (String [] args) 
  {
    new DataBaseApp();
  }
}