package net.spilledcoffee.bot.commands;

import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.spilledcoffee.bot.CoffeeBot;
import org.jetbrains.annotations.NotNull;
import java.sql.*;

public class PastOrders extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        // If user enters -pastorders, bot should output user's pastorders
        if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "pastorders")){
            event.getChannel().sendTyping().queue();
            // Delay bot Response
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage("Email: " + args[1]).queue();
            event.getChannel().sendMessage("These are your previous orders: \n").queue();
               
            // Getting Connection to the MySQL database and reviewing the order to the customer 
            try {
                Connection connection = DriverManager.getConnection(CoffeeBot.url, CoffeeBot.username, CoffeeBot.password);//Statement myStmt = connection.createStatement();
                PreparedStatement myStmt = connection.prepareStatement("Select * FROM customer_orders2 WHERE cust_email = '" + args[1] + "'");
                ResultSet myRs = myStmt.executeQuery();

                if (myRs.next()) {

                    event.getChannel().sendMessage("===========================\n").queue();
                    event.getChannel().sendMessage("Date: " + myRs.getString("date")).queue();
                    event.getChannel().sendMessage("Email: " + myRs.getString("cust_email")).queue();
                    event.getChannel().sendMessage("Zip Code: " + myRs.getString("cust_location")).queue();
                    event.getChannel().sendMessage("Product ID: " + myRs.getString("product_id")).queue();
                    event.getChannel().sendMessage("Quantity: " + myRs.getString("product_quantity")).queue();
                    event.getChannel().sendMessage("Order ID: " + myRs.getString("order_id")).queue();

                    while (myRs.next()) {

                        event.getChannel().sendMessage("===========================\n").queue();
                        event.getChannel().sendMessage("Date: " + myRs.getString("date")).queue();
                        event.getChannel().sendMessage("Email: " + myRs.getString("cust_email")).queue();
                        event.getChannel().sendMessage("Zip Code: " + myRs.getString("cust_location")).queue();
                        event.getChannel().sendMessage("Product ID: " + myRs.getString("product_id")).queue();
                        event.getChannel().sendMessage("Quantity: " + myRs.getString("product_quantity")).queue();
                        event.getChannel().sendMessage("Order ID: " + myRs.getString("order_id")).queue();
                    }
                    event.getChannel().sendMessage("===========================\n").queue();
                    event.getChannel().sendMessage("*** DONE LOADING PREVIOUS ORDERS ***").queue();
                }
                else{
                    event.getChannel().sendMessage("Sorry, Email was not found.\n").queue();
                }
            }catch (SQLException e) {
                // TODO Auto-generated catch block
                event.getChannel().sendMessage("Oops, Error!\n").queue();
                e.printStackTrace();
            }
        }
    }
}
