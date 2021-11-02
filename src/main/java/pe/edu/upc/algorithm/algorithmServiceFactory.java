package pe.edu.upc.algorithm;

import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.FactoryBean;


public class algorithmServiceFactory implements FactoryBean<algorithmService> {

 @Override
 public algorithmService getObject() throws Exception {
	
	//The python classpath is usually set by environment variable 
	 //or included in the java project classpath but it can also be set
	 // programmatically.  Here I hard code it just for the example.
	//This is not required if we use jython standalone JAR 
	 
	PySystemState systemState = Py.getSystemState();
	systemState.path.append(new PyString("C:\\jython2.7.2\\Lib"));
	
	PythonInterpreter interpreter = new PythonInterpreter();
	
	// PARA EL DESPLIEGUE
	//interpreter.execfile("classes/pe/edu/upc/python/algorithmServicePython.py");
	//interpreter.execfile("algorithmServicePython.py");
	
	//PARA EL IDE
	interpreter.execfile("src\\main\\java\\pe\\edu\\upc\\python\\algorithmServicePython.py");
		
	
	PyObject buildingObject = interpreter.get("algorithmServicePython").__call__(); 

//Cast the created object to our Java interface 
	return (algorithmService) buildingObject.__tojava__(algorithmService.class);  
 }

 @Override
 public Class<?> getObjectType() {
 	return algorithmService.class;
 }

}