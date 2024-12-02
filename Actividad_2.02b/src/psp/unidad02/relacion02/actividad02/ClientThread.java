package psp.unidad02.relacion02.actividad02;

/**
 * Client thread. Represents a client
 */
public class ClientThread extends Thread {

  // Minimum and maxiimum times to wait (after eating or not)
  private static final long MIN_WAIT_AFTER_EATING = 20000;
  private static final long MAX_WAIT_AFTER_EATING = 30000;
  private static final long MIN_WAIT_NO_EATING = 10000;
  private static final long MAX_WAIT_NO_EATING = 15000;

  // Number of pizzas to eat
  private static final int PIZZAS_TO_EAT = 5;
  
  // Thread id
  private int id;
  
  // Shared pizza tray
  Tray tray;
  
  // Pizzas to go
  private int pizzasToGo;

  /**
   * Constructor
   * @param id Client id (for messages)
   * @param tray Tray where to get pizzas from
   */
  public ClientThread(int id, Tray tray) {
    this.id = id;
    this.tray = tray;
    // Initializes pizzas to go
    this.pizzasToGo = PIZZAS_TO_EAT;
  }

  @Override
  public void run() {
    // Starting message
    message("Starting");
    
    // While pizzas to eat
    while (this.pizzasToGo > 0) {
      // Try to get a pizza
      message("Trying to get a pizzas");
      if (tray.getPizza()) {
        // Got pizza
        // One less
        this.pizzasToGo--;
        // Shows message
        message("Pizza taken. " + this.pizzasToGo + " pizzas to go");
        // If pizzas to go
        if (this.pizzasToGo > 0) {
          // Take a long walk
          message("Taking a long walk");
          longWalk();
        }
      } else {
        // No pizza
        message("No pizzas on tray");
        // Taking a short walk
        message("Taking a short walk");
        shortWalk();
      }
    }
    message ("No more pizzas to eat. Terminating");
  }

  private void shortWalk() {
    // Calculates time to wait
    long timeToWait = (long)(Math.random() * (MAX_WAIT_NO_EATING - MIN_WAIT_NO_EATING + 1) + MIN_WAIT_NO_EATING);
    // And waits it
    try {
      sleep(timeToWait);
    } catch (InterruptedException e) {}
  }

  private void longWalk() {
    // Calculates time to wait
    long timeToWait = (long)(Math.random() * (MAX_WAIT_AFTER_EATING - MIN_WAIT_AFTER_EATING + 1) + MIN_WAIT_AFTER_EATING);
    // And waits it
    try {
      sleep(timeToWait);
    } catch (InterruptedException e) {}
  }

  private void message(String messageText) {
    System.out.println("Client (" + this.id + ") - " + messageText);
  }
  

}
