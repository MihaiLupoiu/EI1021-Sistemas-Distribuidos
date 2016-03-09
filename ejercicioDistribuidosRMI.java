/**
ESCRIBIR UNA APLICACION JAVA RMI PARA QUE UN OBJETO REMOTO DEVUELVA EL TIEMPO

¿Qué hay que hacer? Es un software C/S. El servidor tiene un metodo que proporciona la hora del día. Los clienes puedem acceder. Hay que escribir la interfaz el software de servidor y el de cliente. Numero de puerto: 1234; nombre de host: localhost.
*/


public interface InterfaceEjemplo extends Remote {
	
	public String getHora() throws RemoteException;

}


public class ImplementacionEjemplo extends UnicastRemoteObject
				   implements InterfaceEjemplo {

	public ImplementacionEjemplo() throws RemoteException {
		super();
	}

	@Override
	public String getHora() throws RemoteException {
		return new Date().toString();
	}
}


public class ServidorEjemplo {

	public static void main(String[] args) {
		int numPuerto = 1234;
		String URLreg = "rmi://localhost:" + numPuerto + "/ejemplo";
		
		try {
			ImplementacionEjemplo ie = new ImplementacionEjemplo();
			LocateRegistry.getRegistry(numPuerto).list();
			Naming.rebind(URLreg, ie);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


public class ClienteEjemplo {
	
	public static void main(String[] args) {
		int numPuerto = 1234
		String URLreg = "rmi://localhost:" + numPuerto + "/ejemplo";

		InterfaceEjemplo ie = (InterfaceEjemplo)Naming.lookup(URLreg);
		System.out.println(ie.getHora());
	}
}
