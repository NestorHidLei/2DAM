package psp.unidad02.relacion02.actividad02;

import psp.common.parameters.Parameters;
import psp.common.parameters.ParametersException;

public class PizzeriaLuigiParameters implements Parameters {

  // Default values
  private static final int DEFAULT_WORKERS = 2;
  private static final int DEFAULT_CLIENTS = 5;
  
  // Workers
  private int workers;
  // Clients
  private int clients;

  @Override
  public boolean processArguments(String[] arguments) {
    // If first not given, switch to default value
    if (arguments.length < 1) {
      workers = DEFAULT_WORKERS;
    } else {
      try {
        // Tries to parse argument
        workers = Integer.parseInt(arguments[0]);
        // If ok but less than 1
        if (workers < 1) {
          // Error
          return false;
        }
      } catch (NumberFormatException e) {
        // If argument can not bet parsed as integer, error
        return false;
      }
    }

    // If second not given, switch to default value
    if (arguments.length < 2) {
      clients = DEFAULT_CLIENTS;
    } else {
      try {
        // Tries to parse argument
        clients = Integer.parseInt(arguments[1]);
        // If ok but less than 1
        if (clients < 1) {
          // Error
          return false;
        }
      } catch (NumberFormatException e) {
        // If argument can not bet parsed as integer, error
        return false;
      }
    }
    
    // If we reached here, all is OK
    return true;
  }

  @Override
  public int getIntParameter(int position) {
    if (position == 0) {
      return workers;
    }
    if (position == 1) {
      return clients;
    }
    // Invalid parameter
    throw new ParametersException("Invalid position");
  }

  @Override
  public long getLongParameter(int position) {
    // Invalid parameter
    throw new ParametersException("Invalid position");
  }

  @Override
  public double getDoubleParameter(int position) {
    // Invalid parameter
    throw new ParametersException("Invalid position");
  }

  @Override
  public String getStringParameter(int position) {
    // Invalid parameter
    throw new ParametersException("Invalid position");
  }

}
