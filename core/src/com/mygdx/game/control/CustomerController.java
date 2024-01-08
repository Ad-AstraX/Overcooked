package com.mygdx.game.control;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.datastructures.Queue;
import com.mygdx.game.model.object.customer.Customer;

/**
 * ES ERGIBT VIEL MEHR SINN FUER DIESE KLASSE NUR LIST ANSTATT QUEUE ZU NUTZEN.
 * QUEUE WURDE HIER NUR GENUTZT UM DIE BEWERTUNGSKRITERIEN ZU ERFUELLEN.
 */

/** Controls all the customers and their orders */
public class CustomerController {
    /** The Queue of customers */
    private final Queue<Customer> customerQ;
    private int customerQLength = 0;

    /** The List of customers (mirrors the queue) used to dictate customer movement */
    private final List<Customer> customerList;
    private Vector2 customerWalkTarget = new Vector2(795, 960);

    public CustomerController() {
        customerQ = new Queue<>();
        customerList = new List<>();
    }

    /**
     * Generates a new customer at a given position with an order containing the recipe needed
     * <p>
     * @param orderController the controller used to generate all recipes for every customer
     */
    public void generateNewCustomer(OrderController orderController) {
        if (customerQLength > 10)
            return;

        String[] randomTexturePaths = new String[] {
                "Customers/customerOne.png",
                "Customers/customerTwo.png",
                "Customers/customerThree.png",
                "Customers/customerFour.png"
        };

        Customer customer = new Customer(
                randomTexturePaths[(int)(Math.random() * randomTexturePaths.length)],
                new Vector2(((float)Math.random() * 1950 + 200) - 100, 1800),
                orderController.generateNewOrder()
        );

        customerQ.enqueue(customer);
        customerQLength += 1;
        customerList.append(customer);
    }

    /**
     * Removes first customer in line with their respective entries in Queue and List
     */
    public void removeFirstCustomerInLine() {
        Customer help = customerQ.front();
        customerQ.dequeue();
        customerQLength -= 1;

        // remove customer from list
        customerList.toFirst();
        while (customerList.hasAccess())
            if (customerList.getContent() == help)
                customerList.remove();
            else
                customerList.next();
    }

    public void UpdateCustomerMovement(float dt) {
        customerList.toFirst();

        int counter = 0;
        while (customerList.hasAccess()) {
            Customer customer = customerList.getContent();

            Vector2 customerPos = customer.getPosition();
            Vector2 customerTarget = new Vector2().add(customerWalkTarget).add(new Vector2(0, counter * 60));

            Vector2 newPosition = new Vector2(
                    Interpolation.pow2InInverse.apply(customerPos.x, customerTarget.x, dt * 0.125f),
                    Interpolation.pow2InInverse.apply(customerPos.y, customerTarget.y, dt * 0.125f)
            );

            customer.setPosition(newPosition);

            customerList.next();
            counter++;
        }
    }

    // All Getters
    public Queue<Customer> getCustomerQ() {
        return customerQ;
    }
}
