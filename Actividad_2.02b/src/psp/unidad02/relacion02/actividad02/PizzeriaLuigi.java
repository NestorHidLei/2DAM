package psp.unidad02.relacion02.actividad02;

import java.util.ArrayList;
import java.util.List;
import psp.common.parameters.Parameters;

public class PizzeriaLuigi {

  // Parameters
  Parameters parameters;
  // Workers
  List<WorkerThread> workers;
  // Clients
  List<ClientThread> clients;
  
  // Pizza tray
  Tray tray;
  
  public static void main(String[] args) {
    
    PizzeriaLuigi pizzeria = new PizzeriaLuigi();
    pizzeria.run(args);
  }

  private void run(String[] args) {
    // Process parameters
    parameters = new PizzeriaLuigiParameters();
    
    // If parameters ok
    if (parameters.processArguments(args)) {
      
      // Creates tray
      tray = new Tray();

      // Creates workers
      createWorkers();
      
      // Creates clients
      createClients();
      
      // Runs simulation
      runSimulation();
      
    } else {
      // Show usage info and exit
      usage();
    }
  }

  private void runSimulation() {
    // Starts all threads
    for (WorkerThread thread: workers) {
      thread.start();
    }
    for (ClientThread thread: clients) {
      thread.start();
    }
    
    // Wait for clients to end
    for (ClientThread thread: clients) {
      try {
        thread.join();
      } catch (InterruptedException e) {}
    }
    
    // Ends all workers
    for (WorkerThread thread: workers) {
      // Ends it
      thread.endJob();
    }
    
    // It does not wait for workers to end because we don't care
  }

  /**
   * Creates worker threads
   */
  private void createWorkers() {
    // Create workers list
    workers = new ArrayList<>();
    // for each worker to create
    for (int i = 0; i < parameters.getIntParameter(0); i++) {
      // Creates worker thread, add it to list and starts it
      WorkerThread thread = new WorkerThread(i, tray);
      workers.add(thread);
    }
  }

  /**
   * Creates client threads
   */
  private void createClients() {
    // Create client list
    clients = new ArrayList<>();
    // for each client to create
    for (int i = 0; i < parameters.getIntParameter(1); i++) {
      // Creates client thread, add it to list and starts it
      ClientThread thread = new ClientThread(i, tray);
      clients.add(thread);
    }
  }
  
  /**
   * Shows usage info
   */
  private void usage() {
    System.err.println("Incorrect parameters.");
    System.err.println("Usage: PizzeriaLuigi workers clients");
    System.err.println("  workers. Optional. Default value 2. Positive Integer. Number of pizza parlor workers. Should be greater than or equal to one");
    System.err.println("  clients. Optional. Default value 5. Positive Integer. Number of clients. Should be greater than or equal to one");
  }

}
