package psp.unidad02.relacion02.actividad02;

/**
 * Pizza tray. Keeps made pizzas for clients to take
 */
public class Tray {

  // Number of pizzas in tray
  private int count;
  
  /**
   * Constructor. Creates an empty try (zero pizzas in it)
   */
  public Tray() {
    count = 0;
  }
  
  /**
   * Puts a new pizza in the tray
   * @return Number of pizzas in tray after putting this one
   */
  public synchronized int putPizza() {
    // Adds one more pizza to tray
    count++;
    // And returns count
    return count;
  }
  
  /**
   * Tries to get a pizza from tray.
   * @return true if pizza taken. false if tray was empty
   */
  public synchronized boolean getPizza() {
    // If pizzas in tray
    if (count > 0) {
      // Takes one
      count--;
      // And returns true
      return true;
    } else {
      // No pizzas in tray
      return false;
    }
  }
  
}
