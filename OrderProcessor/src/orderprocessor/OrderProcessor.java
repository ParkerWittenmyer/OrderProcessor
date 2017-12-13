/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author pwittenmyer
 */
public class OrderProcessor {
    
    public static void main(String[] args) 
    {
        try(BufferedReader br = new BufferedReader(new FileReader("Orders.txt")))
        {
            br.readLine(); //reads the headings
            
            System.out.println("Processing order(s)...");
            String line = br.readLine();
            
            try {PrintWriter outputfile = new PrintWriter(new BufferedWriter(new FileWriter("OrdersProcessed.txt", true)));
                while(line != null)
                {
                    String[] items = line.split("\\|");
                    String orderId = items[0];
                    String partnum = items[1];
                    double price = Double.valueOf(items[2]);
                    int quantity = Integer.parseInt(items[3]);
                    double subtotal = quantity * price;
                    double tax = CalcTax(subtotal);
                    double shipping = CalcShipping(subtotal);
                    double total = subtotal + shipping + tax;
                    
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Part Number: " + partnum);
                    System.out.println("Price : " + price);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Subtotal: " + subtotal);
                    System.out.println("Tax: " + tax);
                    System.out.println("Shipping: "+ shipping); 
                    System.out.println("Total: " + total);
                    System.out.println("\n");
                    line = br.readLine();
                    
                    outputfile.println(orderId);
                    outputfile.println(partnum);
                    outputfile.println(price);
                    outputfile.println(quantity);
                    outputfile.println(subtotal);
                    outputfile.println(tax);
                    outputfile.println(shipping);
                    outputfile.println(total);
                }
            }
            catch(IOException ioe)
            {
                System.out.println("Error! " + ioe);
                System.exit(0);
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            System.out.println("Finished processing order(s).");
        }
    }
    
    public static double CalcTax(double subtotal)
    {
        final double TAX = 0.02;
        double itemTax = subtotal * TAX;
        return itemTax;
    }
    
    public static double CalcShipping(double subtotal)
    {
        final double SHIP = 0.05;
        double itemShip = subtotal * SHIP;
        return itemShip;
    }
}