package belatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GestionEbay {
	

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String descr, prec, brand, resultado;
		
		// Create Chrome driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--disable-extensions"); 
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arnaldo\\Documents\\Selenium Jars\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver(options);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// 1.- Abrir Pagina de Ebay
		driver.get("https://www.ebay.com");
		
		// 2.- Buscar el articulo "zapatos" y hace clic en el boton "Buscar"
		driver.findElement(By.id("gh-ac")).sendKeys("zapatos");
		driver.findElement(By.id("gh-btn")).click();
		Thread.sleep(3000);
		
		// 3.- Selecciona en la seccion brand "PUMA"
		//driver.findElement(By.id("e1-51")).click();
		Thread.sleep(3000);
		
		for (int i = 1; i < 9; i++) {
			brand = (driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div["+i+"]/a/span")).getText());
			if(brand.equals("PUMA")){
				driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div["+i+"]/a/span")).click();
				break;
			}
		}
		
		// 4.- Selecciona la talla "10"
		driver.findElement(By.id("e1-27")).click();
		
		// 5.- Imprimir el numero de resultados
		resultado=driver.findElement(By.className("rcnt")).getText();
		System.out.println("EL NUMERO DE RESULTADOS DE LA BUSQUDA ES: "+ resultado);			
		
		// 6.- Ordenar ascendente por precio
		// Selecciona la lista "Ordenar por"
		driver.findElement(By.xpath("//*[@id='DashSortByContainer']")).click();
		// Selecciona de la lista "Precio+Envio mas bajo primero"
		driver.findElement(By.xpath("//*[@id='SortMenu']/li[3]/a")).click();
		Thread.sleep(3000);
		
		System.out.println("\n");

		// 7.- Assert the order taking the first 5 results
		
		// 8.- Take the first 5 products with their prices and print them in console.
		//creating arraylist  
		ArrayList<Producto> prodLista=new ArrayList<Producto>(); 
		descr = (driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div/div[1]/div/div[3]/div/div[1]/div/w-root/div/div/ul/li[1]/div/div[2]/h3/a")).getText());
		prec = (driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div/div[1]/div/div[3]/div/div[1]/div/w-root/div/div/ul/li[1]/div/div[3]/div[2]/span[1]")).getText());
		
		for (int j = 2; j < 7; j++) {		
		prodLista.add(new Producto(descr,prec.substring(3)));	
		descr = (driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div/div[1]/div/div[3]/div/div[1]/div/w-root/div/div/ul/li["+j+"]/div/div[2]/h3/a")).getText());
		prec = (driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div/div[1]/div/div[3]/div/div[1]/div/w-root/div/div/ul/li["+j+"]/div/div[3]/div[2]/div/span[1]/span")).getText());		
		}
		
		System.out.println("IMPRESION DE LOS 5 PRIMEROS PRODUCTOS DE LA BUSQUEDA");
		// Se imprime el contenido del Array 
		  Iterator itr=prodLista.iterator();  
		  while(itr.hasNext()){  
			  Producto pr=(Producto)itr.next();  
		    System.out.println(pr.getDescripcion()+"    "+pr.getPrecio());  
		  }  
		  System.out.println("\n");
	  
		// 9- Order and print the products by name (ascendant) 
  
		  // Se ordena el array
		  Collections.sort(prodLista, new Comparator<Producto>() {
		          @Override
		          public int compare(Producto p2, Producto p1)
		          {
		              //return  p1.descripcion.compareTo(p2.precio);           
		              return  p2.getDescripcion().compareTo(p1.getDescripcion());
		          }
		      });
		  
		System.out.println("PRODUCTOS ORDENADOS POR NOMBRE ASCENDENTE");
		
		// Se imprime el contenido del Array ordenado  
		  Iterator itr1=prodLista.iterator();  
		  while(itr1.hasNext()){  
			  Producto pr=(Producto)itr1.next();
		    System.out.println(pr.getDescripcion()+"    "+pr.getPrecio());  
		  } 
		  System.out.println("\n");
		  
		// 10- Order and print the products by price in descendant mode
		  
		  // Se ordena el array
		  Collections.sort(prodLista, new Comparator<Producto>() {
		          @Override
		          public int compare(Producto p2, Producto p1)
		          {
		              //return  p1.descripcion.compareTo(p2.precio);           
		              return  p1.getPrecio().compareTo(p2.getPrecio());
		          }
		      });
		  
		  System.out.println("PRODUCTOS ORDENADOS POR PRECIO DESCENDENTE");
		  
		// Se imprime el contenido del Array ordenado  
		  Iterator itr11=prodLista.iterator();  
		  while(itr11.hasNext()){  
			  Producto pr=(Producto)itr11.next();
		    System.out.println(pr.getDescripcion()+"    "+pr.getPrecio());  
		  } 		
	}

}
