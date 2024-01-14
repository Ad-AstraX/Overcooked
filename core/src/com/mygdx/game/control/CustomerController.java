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

/** Controls all the customers and their orders
 * <p>
 * ES ERGIBT VIEL MEHR SINN FÜR DIESE KLASSE NUR LIST ANSTATT QUEUE ZU NUTZEN.
 * QUEUE WURDE HIER NUR GENUTZT UM DIE BEWERTUNGSKRITERIEN ZU ERFÜLLEN.
 * */
public class CustomerController {
    /** The Queue of customers */
    private final Queue<Customer> customerQ;
    /** The Queue's length (tracked here because the Queue implementation of NRW is trash) */
    private int customerQLength = 0;

    /** The List of customers (mirrors the queue) used to dictate customer movement */
    private final List<Customer> customerList;
    /** The final destination fo the customer after walking in */
    private final Vector2 customerWalkTarget = new Vector2(780, 960);
    /** The display of the order */
    private BackgroundObject orderDisplay;
    /** The sound that is played when a customer enters the shop */
    private final Sound newCustomerChime = Gdx.audio.newSound(Gdx.files.internal("Sound/newCustomerSound.mp3"));
    /** The progressbar which displays the customer's patience (outline and filled rectangle) */
    private final RectangleColored[] patienceProgressBars;

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

        if (customer.equals(customerQ.front())) prepareOrder();
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
        if (!customerQ.isEmpty()) prepareOrder();
    }

    /** Instantiates the order object, along with the new progressBars for the new customer */
    private void prepareOrder() {
        orderDisplay = new BackgroundObject("Customers/order.png", new Vector2(1575, 0), new Vector2(200*1.8f, 260*1.8f));
        Main.getStaticObjectLists()[1].append(orderDisplay);

        patienceProgressBars[0] = new RectangleColored(ShapeRenderer.ShapeType.Filled, 1720, 0, 170, 15, 0, 0.5f, 0, 1);
        patienceProgressBars[1] = new RectangleColored(ShapeRenderer.ShapeType.Line, 1720, 0, 170, 15, 0, 0, 0, 1);
        Main.getAllRectangles().append(patienceProgressBars[0]);
        Main.getAllRectangles().append(patienceProgressBars[1]);
    }

    /** Updates the movement of the customer and the order display. Called once in the GameController */
    public void updateCustomerAndOrder(float dt) {
        if (GameController.singleton.getGame().getTimeLeft() > 0 && GameController.singleton.getGame().getPayTotal() < GameController.singleton.getGame().getPayGoal()) {
            updateCustomerMovement(dt);
            updateCustomerPatience(dt);
            updateMovement(orderDisplay, new Vector2(1575, 860), Vector2.Zero, dt);

            if (patienceProgressBars[0] != null)
                patienceProgressBars[0].setPosition(orderDisplay.getPosition().x + 150, orderDisplay.getPosition().y + 330);
            if (patienceProgressBars[1] != null)
                patienceProgressBars[1].setPosition(orderDisplay.getPosition().x + 150, orderDisplay.getPosition().y + 330);
        }
    }

    /** Updates the movement of the customers (so they can go to their respective places in the queue) */
    private void updateCustomerMovement(float dt) {
        customerList.toFirst();
        int counter = 0;
        while (customerList.hasAccess()) {
            updateMovement(customerList.getContent(), customerWalkTarget, new Vector2(0, counter*60), dt);
            customerList.next();
            counter++;
        }
    }

    /** Interpolates between two positions */
    private void updateMovement(WorldObject object, Vector2 target, Vector2 addition, float dt) {
        if (!(object != null && !object.getPosition().equals(target)))
            return;

        Vector2 movementTarget = new Vector2().add(target).add(addition);
        Vector2 newPosition = new Vector2(
                Interpolation.pow2InInverse.apply(object.getPosition().x, movementTarget.x, dt * 0.125f),
                Interpolation.pow2InInverse.apply(object.getPosition().y, movementTarget.y, dt * 0.125f)
        );
        object.setPosition(newPosition);
    }

    /** Updates the patience of the customer in the front of the queue */
    private void updateCustomerPatience(float dt) {
        if (!customerQ.isEmpty()) {
            customerQ.front().setPatience((int) (customerQ.front().getPatience()-dt));

            int burgerElements = Utilities.countStackElements(customerQ.front().getOrder().getRecipe().getIngredients());
            patienceProgressBars[0].setWidth(patienceProgressBars[0].getWidth() - dt*(17f/burgerElements));
            
            if(customerQ.front().getPatience() <= 0) nextCustomer();
        }
    }

    /** Clears the queue and the list so that the game can be restarted */
    public void emptyQueue() {
        customerList.toFirst();
        while (!customerQ.isEmpty()) {
            customerQ.dequeue();
            customerList.remove();
        }
        customerQLength = 0;

        System.out.println(Utilities.countListElements(customerList));

        orderDisplay = null;
        patienceProgressBars[0] = null;
        patienceProgressBars[1] = null;
    }

    // All Getters
    public Queue<Customer> getCustomerQ() {
        return customerQ;
    }
    public BackgroundObject getOrderDisplay() {
        return orderDisplay;
    }
}