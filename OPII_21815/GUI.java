/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author jimge
 */

public class GUI extends JFrame implements ActionListener{
    static JFrame frame;               
    public void createAndShowGUI(){
        //Creating frame
        
        ArrayList<String> crit_choice = new ArrayList();   
        ArrayList<String> radio_choice = new ArrayList();  
        Set<Traveller> CollectionofTravellers = new HashSet<>();
        ArrayList<String> criteriaListeners = new ArrayList(); 
        ArrayList<City> citiesListeners = new ArrayList();               
        
        frame = new JFrame("TRAVEL AGENCY");              
        frame.setSize(700,600);       
        frame.setLocation(50, 100);
        
        //Creating menu Bar components
        JMenu menu_file = new JMenu("File");  
        JMenuItem save_Option = new JMenuItem("Save");
        menu_file.add(save_Option);
        Listeners menu_action = new Listeners();
        menu_action.saveInFile(save_Option,CollectionofTravellers);                                                                                                                                                                                   
        JMenuItem menu_close = new JMenuItem("Exit");             
        menu_close.addActionListener(new ActionListener(){
           @Override
            public void actionPerformed(ActionEvent e) {
                if("Exit".equals(e.getActionCommand())){
                    int dialogButton = JOptionPane.showConfirmDialog (null, "Would you really like to close this program (all your personal data will be lost)?","Terminate Program?",JOptionPane.YES_NO_OPTION);                    
                    if(dialogButton == JOptionPane.YES_OPTION){
                       System.exit(0);
                    }else if(dialogButton == JOptionPane.NO_OPTION){
                         System.out.printf("Action cancelled\n");
                    }                 
                } 
            }                  
        });  
        menu_file.add(menu_close);                                     
        JMenu help = new JMenu("Help");
        JMenuItem menu_info = new JMenuItem("Info");
        menu_info.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if("Info".equals(e.getActionCommand())){
                    JOptionPane.showMessageDialog(frame,"This is a program to help you select the next city you should visit. In order to use the programm you need to choose a type of Traveller, insert your first name and age and the cities you have in mind.Lastly choose some preferences and click FINISH.");
                }
            }                
        });
       help.add(menu_info); 
                        
        //Creating MenuBar
        JMenuBar mb = new JMenuBar();
        mb.add(menu_file);        
        mb.add(help);
        //set menubar to frame
        frame.setJMenuBar(mb);
        //Creating Listener
               
        
        //Creating panels 
        JPanel main_panel=new JPanel(new BorderLayout());//Main panel to insert into it the remaining panels 
        JPanel name_age_traveller_panel = new JPanel(new BorderLayout());//Space to write name and age and also a submit button to include them in the file 
        JPanel traveller_panel=new JPanel(new GridLayout(4,1));//Radio buttons to give type to an object based on the customers choice
        JPanel name_age_panel = new JPanel(new FlowLayout());//Space to write name and age and also a submit button to include them in the file 
        JPanel city_panel=new JPanel(new FlowLayout());//Text area so that the client will be able to provide the necessary info about the city 
        JPanel criteria_panel = new JPanel(new GridLayout(1,1));//Checkbox area to select the criteria and also a list to gve all the kinds of criteria 
        //WE need a new window to give the client the answer for wich city to select           
        
        //First the name and age panel         
        //Border blackline = BorderFactory.createTitledBorder("Provide your firdt name and age only.");
        name_age_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Provide your full name and age only."));
        JTextField tf1 = new JTextField("e.g. Rayman Glouglou",35);
        JTextField tf2 = new JTextField("25",5);
        JButton Submit_Name_Age = new JButton("Submit");        
        name_age_panel.add(tf1);
        name_age_panel.add(tf2);
        name_age_panel.add(Submit_Name_Age);
        
        //Second is the traveller panel where there are radio buttons
        traveller_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "What kind of traveller are you?"));
        ButtonGroup options = new ButtonGroup();
        JRadioButton rb1= new JRadioButton("Traveller");
        JRadioButton rb2= new JRadioButton("Tourist");
        JRadioButton rb3= new JRadioButton("Bussinessman");
        
        options.add(rb1);
        options.add(rb2);
        options.add(rb3);
   
        traveller_panel.add(rb1); 
        traveller_panel.add(rb2);
        traveller_panel.add(rb3);
                
        JSplitPane split_name_age_traveller_panel = new JSplitPane();
        split_name_age_traveller_panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
        split_name_age_traveller_panel.setResizeWeight(0.5);
        split_name_age_traveller_panel.setTopComponent(traveller_panel);
        split_name_age_traveller_panel.setBottomComponent(name_age_panel);
        name_age_traveller_panel.add(split_name_age_traveller_panel);
        
        //Third is the city pane where the client will insert the cities he wants to travel
        city_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cities with their Country codes"));
        JTextField cities_text = new JTextField("Athens,GR/Cairo,EG...",55); 
        JButton send_request = new JButton("Send");//Will try to get the necessary info about the cities
        city_panel.add(cities_text);
        city_panel.add(send_request);
        
        //Forth is the criteria panel where we have a list and checkboxxes // We can also not include a list and have the criteria in checkboxes and the specified in radio buttons since we cannot choose more than one
        //Δημιουργία νέου κουμιού. Το ο΄οίο και περνάω στους γενικούς actionListeners.
        JButton get_humidity = new JButton("Humidity for Cities");
        JButton finished = new JButton("Finish");                                                                          

        DefaultMutableTreeNode Criteria=new DefaultMutableTreeNode("Criteria");  
        DefaultMutableTreeNode Transportation=new DefaultMutableTreeNode("Transportation");  
        DefaultMutableTreeNode Weather=new DefaultMutableTreeNode("Weather");  
        DefaultMutableTreeNode Museum=new DefaultMutableTreeNode("Museum");
        DefaultMutableTreeNode CafeRestaurantBar=new DefaultMutableTreeNode("CafeRestaurantBar");
        DefaultMutableTreeNode Landscape=new DefaultMutableTreeNode("Landscape");
        
        Criteria.add(Transportation);  
        Criteria.add(Weather);  
        Criteria.add(Museum);  
        Criteria.add(CafeRestaurantBar);  
        Criteria.add(Landscape);          
        
        DefaultMutableTreeNode Metro=new DefaultMutableTreeNode("Metro");  
        DefaultMutableTreeNode Bus=new DefaultMutableTreeNode("Bus");  
        DefaultMutableTreeNode Airport=new DefaultMutableTreeNode("Airport");          
        Transportation.add(Metro); Transportation.add(Bus); Transportation.add(Airport);  
        
        DefaultMutableTreeNode Clouds=new DefaultMutableTreeNode("Clouds");  
        DefaultMutableTreeNode Clear=new DefaultMutableTreeNode("Clear");  
        DefaultMutableTreeNode Snow=new DefaultMutableTreeNode("Snow"); 
        Weather.add(Clouds); Weather.add(Clear); Weather.add(Snow);
                 
        DefaultMutableTreeNode Archaeology_museum=new DefaultMutableTreeNode("Archaeology museum");  
        DefaultMutableTreeNode Art_museum=new DefaultMutableTreeNode("Art museum"); 
        DefaultMutableTreeNode Military_and_war_museum=new DefaultMutableTreeNode("Military and war museum"); 
        Museum.add(Archaeology_museum); Museum.add(Art_museum); Museum.add(Military_and_war_museum); 
        
        DefaultMutableTreeNode cafe=new DefaultMutableTreeNode("cafe");  
        DefaultMutableTreeNode restaurant=new DefaultMutableTreeNode("restaurant");  
        DefaultMutableTreeNode bar=new DefaultMutableTreeNode("bar");          
        CafeRestaurantBar.add(cafe); CafeRestaurantBar.add(restaurant); CafeRestaurantBar.add(bar);
        
        DefaultMutableTreeNode Valley=new DefaultMutableTreeNode("Valley");  
        DefaultMutableTreeNode Mount=new DefaultMutableTreeNode("Mount");  
        DefaultMutableTreeNode Desert=new DefaultMutableTreeNode("Desert");  
        DefaultMutableTreeNode Forest=new DefaultMutableTreeNode("Forest");  
        DefaultMutableTreeNode Glacier=new DefaultMutableTreeNode("Glacier");         
        Landscape.add(Valley); Landscape.add(Mount); Landscape.add(Desert); Landscape.add(Forest); Landscape.add(Glacier); 
                                
        JTree jt=new JTree(Criteria);  
        JScrollPane tmp = new JScrollPane(jt);
        
        //Αλλαγή στο προηγούμενο GUI για να εμφανίζεται η νέα επιλογή πάνω από το κουμπί τερματισμού,
        //ώστε ο χρήστης να έχει εισάγει τα απαραίτητα δεδομένα. 
        JSplitPane split_finish = new JSplitPane();
        split_finish.setOrientation(JSplitPane.VERTICAL_SPLIT);
        split_finish.setTopComponent(get_humidity);
        split_finish.setBottomComponent(finished);

        JSplitPane split_criteria_panel = new JSplitPane();
        split_criteria_panel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        //split_criteria_panel.setDividerLocation();
        split_criteria_panel.setResizeWeight(1);
        split_criteria_panel.setLeftComponent(tmp); //IF VERTICAL SPLIT
        split_criteria_panel.setRightComponent(split_finish);        
        criteria_panel.add(split_criteria_panel);        
        criteria_panel.setBackground(Color.LIGHT_GRAY);       
                                                                                                                                                                                      
        JSplitPane split_main_panel = new JSplitPane(SwingConstants.HORIZONTAL,name_age_traveller_panel,city_panel);
        main_panel.add(split_main_panel);                     
        JSplitPane Frame_Split = new JSplitPane();        
        Frame_Split.setOrientation(JSplitPane.VERTICAL_SPLIT);
        Frame_Split.setDividerLocation(300);
        Frame_Split.setResizeWeight(0.5);
        Frame_Split.setTopComponent(main_panel); //IF VERTICAL SPLIT
        Frame_Split.setBottomComponent(criteria_panel);
        frame.add(Frame_Split);       
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                      
        Listeners action = new Listeners();        
        //Συμπεριλαμβάνψ στο τέλος των παρακάτω το νεο-σύστατο κουμπί για να το περάσω στην κλάση των ακροατών και να ελτεέστεί.
        action.Listener_Radio(rb1,radio_choice,tf1,tf2,options,Submit_Name_Age,send_request,cities_text,CollectionofTravellers,finished,citiesListeners,criteriaListeners,get_humidity);
        action.Listener_Radio(rb2,radio_choice,tf1,tf2,options,Submit_Name_Age,send_request,cities_text,CollectionofTravellers,finished,citiesListeners,criteriaListeners,get_humidity);
        action.Listener_Radio(rb3,radio_choice,tf1,tf2,options,Submit_Name_Age,send_request,cities_text,CollectionofTravellers,finished,citiesListeners,criteriaListeners,get_humidity);
        Listeners tree_action = new Listeners();     
                
        tree_action.TreeListener_Transportation(jt,Metro,Bus,Airport,criteriaListeners,crit_choice);       
        tree_action.TreeListener_Weather(jt,Clouds,Clear,Snow,criteriaListeners,crit_choice);
        tree_action.TreeListener_Museum(jt,Archaeology_museum,Art_museum,Military_and_war_museum,criteriaListeners,crit_choice);
        tree_action.TreeListener_CafeRestaurantBar(jt,cafe,restaurant,bar,criteriaListeners,crit_choice);
        tree_action.TreeListener_Landscape(jt,Valley,Mount,Desert,Forest,Glacier,criteriaListeners,crit_choice);              
      
    }                                                                                           

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
                    
}
