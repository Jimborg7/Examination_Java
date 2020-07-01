/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opii_21815_21873;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import static opii_21815_21873.GUI.frame;
import static opii_21815_21873.Database_Cities.connectionToDatabase;
import static opii_21815_21873.Database_Cities.selectToDatabase;
import static opii_21815_21873.OPII_21815_21873.load_Travellers;
import static opii_21815_21873.Database_Cities.insertToDatabase;
import static opii_21815_21873.OPII_21815_21873.save_Travellers;
import static opii_21815_21873.OPII_21815_21873.clear_Arraylists;
import java.sql.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jimge
 */
public class Listeners extends GUI implements ActionListener{

    List<Traveller> CollectionListofTravellers = new ArrayList<>();
    ArrayList<City> citiesexcluded = new ArrayList<>();
    ArrayList<String> citynameListeners = new ArrayList();
    ArrayList<String> countrycodeListeners = new ArrayList();
    ArrayList<City> tmp_city = new ArrayList();
    City candidate_city = new City(null,null,null,null,null,null,0.0,0.0,null,0,0);
    ArrayList<String> filename = new ArrayList<>();
    Traveller tmptrav = new Traveller(null,0,null,0,0,null,null,null,null,null,null);
    Map<String,String> humimap = new HashMap<>();
    //ArrayList<Integer> credentials = new ArrayList<>();
   // ArrayList<Integer> sum_for_humidity = new ArrayList<>();


