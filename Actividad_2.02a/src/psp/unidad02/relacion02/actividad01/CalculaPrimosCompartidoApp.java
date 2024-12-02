package psp.unidad02.relacion02.actividad01;

import psp.common.parameters.Parameters;

/**
 * Application Main Class
 */
public class CalculaPrimosCompartidoApp {
  
  // Command line parameters
  private Parameters parameters;
  // Worker Manager
  private WorkerManager workerManager;

  /**
   * Main entry point
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    
    // Creates object of this class and runs it
    CalculaPrimosCompartidoApp app = new CalculaPrimosCompartidoApp();
    app.run(args);
  }

  /**
   * Private run. Like main but it can use object attributes
   * @param args Command line arguments
   */
  private void run(String[] args) {
    // Process arguments
    parameters = new CalculaPrimosCompartidoAppParameters();
    
    // If arguments checks
    if (parameters.processArguments(args)) {
      // Creates worker manager
      workerManager = new WorkerManager(parameters.getIntParameter(0), parameters.getIntParameter(1));
      // Perform calculation using threads managed by worker manager
      workerManager.performCalculation();
      // Obtains results
      PrimeSet primeSet = workerManager.getResults();
      // Print results
      PrimePrinter printer = new PrimePrinter(primeSet);
      printer.print();
    } else {
      // If incorrect parameter, show usage message and ends
      System.err.println("Incorrect parameters.");
      System.err.println("Usage: CalculaPrimosCompartidoApp minRange maxRange");
      System.err.println("  minRange. Required. Positive Integer. Lower limit of range to search for prime numbers (included)");
      System.err.println("  maxRange. Required. Positive Integer. Upper limit of range to search for prime numbers (included)");
    }
  }

}
