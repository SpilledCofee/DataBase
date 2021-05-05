package net.spilledcoffee.bot.commands;

import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.spilledcoffee.bot.CoffeeBot;
import org.jetbrains.annotations.NotNull;
import java.sql.*;

public class Recommend extends ListenerAdapter {

    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        // Separates commands into a list
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        // If user enters -recommend, bot outputs recommended items
        if (args[0].equalsIgnoreCase(CoffeeBot.prefix + "recommend")){
            event.getChannel().sendTyping().queue();
            // Delay bot Response
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.getChannel().sendMessage("Here is a list of my recommended products:").queue();

            try {
                Connection connection = DriverManager.getConnection(CoffeeBot.url, CoffeeBot.username, CoffeeBot.password);

                // SQL quantity Error: Also return products with quantity greater than 0. For inventory products.
                PreparedStatement myStmt = connection.prepareStatement("Select * FROM new_inventory ORDER BY RAND() LIMIT 4;");
                ResultSet myRs = myStmt.executeQuery();

                if (myRs.next()) {

                    event.getChannel().sendMessage("===========================\n").queue();
                    //event.getChannel().sendMessage("Product ID: " + myRs.getString("product_id")).queue();
                    event.getChannel().sendMessage("Product Title: " + myRs.getString("product_title")).queue();
                    event.getChannel().sendMessage("Product Description: " + myRs.getString("product_description")).queue();
                    event.getChannel().sendMessage("Quantity: " + myRs.getString("quantity")).queue();
                    //event.getChannel().sendMessage("Wholesale: " + myRs.getString("wholesale_price")).queue();
                    event.getChannel().sendMessage("Sale Price: " + myRs.getString("sale_price")).queue();
                    //event.getChannel().sendMessage("Supplier ID: " + myRs.getString("supplier_id")).queue();

                    while (myRs.next()) {

                        event.getChannel().sendMessage("===========================\n").queue();
                        //event.getChannel().sendMessage("Product ID: " + myRs.getString("product_id")).queue();
                        event.getChannel().sendMessage("Product Title: " + myRs.getString("product_title")).queue();
                        event.getChannel().sendMessage("Product Description: " + myRs.getString("product_description")).queue();
                        event.getChannel().sendMessage("Quantity: " + myRs.getString("quantity")).queue();
                        //event.getChannel().sendMessage("Wholesale: " + myRs.getString("wholesale_price")).queue();
                        event.getChannel().sendMessage("Sale Price: " + myRs.getString("sale_price")).queue();
                        //event.getChannel().sendMessage("Supplier ID: " + myRs.getString("supplier_id")).queue();

                    }

                    event.getChannel().sendMessage("===========================\n").queue();
                    event.getChannel().sendMessage("*** DONE LOADING LIST OF PRODUCTS ***").queue();
                }
                else {
                    event.getChannel().sendMessage("There was an error somewhere!\n").queue();
                }
            } catch (SQLException e) {

                // TODO Auto-generated catch block
                event.getChannel().sendMessage("Oops, Error!\n").queue();
                e.printStackTrace();
            }
        }
    }
}