    public void Listener_Radio(JRadioButton radiobutton,ArrayList<String> radio_choice,JTextField tf1,JTextField tf2,ButtonGroup Joptions,JButton Submit_Name_Age,JButton send_request,JTextField cities_text,Set <Traveller> CollectionofTravellers,JButton finished,ArrayList<City> citiesListeners,ArrayList<String> criteriaListeners,JButton get_humidity ){
        JTextField textField = new JTextField();
        radiobutton.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e){
                textField.setEditable(true);
                 if(!radio_choice.contains("1")) {
                    Object[] options = {"Yes","No","Cancel"};
                    int click = JOptionPane.showOptionDialog(frame,"Are you sure about the type of Traveller?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                    if(click == JOptionPane.YES_OPTION ){
                        JOptionPane.showMessageDialog(frame,"OPTION SAVED.Remember that you cannot change this setting later.");
                        radio_choice.add("1");
                        filename.add(getSaveFile());
                        String check_rb = getSelectedButton(Joptions);
                        if(check_rb.equals("Traveller")){
                        Connection connListeners = connectionToDatabase();
                        Execute(connListeners,citiesListeners,CollectionofTravellers,filename.get(0));
                        Traveller traveller = new Traveller(null,0,null,0,0,null,null,null,null,null,null);
                        Listener(tf1, tf2, Submit_Name_Age);
                        Listener(tf1,Submit_Name_Age);
                        Listener(tf2,Submit_Name_Age);
                        Listeners_Name_Age(Submit_Name_Age,tf1,tf2,traveller);
                        Listener(cities_text,send_request);
                        Listeners_City(send_request,cities_text,traveller,citiesListeners  );
                        //Εισάγουμε την μέθοδο ποθ περιέχει τον Action Listener
                        Humidity_For_CandidateCities(get_humidity);
                        Finish(finished, traveller,criteriaListeners,citiesListeners,CollectionofTravellers,connListeners,filename.get(0));
                    }else if(check_rb.equals("Tourist")){
                        Connection connListeners =  connectionToDatabase();
                        Execute(connListeners,citiesListeners,CollectionofTravellers,filename.get(0));
                        Tourist tourist = new Tourist(null,0,null,0,0,null,null,null,null,null,null);
                        Listener(tf1,tf2,Submit_Name_Age);
                        Listener(tf1,Submit_Name_Age);
                        Listener(tf2,Submit_Name_Age);
                        Listeners_Name_Age(Submit_Name_Age,tf1,tf2,tourist);
                        Listener(cities_text,send_request);
                        Listeners_City(send_request,cities_text,tourist,citiesListeners  );
                        //Το ίδιο
                        Humidity_For_CandidateCities(get_humidity);
                        Finish(finished, tourist,criteriaListeners,citiesListeners,CollectionofTravellers,connListeners,filename.get(0));
                    }else if(check_rb.equals("Bussinessman")){
                        Connection connListeners =  connectionToDatabase();
                        Execute(connListeners,citiesListeners,CollectionofTravellers,filename.get(0));
                        Business businessman = new Business(null,0,null,0.0,0.0,null,null,null,null,null,null);
                        Listeners action3 = new Listeners();
                        Listener(tf1, tf2, Submit_Name_Age);
                        Listener(tf1,Submit_Name_Age);
                        Listener(tf2,Submit_Name_Age);
                        Listeners_Name_Age(Submit_Name_Age,tf1,tf2,businessman);
                        Listener(cities_text,send_request);
                        Listeners_City(send_request,cities_text,businessman,citiesListeners  );
                        //Το ίδιο
                        Humidity_For_CandidateCities(get_humidity);
                        Finish(finished,businessman,criteriaListeners,citiesListeners,CollectionofTravellers,connListeners,filename.get(0));
                        JDialog dialog = new JDialog(frame, "Businessman");
                                JPanel busy = new JPanel(new FlowLayout());
                                JLabel label = new JLabel("Insert your current latitude/longtitude");
                                JTextField btext1 = new JTextField(25);
                                JTextField btext2 = new JTextField(25);
                                JButton bb = new JButton("Submit");
                                busy.add(label);
                                busy.add(btext1);
                                busy.add(btext2);
                                busy.add(bb);
                                Listener(btext1,bb);
                                Listener(btext2,bb);
                                dialog.add(busy);
                                dialog.setSize(350,150);
                                dialog.setLocationRelativeTo(frame);
                                dialog.setVisible(true);
                                bb.addActionListener(new ActionListener(){
                                    @Override
                                    public void actionPerformed(ActionEvent e){
                                        businessman.current_lan = Double.parseDouble(btext1.getText());
                                        businessman.current_lon = Double.parseDouble(btext2.getText());
                                        System.out.println(businessman.current_lan+" "+businessman.current_lon);
                                    }
                                });
                            }
                    }else if(click == JOptionPane.NO_OPTION ){
                                JOptionPane.showMessageDialog(frame,"Cancelled!");
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,"You have already selected a type.");
                }
            }
        });
    }


    public String getSelectedButton(ButtonGroup options){
            for (Enumeration<AbstractButton> buttons = options.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                  return button.getText();
        }
    }
        return null;
    }

    public void Listener(JTextField jtf , JButton jbt ){
        //Action Listener for text only
        jtf.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                    changed();
            }
            public void changed() {
                if (jtf.getText().equals("")){
                             jbt.setEnabled(false);
                }
                 else {
                 jbt.setEnabled(true);
                }
            }
        });
    }

    public void Listener(JTextField jtf ,JTextField jtf2, JButton jbt ){
        //Action Listener for text only
        jtf.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                    changed();
            }
            public void changed() {
                if (jtf.getText().equals("")){
                             jbt.setEnabled(false);
                } else {
                    jtf2.getDocument().addDocumentListener(new DocumentListener(){
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                       changed();
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                      changed();
                    }
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        changed();
                    }
                    public void changed() {
                        if (jtf.getText().equals("")){
                             jbt.setEnabled(false);
                        }else{
                           jbt.setEnabled(true);
                        }
                    }
                    });
                }
            }
        });
    }

    public void Listeners_Name_Age(JButton jbt , JTextField tf1, JTextField tf2,Traveller traveller){
       jbt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                traveller.name = tf1.getText();
                String integer = tf2.getText();
                traveller.age = Integer.parseInt(integer);
                if ((traveller.age<=0 || traveller.age>125) || (tf1.getText().isEmpty()==true || !tf1.getText().matches("[a-zA-Z_]+"))) {
                    System.out.println("This is not an acceptable age, please give a correct one");
                    JDialog dialog = new JDialog(frame, "Unsuccesful Entry");
                    JLabel label = new JLabel("Entry Failed. Try again.");
                    dialog.getContentPane().add(label);
                    dialog.setSize(200,100);
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);
                } else{
                    System.out.println(traveller.name);
                    System.out.println(traveller.age);
                    JOptionPane.showMessageDialog(frame,"Succesfully passed name and age");
                }
            }
        });
    }

    public void Listeners_City(JButton jbt , JTextField cit,Traveller traveller,ArrayList<City> citiesListeners   ){
        jbt.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                 if ((cit.getText().isEmpty()==true)||(cit.getText().indexOf('/')==-1)) {
                    System.out.println("This is not acceptable, please give a correct one");
                    JDialog dialog = new JDialog(frame, "Unsuccesful Entry");
                    JLabel label = new JLabel("Entry Failed. Try again.");
                    dialog.getContentPane().add(label);
                    dialog.setSize(200,100);
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);
                }else{
                    int how_many_cities,countries;
                    String [] split_cities = cit.getText().split("/");
                    String [] city_name;
                    String city1,country1;
                    for(how_many_cities = 0;how_many_cities<split_cities.length;how_many_cities++){
                        city_name = split_cities[how_many_cities].split(",");
                        for(countries=0;countries<city_name.length;countries = countries  + 2){
                            citynameListeners.add(city_name[countries]);
                            countrycodeListeners.add(city_name[countries+1]);
                        }//if athens,gr/berlin,de city_name[0]=athens,city_name[1] = gr,city_name[2] = berlin...
                    }
                    citynameListeners.add(null);

                    for(int citynum = 0;citynum<=citynameListeners.size();citynum++){

                        if(citynameListeners.get(citynum)==null){
                            break;
                        }
                        candidate_city = new City(citynameListeners.get(citynum),countrycodeListeners.get(citynum),null,null,null,null,0.0,0.0,null,0,0);

                        if(citiesListeners.isEmpty()){
                            try{
                                 candidate_city.ExtractData(citynameListeners.get(citynum),countrycodeListeners.get(citynum));
                            } catch (IOException ex) {
                                 Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.out.print("You have entered the country code correctly\n");

                            if(candidate_city.city_weather.equals("Rain")){
                                    traveller.CompareCities(Boolean.TRUE);
                                    citiesexcluded.add(new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape,candidate_city.city_pressure,candidate_city.city_humidity));
                            }else{
                                traveller.CompareCities(Boolean.FALSE);
                                citiesListeners.add( new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape,candidate_city.city_pressure,candidate_city.city_humidity));
                                tmp_city.add(new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape,candidate_city.city_pressure,candidate_city.city_humidity));
                                //Προσθέτουμε την πόλη στον χάρτη όταν η λίστα από την βάση δεδομένων είναι κενή
                                humimap.put(candidate_city.city_name,candidate_city.city_pressure+","+candidate_city.city_humidity);
                               ;
                            }
                        }else{
                            System.out.print("You have entered the city corrrectly\n");
                            if(citiesListeners.contains(candidate_city)){
                                int pos = citiesListeners.indexOf(candidate_city);
                                    System.out.print("The city inserted already exists.\n");
                                    tmp_city.add(new City(citiesListeners.get(pos).city_name,citiesListeners.get(pos).city_countrycode,citiesListeners.get(pos).city_transportation,citiesListeners.get(pos).city_museum,citiesListeners.get(pos).city_CafeRestaurantBar,citiesListeners.get(pos).city_weather,citiesListeners.get(pos).city_lat,citiesListeners.get(pos).city_lon,citiesListeners.get(pos).city_landscape,citiesListeners.get(pos).city_pressure,citiesListeners.get(pos).city_humidity));
                                    //Προσθέτουμε την πόλη που  υπάρχει μέσα στη λίστα της βάσης, στο χάρτη
                                    humimap.put(citiesListeners.get(pos).city_name,citiesListeners.get(pos).city_pressure+","+citiesListeners.get(pos).city_humidity);
                            }else{
                                try{
                                    candidate_city.ExtractData(citynameListeners.get(citynum),countrycodeListeners.get(citynum));
                                } catch (IOException ex) {
                                        System.err.println(ex);
                                }
                                if(candidate_city.city_weather.equals("Rain")){
                                    traveller.CompareCities(Boolean.TRUE);
                                    citiesexcluded.add(new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape,candidate_city.city_pressure,candidate_city.city_humidity));
                                }else{
                                    traveller.CompareCities(Boolean.FALSE);
                                    citiesListeners.add( new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape,candidate_city.city_pressure,candidate_city.city_humidity));
                                    tmp_city.add(new City(candidate_city.city_name,candidate_city.city_countrycode,candidate_city.city_transportation,candidate_city.city_museum,candidate_city.city_CafeRestaurantBar,candidate_city.city_weather,candidate_city.city_lat,candidate_city.city_lon,candidate_city.city_landscape,candidate_city.city_pressure,candidate_city.city_humidity));
                                    //Προσθέτουμε την πόλη που δεν υπάρχει μέσα στη λίστα της βάσης, στο χάρτη
                                    humimap.put(candidate_city.city_name,candidate_city.city_pressure+","+candidate_city.city_humidity);
                                }
                            }
                        }
                    }

                    for(int i=0;i<tmp_city.size();i++){
                        traveller.cities += tmp_city.get(i).city_name;

                    }
                    System.out.println("TRAVELLER CITIES -> "+traveller.cities);
                    clear_Arraylists(citynameListeners,countrycodeListeners);
            }
            }
        });
    }

    public void TreeListener_Transportation(JTree tree,DefaultMutableTreeNode child,DefaultMutableTreeNode child2,DefaultMutableTreeNode child3,ArrayList<String> criteriaListeners,ArrayList<String> crit_choice ){
        tree.addTreeSelectionListener(new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            tree.getLastSelectedPathComponent();
            tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

            if(!crit_choice.contains("1")){
                if (node == null){
                    System.out.print("Error");
                    criteriaListeners.add(null);
                }else{
                    if(node == child){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("1");
                            criteriaListeners.add("Metro");
                        }
                    }else if(node == child2){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("1");
                            criteriaListeners.add("Bus");
                        }
                    }else if(node == child3){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("1");
                            criteriaListeners.add("Airport");
                        }
                    }
                }
            }
          }
        });
    }

    public void TreeListener_Weather(JTree tree,DefaultMutableTreeNode weather1,DefaultMutableTreeNode weather2,DefaultMutableTreeNode weather3,ArrayList<String> criteriaListeners,ArrayList<String> crit_choice){

        tree.addTreeSelectionListener(new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            tree.getLastSelectedPathComponent();
            tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
            if(!crit_choice.contains("2") ) {
                if (node == null){
                    System.out.print("Error");
                    criteriaListeners.add(null);
                }else{
                    if(node == weather1){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("2");
                            criteriaListeners.add("Clouds");
                        }
                    }else if(node == weather2){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("2");
                            criteriaListeners.add("Clear");
                        }
                    }else if(node == weather3){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("2");
                            criteriaListeners.add("Snow");
                        }
                    }
                }
            }
        }
    });
    }

    public void TreeListener_Museum(JTree tree,DefaultMutableTreeNode museum1,DefaultMutableTreeNode museum2,DefaultMutableTreeNode museum3,ArrayList<String> criteriaListeners,ArrayList<String> crit_choice){

        tree.addTreeSelectionListener(new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            tree.getLastSelectedPathComponent();
            tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
            if(!crit_choice.contains("3") ) {
                if (node == null){
                    System.out.print("Error");
                    criteriaListeners.add(null);
                }else{
                    if(node == museum1){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("3");
                            criteriaListeners.add("Archaeology museum");
                        }
                    }else if(node == museum2){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("3");
                            criteriaListeners.add("Art museum");
                        }
                    }else if(node == museum3){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("3");
                            criteriaListeners.add("Military and war museum");
                        }
                    }
                }
            }
        }
        });
    }

    public void TreeListener_CafeRestaurantBar(JTree tree,DefaultMutableTreeNode cafe,DefaultMutableTreeNode restaurant,DefaultMutableTreeNode bar,ArrayList<String> criteriaListeners,ArrayList<String> crit_choice ){
        tree.addTreeSelectionListener(new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            tree.getLastSelectedPathComponent();
            tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

            if(!crit_choice.contains("4") ) {
                if (node == null){
                    System.out.print("Error");
                    criteriaListeners.add(null);
                }else{
                    if(node == cafe){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("4");
                            criteriaListeners.add("cafe");
                        }
                    }else if(node == restaurant){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("4");
                            criteriaListeners.add("restaurant");
                        }
                    }else if(node == bar){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("4");
                            criteriaListeners.add("bar");
                        }
                    }
                }
            }
        }
    });
    }
    public void TreeListener_Landscape(JTree tree,DefaultMutableTreeNode valley,DefaultMutableTreeNode mount,DefaultMutableTreeNode desert,DefaultMutableTreeNode forest,DefaultMutableTreeNode glacier,ArrayList<String> criteriaListeners,ArrayList<String> crit_choice){
        tree.addTreeSelectionListener(new TreeSelectionListener() {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)
            tree.getLastSelectedPathComponent();
            tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

            if(!crit_choice.contains("5") ) {
                if (node == null){
                    System.out.print("Error");
                    criteriaListeners.add(null);
                }else{
                    if(node == valley){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("5");
                            criteriaListeners.add("Valley");
                        }
                    }else if(node == mount){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("5");
                            criteriaListeners.add("Mount");
                        }
                    }else if(node == desert){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("5");
                            criteriaListeners.add("Desert");
                        }
                    }else if(node == forest){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("5");
                            criteriaListeners.add("Forest");
                        }
                    }else if(node == glacier){
                        int criteria_choice = JOptionPane.showConfirmDialog(frame,"Would you like to choose this "+node+", to add in your criteria","Confirm Dialog",JOptionPane.YES_NO_OPTION);
                        if(criteria_choice == JOptionPane.YES_OPTION ){
                            crit_choice.add("5");
                            criteriaListeners.add("Glacier");
                        }
                    }
                }
            }
        }
    });
    }

    //Δημιουργία νέας μεθόδου για την εκτέλεση εντολών με το πάτημα του κουμιού
    public void Humidity_For_CandidateCities(JButton get_humidity){
        get_humidity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=0,sum=0;
                int average_humidity = 0;
                //Υπολογισμός του αθροίσματος υγρασίας για τις πόλεις που έχει εισάγει ο χρήστης .
                //Η ιδέα είναι πολύ απλή. Το κλειδί είναι ούτως ή άλλως κείμενο αφού περιέχει όνομα,.
                //Τις τιμές, τις έβαλα να είναι επίσης string και αυτό γιατί είναι απλώς ο τρόπος να διαχειριστούμε τα στοιχεία κειμένου.
                //Προσπάθησα να βάλω στις τιμές του΄χάρτη λίστα ακεραίων αλλα μου ήταν εξαιρετικά δύσκολο να λάβω την δεύτερη τιμή για την υγρασία
                //παρότι τα πέρναγα σωστά στο χάρτη. Δοκίμασα να το διαχειριστώ ώς stream αλλά αντιμετώπισα άλλα προβλήματα
                for(Map.Entry<String,String> entry:humimap.entrySet()) {                
                    //Η λογική είναι ιδιαίτερα απλοική. Χωρίζω με βάση το κόμμμα που εισάγεται στον χάρτη τα δύο κομάτια του
                    //κειμένου και εξάγω την δεύτερη τιμή, την οποία και μετατρέπω σε ακέραιο.
                    //Εδώ το χρησιμοποιώ για να βγάλω παρακάτω τον μέσο όρο υγρασίας των δοθέντων πόλλεων
                    String []values_split  = entry.getValue().split(",");
                    int string_humidity = Integer.parseInt(values_split[1]);
                    System.out.println("Humidity of "+entry.getKey() +" is: "+string_humidity);
                    sum += string_humidity;
                    i++;
                }
                //Υπολογισμός μέσου όρου
                average_humidity = sum /i;
                System.out.println("Average humidity="+average_humidity);

                //Έλεγχος για κάθε υγρασία των πόλλεων που υπάρχουν στο χάρτη με τον μέσο όρο
                //Η λογική είναι ίδια μόνο που τώρα το δεύτερο κομμάτι που παίρνω το συγκρίνω με τον μέσο όρο κάθε φορά
                String map_names ="High humidity:";
                for ( Map.Entry<String,String> entry:humimap.entrySet()) {
                    String []values_split  = entry.getValue().split(",");
                    int string_humidity = Integer.parseInt(values_split[1]);
                    if(string_humidity > average_humidity){
                       map_names +="\n"+(entry.getKey())+",";
                    }
                }
                //Συγκεντρώνω τις τιμές των πόλλεων που ταιριάζουν σε μία μεταβλητή κειμένου για ομαδοποίηση
                //Δημιουργία παραθύρου για να εμφανίσουμε τα αποτελέσματα στον χρήστη
                JDialog window = new JDialog(frame,"HUMIDITY FOR CANDIDATE CITIES");
                JPanel panel = new JPanel(new FlowLayout());
                JLabel window_label = new JLabel("These are the cities with humidity above the average humidity of the given cities");
                JLabel window_label2 = new JLabel(map_names);
                JButton confirmation = new JButton("Proceed");
                //Εδώ με το πάτημα του κουμπιού θα κλείσει μεμονομένα το παράθυρο και το πρόγραμμα συνεχίζει κανονικά
                confirmation.addActionListener((ActionEvent e1) -> {
                    window.dispose();
                });
                panel.add(window_label);
                panel.add(window_label2);
                panel.add(confirmation);
                window.add(panel);
                window.setSize(500,120);
                window.setLocationRelativeTo(frame);
                window.setVisible(true);
        }});
    }

    public void Finish(JButton finish,Traveller traveller,ArrayList<String> criteriaListeners,ArrayList<City> citiesListeners,Set<Traveller> CollectionofTravellers, Connection connListeners ,String file_name_save  ){

        finish.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JDialog finish_dialog = new JDialog();
                JLabel finish_label = new JLabel("There are two ways to determin");
                JPanel finish_panel = new JPanel(new GridLayout(2,2));
                finish_panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "How to calculate the result?"));
                ButtonGroup finish_options = new ButtonGroup();
                JRadioButton finish_Based_filtering = new JRadioButton("Based on your given Criteria");
                JRadioButton finish_Collaborative_filtering = new JRadioButton("Based on other users criteria");
                finish_options.add(finish_Based_filtering);
                finish_options.add(finish_Collaborative_filtering);
                finish_panel.add(finish_Based_filtering);
                finish_panel.add(finish_Collaborative_filtering);

                finish_Based_filtering.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        /////SIMILARITY\\\\\\
                        String answer = traveller.CompareCities(tmp_city, criteriaListeners);
                        traveller.setVisit(answer);
                        getTravellerCriteria(criteriaListeners,traveller);
                        addTravellers(tmptrav,CollectionofTravellers);
                        CollectionTravellers(CollectionofTravellers);
                        save_Travellers(CollectionListofTravellers,file_name_save);
                                    try{
                                        for(int k=0; k<tmp_city.size();k++){
                                            insertToDatabase(connListeners,tmp_city.get(k));
                                        }
                                        connListeners.close();
                                    }catch(SQLException ex){
                                        System.err.println(ex);
                                    }
                        //FREETICKET
                        int freeticketresult = FreeTicket();
                        if(freeticketresult==0){
                            JDialog dialog = new JDialog();
                            JPanel dialog_panel = new JPanel(new FlowLayout());
                            JLabel label = new JLabel("Also from the criteria you selected");
                            JLabel label2 = new JLabel("we have confirmed that the best suited city for you to visit is:\n\t"+answer+"!");
                            JButton complete = new JButton("Complete");
                            Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
                            dialog_panel.setBorder(blackline);
                            dialog_panel.add(label);
                            dialog_panel.add(label2);
                            System.out.println(file_name_save);
                            complete.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ea){
                                    clear_Arraylists(citynameListeners,countrycodeListeners);
                                    System.exit(0);
                                }
                            });
                            dialog_panel.add(complete);
                            dialog.add(dialog_panel);
                            dialog.setLocationRelativeTo(frame);
                            dialog.setSize(420,150);
                            dialog.setVisible(true);
                        }else if(freeticketresult == 1){
                            JDialog dialog = new JDialog();
                            JPanel dialog_panel = new JPanel(new FlowLayout());
                            JLabel label = new JLabel("The best suited city for you is"+"\n"+"********************"+"\n"+tmptrav.visit+"!");
                            JButton complete = new JButton("Complete");
                            Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
                            dialog_panel.setBorder(blackline);
                            dialog_panel.add(label);
                            System.out.println(file_name_save);
                            complete.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ea){

                                    clear_Arraylists(citynameListeners,countrycodeListeners);
                                    System.exit(0);
                                 }
                            });
                            dialog_panel.add(complete);
                            dialog.add(dialog_panel);
                            dialog.setLocationRelativeTo(frame);
                            dialog.setSize(500,150);
                            dialog.setVisible(true);
                        }
                    }
                });

                //////Streams\\\\\\
                finish_Collaborative_filtering.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        getTravellerCriteria(criteriaListeners,traveller);
                        addTravellers(tmptrav,CollectionofTravellers);
                        CollectionTravellers(CollectionofTravellers);
                        int freestreamsticket = FreeTicket();
                        if(freestreamsticket==0){
                            Streams st = new Streams();
                            tmptrav.visit = st.streamsLamda(CollectionListofTravellers,tmptrav,criteriaListeners);
                            System.out.println("Collaborative Filtering shows -> "+tmptrav.visit);
                            System.out.println(file_name_save);
                            JDialog dialog = new JDialog();
                            JPanel dialog_panel = new JPanel(new FlowLayout());
                            JLabel label = new JLabel("Also based on other peoples preferences and your own criteria," );
                            JLabel label2 = new JLabel("we have confirmed that the best suited city for you to visit is:\n\t"+tmptrav.visit+"!");
                            JButton complete = new JButton("Complete");
                            Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
                            dialog_panel.setBorder(blackline);
                            dialog_panel.add(label);
                            dialog_panel.add(label2);
                            System.out.println(file_name_save);
                            complete.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ea){
                                    save_Travellers(CollectionListofTravellers,file_name_save);
                                    try{
                                        for(int k=0; k<tmp_city.size();k++){
                                            insertToDatabase(connListeners,tmp_city.get(k));
                                        }
                                        connListeners.close();
                                    }catch(SQLException ex){
                                      System.err.println(ex);
                                    }
                                    clear_Arraylists(citynameListeners,countrycodeListeners);
                                    System.exit(0);
                                }
                            });
                            dialog_panel.add(complete);
                            dialog.add(dialog_panel);
                            dialog.setLocationRelativeTo(frame);
                            dialog.setSize(500,100);
                            dialog.setVisible(true);
                        }else if(freestreamsticket == 1){
                            Streams st = new Streams();
                            tmptrav.visit = st.streamsLamda(CollectionListofTravellers,tmptrav,criteriaListeners);
                            System.out.println("Collaborative Filtering shows -> "+tmptrav.visit);
                            System.out.println(file_name_save);
                            JDialog dialog = new JDialog();
                            JPanel dialog_panel = new JPanel(new FlowLayout());
                            JLabel label = new JLabel("The best suited city for you, based on similar criteria with other users and their recomended cities, is"+"\n"+"********************"+"\n"+tmptrav.visit+"!");
                            JLabel label2 = new JLabel("based on similar criteria with other users and their recomended cities, is"+"\n"+"********************"+"\n"+tmptrav.visit+"!");
                            JButton complete = new JButton("Complete");
                            Border blackline = BorderFactory.createLineBorder(Color.DARK_GRAY);
                            dialog_panel.setBorder(blackline);
                            dialog_panel.add(label);
                            System.out.println(file_name_save);
                            complete.addActionListener(new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent ea){
                                    save_Travellers(CollectionListofTravellers,file_name_save);
                                    try{
                                        System.out.print("Please wait while we are adding the newly inserted cities in our database\n");
                                        for(int k=0; k<tmp_city.size();k++){
                                            insertToDatabase(connListeners,tmp_city.get(k));
                                        }
                                        connListeners.close();
                                    }catch(SQLException ex){
                                        System.err.println(ex);
                                    }
                                    clear_Arraylists(citynameListeners,countrycodeListeners);
                                    System.exit(0);
                                 }
                            });
                            dialog_panel.add(complete);
                            dialog.add(dialog_panel);
                            dialog.setLocationRelativeTo(frame);
                            dialog.setSize(350,150);
                            dialog.setVisible(true);
                        }
                    }
                });
                finish_dialog.add(finish_panel);
                finish_dialog.setLocationRelativeTo(frame);
                finish_dialog.setSize(350,150);
                finish_dialog.setVisible(true);
            }
        });
    }

    public void getTravellerCriteria(ArrayList<String> criteria, Traveller traveller){
        if(criteria.contains("Metro")){
            traveller.transportation = "Metro";
        }else if(criteria.contains("Bus")){
            traveller.transportation = "Bus";
        }else if(criteria.contains("Airport")){
            traveller.transportation = "Airport";
        }else
            traveller.transportation = null;

        if(criteria.contains("Clouds")){
            traveller.weather = "Clouds";
        }else if(criteria.contains("Clear")){
            traveller.weather = "Clear";
        }else if(criteria.contains("Snow")){
            traveller.weather = "Snow";
        }else
            traveller.weather = null;

        if(criteria.contains("Archeological museum")){
            traveller.museum = "Archeological museum";
        }else if(criteria.contains("Art museum")){
            traveller.museum = "Art museum";
        }else if(criteria.contains("Military and war museum")){
            traveller.museum = "Military and war museum";
        }else
            traveller.museum = null;

        if(criteria.contains("cafe")){
            traveller.CafeRestaurantBar = "cafe";
        }else if(criteria.contains("restaurant")){
            traveller.CafeRestaurantBar = "restaurant";
        }else if(criteria.contains("bar")){
            traveller.CafeRestaurantBar = "bar";
        }else
            traveller.CafeRestaurantBar = null;

        if(criteria.contains("Valley")){
            traveller.landscape = "Valley";
        }else if(criteria.contains("Mount")){
            traveller.landscape = "Mount";
        }else if(criteria.contains("Desert")){
            traveller.landscape = "Desert";
        }else if(criteria.contains("Forest")){
            traveller.landscape = "Forest";
        }else if(criteria.contains("Glacier")){
            traveller.landscape = "Glacier";
        }else
            traveller.landscape = null;

        tmptrav = new Traveller(traveller.name,traveller.age,traveller.transportation,traveller.current_lan,traveller.current_lon,traveller.weather,traveller.museum,traveller.CafeRestaurantBar,traveller.landscape,traveller.cities,traveller.visit);
    }

    public void CollectionTravellers( Set<Traveller> CollectionofTravellers ) {

        CollectionListofTravellers = CollectionofTravellers.stream().collect(Collectors.toList());
                for(int i=0;i<CollectionListofTravellers.size();i++){
                    for(int j=i+1;j<CollectionListofTravellers.size();j++){
                        if(CollectionListofTravellers.get(i).age == CollectionListofTravellers.get(j).age && CollectionListofTravellers.get(i).name.equals(CollectionListofTravellers.get(j).name)){
                            CollectionListofTravellers.remove(i);
                        }
                    }
                }

                Collections.sort(CollectionListofTravellers);
                for(int j=0;j < CollectionListofTravellers.size();j++){
                    if(CollectionListofTravellers.get(j).visit==null){
                        CollectionListofTravellers.remove(j);
                    }

                }
                for(int j =0;j<CollectionListofTravellers.size();j++){
                                       System.out.println(j + ") " + CollectionListofTravellers.get(j).name + "  "+CollectionListofTravellers.get(j).age+" "+CollectionListofTravellers.get(j).transportation);
                }
    }

    public void Execute(Connection connListeners,ArrayList <City> citiesListeners,Set<Traveller> CollectionofTravellers,String file_name_save){
        selectToDatabase(connListeners,citiesListeners);
        try {
            load_Travellers(CollectionofTravellers,file_name_save);
        }catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }

    public void saveInFile(JMenuItem save_Option,Set<Traveller> CollectionofTravellers){
       save_Option.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(frame,"You now have to choose the file in order to save!");
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt & Doc Files","txt", "doc");;
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(frame);
            save_Travellers(CollectionListofTravellers,chooser.getSelectedFile().getName());
       });
    }

    public String getSaveFile(){
        JOptionPane.showMessageDialog(frame,"You now have to choose the file in order to save!");
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt & Doc Files","txt", "doc");;
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("\nYou chose to open this file: " + chooser.getSelectedFile().getName());
            return chooser.getSelectedFile().getName();
        }
        return chooser.getSelectedFile().getName();
    }

    public void addTravellers(Traveller traveller , Set<Traveller> CollectionofTravellers ){
                /*Traveller t2 =  new Traveller("Nikos",19,"Metro",37.98,27.92,"Clouds","Archeological museum","cafe","Glacier","LondonAthensBerlin","London");
                Traveller t3 =  new Traveller("Eleni",44,"Airport",37.03,22.11,"Clear","Archeological museum","restaurant","Desert","CairoBarcelonaAthens","Cairo");
                Traveller t4 =  new Tourist("Panos",21,"Airport",24.98,23.54,"Clouds","Military and war museum","bar","Mount","MadridParis","Madrid");
                Traveller t5 =  new Tourist("Maria",36,"Airport",40.64,22.94,"Snow","Art museum","bar","Mount","BerlinBarcelonaMadrid","Berlin");
                Traveller t6 =  new Traveller("Christina",20,"Bus",37.03,22.11,"Clear","Art museum","restaurant","Desert","CairoLondonMadrid","Cairo");*/


                CollectionofTravellers.add(traveller);
                /*CollectionofTravellers.add(t2);
                CollectionofTravellers.add(t3);
                CollectionofTravellers.add(t4);
                CollectionofTravellers.add(t5);
                CollectionofTravellers.add(t6);*/

    }




    public int FreeTicket(){
            //FREE TICKET
                for(int i = 0; i<tmp_city.size();i++){
                    if(tmp_city.get(i).city_name.equals("Cairo")){
                        JOptionPane free_pane= new JOptionPane();
                        Object[] options = {"Yes","No"};
                        int click = JOptionPane.showOptionDialog(frame,"You seem to have inserted the city Cairo as one of your choices.Are you interested in participating in a contest to win a free ticket to Cairo?", "CAIRO Free Ticket", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if(click == JOptionPane.YES_OPTION){
                            double max_key =-1.0,user;
                            ArrayList<String> collectioncriteria = new ArrayList<>();
                            Hashtable<Double,String> free_ticket_winner = new Hashtable<>();
                            String free_criteria = null;
                            String [] max_Value = new String[2];
                            for(int k=0;k<CollectionListofTravellers.size();k++){
                                free_criteria = (CollectionListofTravellers.get(k).name+","+CollectionListofTravellers.get(k).age);
                                collectioncriteria.add(CollectionListofTravellers.get(k).getTransportation()); collectioncriteria.add(CollectionListofTravellers.get(k).getWeather());  collectioncriteria.add(CollectionListofTravellers.get(k).getMuseum());   collectioncriteria.add(CollectionListofTravellers.get(k).getCafeRestaurantBar());    collectioncriteria.add(CollectionListofTravellers.get(k).getLandscape());
                                user=CollectionListofTravellers.get(k).Similarity(tmp_city.get(i),collectioncriteria );
                                free_ticket_winner.put(user,free_criteria);
                                for(Map.Entry<Double,String> entry : free_ticket_winner.entrySet() ){
                                    System.out.println();
                                    if(entry.getKey()>=max_key){
                                        String tmp_string =  entry.getValue();
                                        max_Value = tmp_string.split(",");
                                        max_key = entry.getKey();
                                    }
                                }
                            }
                                if(max_Value[0].equals(tmptrav.name) && max_Value[1].equals(String.valueOf(tmptrav.age))){
                                    JOptionPane.showMessageDialog(frame,"\t*******************************************\nYOU HAVE WON. YOU HAVE NOW EARNED A FREE TICKET TO CAIRO!\n\t*******************************************");
                                     collectioncriteria.clear();
                                    return 0;
                                }else{
                                    JOptionPane.showMessageDialog(frame,"Unfortunately you did not earn a free ticket to Cairo!");
                                    collectioncriteria.clear();
                                    return 0;
                            }
                        }
                        return 1;
                    }
                }
        return 1;
    }

    /*@Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/
}
