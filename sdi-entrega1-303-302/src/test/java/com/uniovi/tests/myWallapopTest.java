package com.uniovi.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//Ordenamos las pruebas por el nombre del método

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Register;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class myWallapopTest {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox64 = "C:\\Program Files\\MozillaFirefox\\firefox.exe";
	static String Geckdriver022 = "C:\\Path\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas):
	//static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	//static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox64, Geckdriver022);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {

		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);

		WebDriver driver = new FirefoxDriver();

		return driver;
	}
	// Antes de cada prueba se navega al URL home de la aplicaciónn

	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown(){
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
		//		PO_NavView.changeIdiom(driver, "btnSpanish");
	}

	//	[Prueba1] Registro de Usuario con datos válidos.
	public void PR01(){
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_Register.fillForm(driver, "123@gmail.com" , "12345", "12345", "12345", "12345" );
		//Comprobamos pagina de inicio
		PO_View.checkElement(driver, "text", "MyWallapop");
	}

	//	[Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío, apellidos vacíos).
	public void PR02(){
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_Register.fillForm(driver, "" , "", "", "12345", "12345" );
		//Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}

	//[Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña inválida).
	public void PR03(){//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_Register.fillForm(driver, "123@gmail.com" , "12345", "12345", "12345", "54321" );
		//Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}
	//	[Prueba4] Registro de Usuario con datos inválidos (email existente).
	public void PR04(){
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_Register.fillForm(driver, "123@gmail.com" , "12345", "12345", "12345", "54321" );
		//Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}
	//	[Prueba5] Inicio de sesión con datos válidos (administrador).
	public void PR05(){
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com" , "admin" );
		//COmprobamos que entramos en la pagina de inicio
		PO_View.checkElement(driver, "text", "admin@email.com");
	}
	//	[Prueba6] Inicio de sesión con datos válidos (usuario estándar).
	public void PR06(){
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, "prueba@pueba.com" , "123456" );
		//COmprobamos que entramos en la pagina de inicio
		PO_View.checkElement(driver, "text", "prueba@prueba.com");
	}
	//	[Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email y contraseña vacíos).
	public void PR07(){

	}
	//	[Prueba8] Inicio de sesión con datos válidos (usuario estándar, email existente, pero contraseña
	//			incorrecta).
	public void PR08(){

	}
	//	[Prueba9] Inicio de sesión con datos inválidos (usuario estándar, email no existente en la aplicación).
	public void PR09(){

	}
	//	[Prueba10] Hacer click en la opción de salir de sesión y comprobar que se redirige a la página de inicio
	//	de sesión (Login).
	@Test
	public void PR10(){
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A" , "123456" );
		//Ahora nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
		//COmprobamos que entramos en la pagina de inicio
		PO_View.checkElement(driver, "text", "MyWallapop");
		SeleniumUtils.esperarSegundos(driver, 1);
	}
	//	[Prueba11] Comprobar que el botón cerrar sesión no está visible si el usuario no está autenticado.
	public void PR11(){

	}
	//	[Prueba12] Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el
	//	sistema
	public void PR12(){

	}
	//	[Prueba13] Ir a la lista de usuarios, borrar el primer usuario de la lista, comprobar que la lista se actualiza
	//	y dicho usuario desaparece.
	public void PR13(){

	}
	//	[Prueba14] Ir a la lista de usuarios, borrar el último usuario de la lista, comprobar que la lista se actualiza
	//	y dicho usuario desaparece.
	public void PR14(){

	}
	//	[Prueba15] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se actualiza y dichos
	//	usuarios desaparecen.
	public void PR15(){

	}
	//	[Prueba16] Ir al formulario de alta de oferta, rellenarla con datos válidos y pulsar el botón Submit.
	//	Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	public void PR16(){

	}
	//	[Prueba17] Ir al formulario de alta de oferta, rellenarla con datos inválidos (campo título vacío) y pulsar
	//	el botón Submit. Comprobar que se muestra el mensaje de campo obligatorio.
	public void PR17(){

	}
	//	[Prueba18] Mostrar el listado de ofertas para dicho usuario y comprobar que se muestran todas los que
	//	existen para este usuario. 
	public void PR18(){

	}
	//	[Prueba19] Ir a la lista de ofertas, borrar la primera oferta de la lista, comprobar que la lista se actualiza y
	//	que la oferta desaparece.
	public void PR19(){

	}
	//	[Prueba20] Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar que la lista se actualiza y
	//	que la oferta desaparece.
	public void PR20(){

	}
	//	Prueba21] Hacer una búsqueda con el campo vacío y comprobar que se muestra la página que
	//corresponde con el listado de las ofertas existentes en el sistema
	public void PR21(){

	}
	//[Prueba22] Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar que se
	//muestra la página que corresponde, con la lista de ofertas vacía.
	public void PR22(){

	}

	//Prueba23] Sobre una búsqueda determinada (a elección de desarrollador), comprar una oferta que deja
	//un saldo positivo en el contador del comprobador. Y comprobar que el contador se actualiza
	//correctamente en la vista del comprador.
	public void PR23(){

	}
	//[Prueba24] Sobre una búsqueda determinada (a elección de desarrollador), comprar una oferta que deja
	//un saldo 0 en el contador del comprobador. Y comprobar que el contador se actualiza correctamente en
	//la vista del comprador.
	public void PR24(){

	}

	//[Prueba25] Sobre una búsqueda determinada (a elección de desarrollador), intentar comprar una oferta
	//que esté por encima de saldo disponible del comprador. Y comprobar que se muestra el mensaje de
	//saldo no suficiente.
	public void PR25(){

	}

	//[Prueba26] Ir a la opción de ofertas compradas del usuario y mostrar la lista. Comprobar que aparecen
	//las ofertas que deben aparecer.
	public void PR26(){

	}


	//[Prueba27] Visualizar al menos cuatro páginas en Español/Inglés/Español (comprobando que algunas
	//		de las etiquetas cambian al idioma correspondiente). Página principal/Opciones Principales de
	//Usuario/Listado de Usuarios de Admin/Vista de alta de Oferta.
	public void PR27(){

	}
	//
	//[Prueba28] Intentar acceder sin estar autenticado a la opción de listado de usuarios del administrador. Se
	//deberá volver al formulario de login.
	public void PR28(){

	}
	//[Prueba29] Intentar acceder sin estar autenticado a la opción de listado de ofertas propias de un usuario
	//estándar. Se deberá volver al formulario de login.
	public void PR29(){

	}
	//[Prueba30] Estando autenticado como usuario estándar intentar acceder a la opción de listado de
	//usuarios del administrador. Se deberá indicar un mensaje de acción prohibida
	public void PR30(){

	}
	//
	//[Prueba31] Sobre una búsqueda determinada de ofertas (a elección de desarrollador), enviar un mensaje
	//a una oferta concreta. Se abriría dicha conversación por primera vez. Comprobar que el mensaje aparece
	//en el listado de mensajes.
	public void PR31(){

	}
	//[Prueba32] Sobre el listado de conversaciones enviar un mensaje a una conversación ya abierta.
	//Comprobar que el mensaje aparece en la lista de mensajes.
	public void PR32(){

	}
	//[Prueba33] Mostrar el listado de conversaciones ya abiertas. Comprobar que el listado contiene las
	//conversaciones que deben ser.
	public void PR33(){

	}
	//[Prueba34] Sobre el listado de conversaciones ya abiertas. Pinchar el enlace Eliminar de la primera y
	//comprobar que el listado se actualiza correctamente.
	public void PR34(){

	}
	//[Prueba35] Sobre el listado de conversaciones ya abiertas. Pinchar el enlace Eliminar de la última y
	//comprobar que el listado se actualiza correctamente.
	public void PR35(){

	}
	//
	//[Prueba36] Al crear una oferta marcar dicha oferta como destacada y a continuación comprobar: i) que
	//aparece en el listado de ofertas destacadas para los usuarios y que el saldo del usuario se actualiza
	//adecuadamente en la vista del ofertante (-20).
	public void PR36(){

	}
	//[Prueba37] Sobre el listado de ofertas de un usuario con menos de 20 euros de saldo, pinchar en el
	//enlace Destacada y a continuación comprobar: i) que aparece en el listado de ofertas destacadas para los
	//usuarios y que el saldo del usuario se actualiza adecuadamente en la vista del ofertante (-20).
	public void PR37(){

	}
	//[Prueba38] Sobre el listado de ofertas de un usuario con menos de 20 euros de saldo, pinchar en el
	//enlace Destacada y a continuación comprobar que se muestra el mensaje de saldo no suficiente.
	public void PR38(){

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas

		driver.quit();
	}

}
