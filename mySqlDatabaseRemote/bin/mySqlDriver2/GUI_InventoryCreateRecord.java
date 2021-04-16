package mySqlDriver2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
 
@SuppressWarnings("serial")
public class GUI_InventoryCreateRecord extends JFrame {
 
   // Private variables of the GUI components
   JTextField pidText, quantityText, wholesale_priceText, sales_priceText, sidText;
   JFormattedTextField formattedField;
   private JLabel label = new JLabel();
   String pid, sid;
   int quantity;
   double wholesale_price, sales_price;
   boolean valid = false;
 
   /** Constructor to set up all the GUI components */
   public GUI_InventoryCreateRecord() {
      
      JPanel tfPanel = new JPanel(new GridLayout(5, 2, 10, 2));
      tfPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10));


      JButton button = new JButton("Add Record");
 
      // Regular text field (Row 1)
      tfPanel.add(new JLabel("Product ID"));
      pidText = new JTextField(12);
      
      
      tfPanel.add(pidText);
 
      // Password field (Row 2)
      tfPanel.add(new JLabel("Quantity"));
      quantityText = new JTextField(10);
      tfPanel.add(quantityText);

      // Formatted text field (Row 3)
      tfPanel.add(new JLabel("Wholesale Price"));
      wholesale_priceText = new JTextField(10);
      tfPanel.add(wholesale_priceText);

      // Formatted text field (Row 4)
      tfPanel.add(new JLabel("Sales Price"));
      sales_priceText = new JTextField(10);
      tfPanel.add(sales_priceText);

      // Formatted text field (Row 5)
      tfPanel.add(new JLabel("Supplier's ID"));
      sidText = new JTextField(12);
      tfPanel.add(sidText);

      JPanel btnPanel = new JPanel();
      btnPanel.setLocation(100, 200);
      btnPanel.add(button);
      label.setFont(new Font("Serif", Font.BOLD, 13));
      label.setForeground(Color.red);
      label.setOpaque(true);
      btnPanel.add(label);


      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //This will reset the label every time that the button is pressed
            label.setForeground(Color.red);
            label.setText("");
            
            //This will check if the quantity is a number
            int q = -1;
            for(int i = 0; i<quantityText.getText().length(); i++){
                char a = quantityText.getText().charAt(i);
                if(Character.isDigit(a)){
                    q = Integer.parseInt(quantityText.getText());
                    break;
                }
                else{
                    label.setText("Quantity needs to be a whole number");
                }
            }//checks for a number value in wholesals price
            double w = -1;
            for(int i = 0; i<wholesale_priceText.getText().length(); i++){
                char a = quantityText.getText().charAt(i);
                if(Character.isDigit(a)){
                    w = Double.parseDouble(quantityText.getText());
                    break;
                }
                else{
                    label.setText("Wholesales Price needs to be a number");
                }
            }
            //checking if sales price is a number
            double s = -1;
            for(int i = 0; i<sales_priceText.getText().length(); i++){
                char a = sales_priceText.getText().charAt(i);
                if(Character.isDigit(a)){
                    s = Double.parseDouble(sales_priceText.getText());
                    break;
                }
                else{
                    label.setText("Sales Price needs to be a number");
                }
            }
            if(pidText == null || pidText.getText().length() != 12){   
                label.setText("Invalid Product ID!!!");
            }

            //This will make sure that quantity text field is niether blank or has a quantity less than 0
            else if(quantityText == null || q < 0){
                label.setText("Invalid Quantity!!!");
            }
            else if(wholesale_priceText == null || w <= 0){
                label.setText("Invalid Wholesales Price!!!");
            }
            else if(sales_priceText == null || s <= 0){
                label.setText("Invalid Sales Price!!!");
            }
            else if(sidText == null || sidText.getText().length() != 8){   
                label.setText("Invalid Supplier ID!!!");
            }
            else{
            pid = pidText.getText();
            quantity = q;
            wholesale_price =w;
            sales_price = s;
            sid = sidText.getText();
            label.setForeground(Color.green);
            label.setText("Success!");
            valid = true;
            }
        }
     });
     
 
      // Setup the content-pane of JFrame in BorderLayout
      Container cp = this.getContentPane();
      cp.setLayout(new BorderLayout(5, 5));
      cp.add(tfPanel, BorderLayout.NORTH);
      cp.add(btnPanel, BorderLayout.PAGE_END);
      //cp.add(tAreaScrollPane, BorderLayout.CENTER);
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Create Record");
      setSize(350, 225);
      setVisible(true);
      setBackground(new ColorUIResource(1, 2, 3));;
   }
   //This will get the vlaues that the GUI has stored so we can use the inventory class to add those 
   //into MySQL
   public String getPid(){return pid;}
   public int getQuantity(){return quantity;}
   public double  getWholesale_price(){return wholesale_price;}
   public double getSales_price(){return sales_price;}
   public String getSid(){return sid;}
   public boolean getValid(){return valid;}
 
   
   public static void main(String[] args) {
      // Run GUI codes in Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new GUI_InventoryCreateRecord();  // Let the constructor do the job
         }
      });  
   }

}
   
