package psp.unidad02.relacion02.actividad01;

import psp.common.parameters.Parameters;
import psp.common.parameters.ParametersException;

/**
 * Parameters implementation for CalculaPrimosCompartidoApp
 */
public class CalculaPrimosCompartidoAppParameters implements Parameters {

  // Range start (Mandatory)
  private long minRange;
  // Range end (mandatory)
  private long maxRange;
  
  @Override
  public boolean processArguments(String[] arguments) {
    // If parameters length less than 2 is an error
    if (arguments.length < 2) {
      return false;
    }
    
    try {
      // Try to acquire parameters
      minRange = Long.parseLong(arguments[0]);
      maxRange = Long.parseLong(arguments[1]);

      // If range ok, returns true, false otherwise
      return minRange <= maxRange;
    } catch (NumberFormatException e) {
      // If any parameter can not be converted, then it is incorrect
      return false;
    }
  }

  @Override
  public int getIntParameter(int position) {
    throw new ParametersException("Position invalid");
  }

  @Override
  public long getLongParameter(int position) {
    if (position == 0) {
      return minRange;
    } else if (position == 1) {
      return maxRange;
    } else {
      throw new ParametersException("Position invalid");
    }
  }

  @Override
  public double getDoubleParameter(int position) {
    throw new ParametersException("Position invalid");
  }

  @Override
  public String getStringParameter(int position) {
    throw new ParametersException("Position invalid");
  }

}
