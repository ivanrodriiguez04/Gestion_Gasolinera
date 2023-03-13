package Gasolinera;


import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Gasolineria 
{
	//Metodo de una gasolinera la opcion 1 y 2
	public Gasolineria gasolinera(boolean ok, ArrayList<Gasolineria>bd)
	{
		//Creamos un objeto gasoVacio para poder devolver el tipo de obeto y poder añadirlo a la base de datos
		Gasolineria gasoVacio = new Gasolineria();
		 Scanner pedir = new Scanner(System.in);
		 //Construccion de la fecha y hora en este momento
		 String dateTime = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a")
                 .format(LocalDateTime.now());
		 
		 
		 //Pedimos los litros
		 System.out.println("Introduce los litros:");
		
		 //Implementamos los litros en la base de datos
		 gasoVacio.setLitos(pedir.nextDouble());
		 
		 //Pedimos el importe
		 System.out.println("Introduce el importe:");
		 
		 //Implementamos el importe en la base de datos
		 gasoVacio.setImporte(pedir.nextDouble());
		//añadir una id 
		 gasoVacio.setIdentificador(calculoIdAlumno(bd));
		 //Implementamos la fecha y hora en la base de datos
		 gasoVacio.setFecha(dateTime);
		//Saber si es factura
		gasoVacio.setFacturaONormal(ok);		    
		 //If si la persona introduce la 2 opcion pasa y si no pues va por el else
		if(ok)
		{
			System.out.println("Introduce tu dni:");
			//Implementamos el dni
			gasoVacio.setDni(pedir.next());
			System.out.println("Introduce tu matricula:");
			//Implementamos la matricula
			gasoVacio.setMatricula(pedir.next());
			
		}
		else
		{
			//En este caso solo entra cuando la persona ha elegido la 1 opcion y no se le tiene que pedir ni el dni ni la matricula
			
			//Guardamos dni como si fuera nulo
			gasoVacio.setDni(null);
			//Guardamos matricula como si fuera nula 
			gasoVacio.setMatricula(null);
			
		}
		//Devolvemos gasoVacio para que en la llamada se pueda implementar en una lista del mismo tipo que en este caso es Gasolineria
		return gasoVacio;
	}
	//Mostrar repostaje opcion 3
		public void mostrardatosgasolinera(ArrayList<Gasolineria>bd)
		{	//Un bucle forech para sacar todos los datos de la base de datos de gasolinera
			//Dentro del bloque recorre toda la lista bd con un atributo del tipo Gasolina que es el mismo que el de la base de datos
			for(Gasolineria gaso:bd)
			{
				gaso.toString();
			}
		}
		public ArrayList<Gasolineria> eliminarepostajes(ArrayList<Gasolineria>bd)
		{
			System.out.print("Introduce el id del repostaje que desea eliminar:");
			Scanner scanID=new Scanner(System.in);
			int idIntroducir=scanID.nextInt();
			for(Gasolineria gaso: bd) 
			{
				 if(gaso.getIdentificador() == 1) 
				 {
					 bd.remove(idIntroducir);
					 if(bd.size()==0) 
						 break; 
				  } 
			}
			return bd;
		}
		public void mostrarrepostajesdividido(ArrayList<Gasolineria>bd)
		{
			Scanner modificar = new Scanner(System.in);
				boolean ok;
				do 
				{
					ok=true;
					
					System.out.println("Introduce 1 para repostaje normal y 2 para repostaje con factura");
					int numero=modificar.nextInt();
					switch (numero) 
					{
					//si es repostaje normal
					case 1:
						System.out.println("--------Repostajes Normales--------");
						for(Gasolineria gaso:bd)
						{

							
								//en caso de false va a mostrar solo los normales
								if(gaso.isFacturaONormal()==false)
									System.out.println("Id: "+gaso.getIdentificador()+" Importe: "+gaso.getImporte()+", Litros: "+gaso.getLitos()+", fecha: "+gaso.getFecha());
									
						}
						break;
					//si es repostaje con factura
					case 2:
						System.out.println("--------Repostajes con Factura--------");
						for(Gasolineria gaso:bd)
						{
								if(gaso.isFacturaONormal()==true)
									System.out.println("Id: "+gaso.getIdentificador()+" Dni: "+gaso.getDni()+", Importe: "+gaso.getImporte()+", Litros: "+gaso.getLitos()+", Matricula: "+gaso.getMatricula()+", fecha: "+gaso.getFecha());
						}
						break;
						default:
							ok=false;
					}	
						
					
					
				}while(!ok);
				
		}
		public ArrayList<Gasolineria> modificarrepostaje(ArrayList<Gasolineria>bd)
		{
			boolean ok;
			String dateTime = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a")
	                 .format(LocalDateTime.now());
			do 
			{
				
				ok=true;
				Scanner modificar = new Scanner(System.in);
				System.out.println("Introduce el inditifador del repostaje que deseas modificar:");
				int repostajedeseado=modificar.nextInt();
				for(Gasolineria gaso: bd) 
				{
					if(gaso.getIdentificador()==repostajedeseado)
					{
						System.out.println("Las sitas que quieres modificar es: "+gaso.toString()+"[S=Si o N=No]");
						String siono=modificar.next().toUpperCase();
						switch(siono)
						{
						case "S":case "SI":
							System.out.println("Introduce el nuevo dni nuevo: ");
							String dni=modificar.next();
							gaso.setDni(dni);
							System.out.println("Introduce la nueva matricula nuevo: ");
							String matricula=modificar.next();
							gaso.setMatricula(matricula);
							System.out.println("Introduce los nuevos litros nuevo: ");
							double litros =modificar.nextDouble();
							gaso.setLitos(litros);
							System.out.println("Introduce el importe nuevo nuevo: ");
							double importe =modificar.nextDouble();
							gaso.setImporte(importe);
							gaso.setFecha(dateTime);
							if(gaso.getDni()!=null&&gaso.getMatricula()!=null)
								gaso.setFacturaONormal(true);
							break;
						case "N":case "NO":
							break;
							default:
								ok=false;
						}
					}
				}
			
				
			}while(!ok);
			return bd;
		}
			
		private int calculoIdAlumno(ArrayList<Gasolineria> listaAntigua) {
			int auxiliar = 0;
			for(int i = 0; i< listaAntigua.size(); i++) {
				int j = listaAntigua.get(i).getIdentificador();
				if(auxiliar<j) {
					auxiliar = j;
				}
			}
			return auxiliar + 1;
		}
		
		
	
	//--------------------------ATRIBUTOS, GETTERS Y SETTERS, OBJETOS TIPO GASOLINA------------------------------------
	
	
	//Mi objeto gasolinera en uno no esta vacio mientras que en el otro lo tengo que declarar vacio 
	public Gasolineria(String dni, String fecha, String matricula, double litos,double importe) 
	{
		super();
		this.dni = dni;
		this.fecha = fecha;
		this.matricula = matricula;
		this.litos = litos;
		this.importe = importe;
	}
	public Gasolineria() 
	{
		super();
		
	}
	//Los atributos de mi gasolinera
	private String dni;
	private String fecha;
	private String matricula;
	private double litos;
	private double importe;
	private int identificador = 0;
	private int contador;
	private boolean facturaONormal;
	//Los getters y los setters de los atributos
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) 
	{
		this.fecha = fecha;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public double getLitos() {
		return litos;
	}
	public void setLitos(double litos) {
		this.litos = litos;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public boolean isFacturaONormal() {
		return facturaONormal;
	}
	public void setFacturaONormal(boolean facturaONormal) {
		this.facturaONormal = facturaONormal;
	}
	public String toString() {
		return "Repostaje [id=" + identificador + ", litros=" + litos + ", importe=" + importe + ", fchRepostaje=" + fecha
				+ ", dni=" + dni + ", matricula=" + matricula + "]";
	}
}
