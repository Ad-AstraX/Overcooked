package com.mygdx.game.control;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.datastructures.Queue;
import com.mygdx.game.model.object.customer.Customer;

public class CustomerController {
    private final Queue<Customer> customerQ;

    public CustomerController() {
        customerQ = new Queue<>();
    }

    public void generateNewCustomer(OrderController orderController) {
        Customer customer = new Customer(
                new Vector2(0, 0),
                orderController.generateNewOrder((int)((Math.random() + 1) * 3)));

        customerQ.enqueue(customer);
    }
}
