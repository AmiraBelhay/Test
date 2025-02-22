package main;

import entities.offer.*;
import entities.community.*;
import entities.news.*;
import entities.event.*;
import entities.shop.*;
import entities.users.*;
import services.offer.*;
import services.community.*;
import services.news.*;
import services.event.*;
import services.shop.*;
import services.users.*;
import utils.MyDatabase;
import java.time.LocalDateTime;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MyDatabase db = MyDatabase.getInstance();

        //product
        // beech nesti beha el create
        //Product p1 = new Product("GymShark", 200, 5, "JIHIGYUFTYDFRTCGHBJKNL", "jhgvfytvghvhgghbhbg", 1, "amiraamira", 7);
        // bech nestiw el update
        //Product p1 = new Product(1,"Shark", 200, 5, "JIHIGYUFTYDFRTCGHBJKNL", "jhgvfytvghvhgghbhbg", 1, "amiraamira", 7);

        //ProductServices ps = new ProductServices();

        /*try {
            //ps.create(p1); // create
            //ps.update(p1); // update
            ps.delete(1); // delete
            System.out.println(ps.readList()); // read
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/


        // cart
        /*Cart cart = new Cart(1,null,12);
        Cart cart1 = new Cart(2,1,null,13);
        cart_services service = new cart_services();
        try {
            //service.create(cart,1);
            //service.update(cart1);
            service.delete(2);
            System.out.println(service.readList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/



        // cartitem
        /*Cartitem cartitem = new Cartitem(1,1,7,3.7);
        Cartitem cartitem1 = new Cartitem(1,1,1,8,3.7);
        caritem_services service  = new caritem_services();
        try {
            //service.create(cartitem);
            //service.update(cartitem1);
            service.delete(2);
            System.out.println(service.readList());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/


        // command
        /*Command command = new Command(1,1,"cash",null);
        Command command1 = new Command(2,1,1,null, Command.Status.Confirmed,"Cash");

        command_services services = new command_services();
        try {
            //services.create(command);
            //services.update(command1);
            services.delete(2);
            System.out.println(services.readList());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/




    }
}
