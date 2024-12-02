package psp.common.parameters;

/**
 * Parameter processor interface. All Parameter processors must implement this
 */
public interface Parameters {

  /**
   * Process arguments and extract them
   * @param arguments argument string from command line
   * @return true if parameters OK. false if any error
   */
  boolean processArguments(String[] arguments);
  
  /**
   * Obtains integer parameter value in given position
   * @param position Position of the parameter in the command line
   * @return Value of parameter, if found.
   * @throws ParametersException If invalid position or parameter is not integer
   */
  int getIntParameter(int position);
  
  /**
   * Obtains long parameter value in given position
   * @param position Position of the parameter in the command line
   * @return Value of parameter, if found.
   * @throws ParametersException If invalid position or parameter is not long
   */
  long getLongParameter(int position);
  
  /**
   * Obtains double parameter value in given position
   * @param position Position of the parameter in the command line
   * @return Value of parameter, if found.
   * @throws ParametersException If invalid position or parameter is not double
   */
  double getDoubleParameter(int position);
  
  /**
   * Obtains String parameter value in given position
   * @param position Position of the parameter in the command line
   * @return Value of parameter, if found.
   * @throws ParametersException If invalid position
   */
  String getStringParameter(int position);
}
