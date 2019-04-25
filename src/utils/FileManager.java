package utils;

import java.io.*;
/*
 * Este es un manager de archivos, donde puedo escribir y leer archivos de una manera mas
 * abreviada en otra clase
 * tiene una ruta de archivo y una linea, la cual se usa solo si se va a escribir
 */

public class FileManager {
	
	String filePath;
	String line;
	
	public FileManager() {
		//no se para que se utilizaria este constructor pero por las dudas ahi esta
		filePath="";
		line="";	
	}
	
	public FileManager(String filePath) {
		
		this.filePath = filePath;
		line="";
	}
	
	public FileManager(String filePath,String line) {
		
		this(filePath);
		this.line = line;
	}
	
	
	public String fileRead() {//lee todo el archivo
		
		String aux;
		String total="";
		File file = new File(filePath);
		boolean firstLine = true;
		
		try {
			
			FileReader fget = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(fget);//permite leer informacion desde un buffer
			
			try {
				
				while((aux = buffReader.readLine()) != null) {//mientras que la linea leida no sea null, de paso lo igualo ahi a mi aux
					
					if(firstLine) {
						
						total = total + aux;
						firstLine = false;
						
					}else {
					
					total = total + "\n" + aux;
					
					}
				}
				
			} catch (IOException err) {
				
				// TODO Auto-generated catch block
				System.out.println(err.getMessage());
				total = err.getMessage();
			}
			
		}catch(FileNotFoundException err) {
			
			System.out.println(err.getMessage());
			total = err.getMessage();
		}
		
		return total;
	}
	
	public String fileRead(int nLine) {//lee la linea especificada
		
		String aux;
		String total="";
		File file = new File(filePath);
		int auxLine = 0;
		
		try {
			
			FileReader fget = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(fget);//permite leer informacion desde un buffer
			
			try {
				
				while(((aux = buffReader.readLine()) != null)) {//mientras que la linea leida no sea null, de paso lo igualo ahi a mi aux
					
					if(auxLine == nLine) {
				
						total = total + aux;
					
					}
					
					auxLine++;
				}
				
			} catch (IOException err) {
				
				// TODO Auto-generated catch block
				System.out.println(err.getMessage());
				total = err.getMessage();
			}
			
		}catch(FileNotFoundException err) {
			
			System.out.println(err.getMessage());
			total = err.getMessage();
		}
		
		return total;
	}

	public String fileReadUntil(int nLineStart,int nLineEnd) {//lee desde una linea especificada hasta otra

		String aux;
		String total="";
		File file = new File(filePath);
		int auxLine = 0;
		boolean firstLine=true;

		try {

			FileReader fget = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(fget);//permite leer informacion desde un buffer

			try {

				while(((aux = buffReader.readLine()) != null)) {//mientras que la linea leida no sea null

					if((auxLine >= nLineStart) && (auxLine <= nLineEnd)) {

						if(firstLine) {

							total = total + aux;
							firstLine = false;

						}else {

							total = total + "\n" + aux;

						}

					}

					auxLine++;
				}

			} catch (IOException err) {

				// TODO Auto-generated catch block
				System.out.println(err.getMessage());
				total = err.getMessage();
			}

		}catch(FileNotFoundException err) {

			System.out.println(err.getMessage());
			total = err.getMessage();
		}

		return total;

	}
	
	public void fileWrite() {//imprime la linea en el archivo, o crea uno nuevo y escribe
		
		Writer input;
		File file = new File(filePath);

		if(file.exists()) {

			try {
				input = new BufferedWriter(new FileWriter(filePath, true));//el booleano indica que solo queremos agregar lineas, no borrar

				if (fileRead() == "") {
					//si el archivo esta vacio entonces no se imprime un \n junto con la linea
					//para no dejar un espacio en blanco arriba del archivo
					input.append(line);

				} else {

					input.append("\n" + line);
				}

				input.close();

			} catch (IOException err) {

				System.out.println(err.getMessage());

			}

		}else {

			try {

				FileOutputStream fos = new FileOutputStream(filePath);
				fos.write(line.getBytes());
				fos.flush();
				fos.close();

			}catch (IOException err) {

				System.out.println(err.getMessage());

			}


		}


	}
	
	public void removeLine(int nLine) {//remueve la linea especificada

		String aux;
		String newTotal="";
		String lineToRemove = fileRead(nLine);
		File file = new File(filePath);

		boolean firstLine = true;

		try {

			FileReader fget = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(fget);//permite leer informacion desde un buffer

			try {

				while((aux = buffReader.readLine()) != null) {//mientras que la linea leida no sea null, de paso lo igualo ahi a mi aux

					if(!aux.equals(lineToRemove)) {//si la linea leida no es igual a la que queremos remover...

						//entonces se agrega al nuevo contenido
						if(firstLine) {

							newTotal = newTotal + aux;
							firstLine = false;

						}else {

							newTotal = newTotal + "\n" + aux;

						}

					}

				}

			} catch (IOException err) {

				System.out.println(err.getMessage());

			}

		}catch(FileNotFoundException err) {

			System.out.println(err.getMessage());

		}

		//se borra el anterior contenido
		fileFlush();

		//se imprime el nuevo contenido sin la linea removida
		line = newTotal;
		
		fileWrite();

	}

	
	public void fileFlush() {
		
		//se borra el contenido del archivo
		PrintWriter pw;
		try {
			pw = new PrintWriter(filePath);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int fileLength() {
		//devuelve la cantidad de lineas en el archivo
		int fl=0;
		File file = new File(filePath);
		
		try {
			
			FileReader fget = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(fget);//permite leer informacion desde un buffer
			
			try {
				
				while((buffReader.readLine()) != null) {//mientras que la linea leida no sea null, de paso lo igualo ahi a mi aux
					
					fl++;
				}
				
			} catch (IOException err) {
				
				// TODO Auto-generated catch block
				System.out.println(err.getMessage());
				
			}
			
		}catch(FileNotFoundException err) {
			
			System.out.println(err.getMessage());
			
		}
		
		
		return fl;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "FileManager [filePath=" + filePath + ", line=" + line + "]";
	}

	public boolean equals(FileManager fm) {
		
		return((filePath==fm.getFilePath())&&(line==fm.getLine()));
	}

}
