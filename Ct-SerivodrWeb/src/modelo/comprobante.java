package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

/**
 * Servlet implementation class comprobante
 */
@WebServlet("/comprobante")
public class comprobante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public comprobante() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		imprimir(request,response);
	}
	
	protected void imprimir(HttpServletRequest request, HttpServletResponse response) {
		String fechaSorteo = request.getParameter("fecha");
		String nombre = request.getParameter("nom");
		String apellido = request.getParameter("ape");
		String funcion = request.getParameter("funcion");
		String espectaculo = request.getParameter("espectaculo");
		String premio = request.getParameter("premio");
		
		Calendar cal = Calendar.getInstance();
		
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH) + 1;
		int ano = cal.get(Calendar.YEAR);
		String nomPDF = "Comprobante" + nombre +"_"+ dia + mes + ano + ".pdf";
		String fullFile = System.getProperty("user.home") + "\\" + nomPDF;
		try {
			Document pdf = new Document();
			FileOutputStream fichero = new FileOutputStream(fullFile);
			
			//ABRIMOS EL ARCHIVO
			PdfWriter.getInstance(pdf, fichero);
			pdf.open();
			
			//ESCRIBIMOS TITULO
			Paragraph titulo = new Paragraph("Sorteo del espectaculo " + espectaculo + "\n\n",
					FontFactory.getFont("arial",22,Font.BOLD,BaseColor.BLACK)
					);
			pdf.add(titulo);
			
			Paragraph contenido = new Paragraph("Se certifica que "+nombre+" "+apellido+" ha ganado un premio en el sorteo de la funcion "+ funcion +" del espectaculo "+ espectaculo +".");
			pdf.add(contenido);
			
			contenido = new Paragraph("\nPremio: " + premio);
			pdf.add(contenido);
			
			contenido = new Paragraph("\n\nEl sorteo fue realizado en la fecha: " + fechaSorteo);
			pdf.add(contenido);
			
			pdf.add(new Paragraph("\n\n"));
			
			contenido = new Paragraph("                                                                               Que disfrutes tu premio!");
			pdf.add(contenido);
			
			contenido = new Paragraph("                                                                               El equipo de CoronaTicketsUy");
			pdf.add(contenido);
			
			
			pdf.close();
			
			//PrintWriter out = response.getWriter();
			response.setHeader("Content-disposition","inline; filename='" + fullFile + "'");
			response.setContentType("application/pdf");
                        
            FileInputStream fileInputStream = new FileInputStream(fullFile);
    		ServletOutputStream responseOutputStream = response.getOutputStream();
            
    		int bytes;
    		while ((bytes = fileInputStream.read()) != -1) {
    			responseOutputStream.write(bytes);
    		}
    		
		} catch(DocumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
}
