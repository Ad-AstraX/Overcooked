package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.BackgroundObject;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.datastructures.Queue;
import com.mygdx.game.model.object.customer.Customer;
import com.mygdx.game.model.utilities.RectangleColored;
import com.mygdx.game.model.utilities.Utilities;
import com.mygdx.game.view.Main;

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
    private final Vector2 customerWalkTarget = new Vector2(780, 960);
    private BackgroundObject orderDisplay;
    private final Sound newCustomerChime = Gdx.audio.newSound(Gdx.files.internal("Sound/newCustomerSound.mp3"));
    private int startPatience;

    private RectangleColored[] patienceProgressBars;

    public CustomerController() {
        customerQ = new Queue<>();
        customerList = new List<>();

        patienceProgressBars = new RectangleColored[2];
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
        newCustomerChime.play(0.8f);

        if (customer.equals(customerQ.front())) {
            orderDisplay = new BackgroundObject("Customers/order.png", new Vector2(1575, 0), new Vector2(200*1.8f, 260*1.8f));
            Main.getStaticObjectLists()[1].append(orderDisplay);

            RectangleColored outline = new RectangleColored(ShapeRenderer.ShapeType.Line, 1720, 0, 165, 25, 0, 0, 0, 1);
            RectangleColored filling = new RectangleColored(ShapeRenderer.ShapeType.Filled, 1720, 0, 165, 25, 0.5f, 0, 0, 1);
            patienceProgressBars[0] = outline;
            patienceProgressBars[1] = filling;
            Main.getAllRectangles().append(patienceProgressBars[0]);
            Main.getAllRectangles().append(patienceProgressBars[1]);
            startPatience = customerQ.front().getPatience();
        }
    }

    /** Removes first customer in line with their respective entries in Queue and List */
    public void nextCustomer() {
        // remove customer from queue
        Customer help = customerQ.front();
        customerQ.dequeue();
        customerQLength -= 1;

        // remove customer from list
        customerList.toFirst();
        while (customerList.hasAccess() && !(customerList.getContent() == help))
            customerList.next();
        customerList.remove();

        Utilities.removeItemList(Main.getStaticObjectLists()[1], orderDisplay);
        Utilities.removeItemList(Main.getAllRectangles(), patienceProgressBars[0]);
        Utilities.removeItemList(Main.getAllRectangles(), patienceProgressBars[1]);
        orderDisplay = null;
        patienceProgressBars[0] = null;
        patienceProgressBars[1] = null;

        // deregister customer from drawing
        help.deregisterFromDrawing();
        if (!customerQ.isEmpty()) {
            startPatience = customerQ.front().getPatience();
            orderDisplay = new BackgroundObject("Customers/order.png", new Vector2(1575, 0), new Vector2(200*1.8f, 260*1.8f));
            Main.getStaticObjectLists()[1].append(orderDisplay);

            RectangleColored outline = new RectangleColored(ShapeRenderer.ShapeType.Line, 1720, 0, 165, 25, 0, 0, 0, 1);
            RectangleColored filling = new RectangleColored(ShapeRenderer.ShapeType.Filled, 1720, 0, 165, 25, 0.5f, 0, 0, 1);
            patienceProgressBars[0] = outline;
            patienceProgressBars[1] = filling;
            Main.getAllRectangles().append(patienceProgressBars[0]);
            Main.getAllRectangles().append(patienceProgressBars[1]);
        }
    }

    public void UpdateCustomerAndOrder(float dt) {
        UpdateCustomerMovement(dt);
        UpdateCustomerPatience(dt);
        UpdateOrderMovement(dt);
        UpdatePatienceBarMovement(dt);
    }

    private void UpdateCustomerMovement(float dt) {
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

    private void UpdateOrderMovement(float dt) {
        if (!(orderDisplay != null && !orderDisplay.getPosition().equals(new Vector2(1575, 860))) )
            return;

        Vector2 orderDisplayTarget = new Vector2().add(new Vector2(1575, 860)).add(new Vector2(0, 60));
        Vector2 newPosition = new Vector2(
                Interpolation.pow2InInverse.apply(orderDisplay.getPosition().x, orderDisplayTarget.x, dt * 0.125f),
                Interpolation.pow2InInverse.apply(orderDisplay.getPosition().y, orderDisplayTarget.y, dt * 0.125f)
        );
        orderDisplay.setPosition(newPosition);
    }

    private void UpdatePatienceBarMovement(float dt) {
        if (!(patienceProgressBars[1] != null && !patienceProgressBars[1].getPosition().equals(new Vector2(1720, 1180))) )
            return;

        Vector2 progressBarTarget = new Vector2().add(new Vector2(1720, 1180)).add(new Vector2(0, 60));
        Vector2 newPosition = new Vector2(
                Interpolation.pow2InInverse.apply(patienceProgressBars[1].getPosition().x, progressBarTarget.x, dt * 0.125f),
                Interpolation.pow2InInverse.apply(patienceProgressBars[1].getPosition().y, progressBarTarget.y, dt * 0.125f)
        );
        patienceProgressBars[1].setPosition(newPosition);
    }

    private void UpdateCustomerPatience(float dt) {
        if (!customerQ.isEmpty()) {
            customerQ.front().setPatience((int) (customerQ.front().getPatience()-dt));

            int burgerElements = Utilities.countStackElements(customerQ.front().getOrder().getRecipe().getIngredients());
            patienceProgressBars[0].setWidth(patienceProgressBars[0].getWidth() - dt*(50f/burgerElements));
            patienceProgressBars[1].setWidth(patienceProgressBars[1].getWidth() - dt*(50f/burgerElements));
            
            if(customerQ.front().getPatience() <= 0) nextCustomer();
        }
    }

    // All Getters
    public Queue<Customer> getCustomerQ() {
        return customerQ;
    }
    public BackgroundObject getOrderDisplay() {
        return orderDisplay;
    }
}
