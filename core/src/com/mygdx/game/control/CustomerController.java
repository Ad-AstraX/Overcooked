package com.mygdx.game.control;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.datastructures.Queue;
import com.mygdx.game.model.object.customer.Customer;
import com.mygdx.game.view.Main;

/**
 * Controls all the customers and their orders
 */
public class CustomerController {
    private final Queue<Customer> customerQ;

    public CustomerController() {
        customerQ = new Queue<>();
    }

    /**
     * Generates a new customer at a given position with a
     * random amount (1-3) of recipes in its order
     * <p>
     * @param orderController the controller used to generate all recipes for every customer
     */
    public void generateNewCustomer(OrderController orderController) {
        Customer customer = new Customer(
                new Vector2(0, 0),
                orderController.generateNewOrder((int)((Math.random() + 1) * 3)));

        customerQ.enqueue(customer);
        Main.getWorldObjectList().append(customer);
    }

    public Queue<Customer> getCustomerQ() {
        return customerQ;
    }
}
